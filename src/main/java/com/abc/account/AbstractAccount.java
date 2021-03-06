package com.abc.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.transaction.Transaction;
import com.abc.util.DateProvider;
import com.abc.util.DateUtil;

public abstract class AbstractAccount implements Account {

	public static final String dFormat = "yyyyMMdd";
	public static final int withdrawalDaysRule = 10;
	protected List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	public void deposit(double amount, String dateString) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount, dateString, dFormat));
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else if (amount > sumTransactions()) {
			throw new IllegalArgumentException(
					"amount must be less than or equal to the balance: " + sumTransactions());
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

	public void withdraw(double amount, String dateString) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else if (amount > sumTransactions()) {
			throw new IllegalArgumentException(
					"amount must be less than or equal to the balance: " + sumTransactions());
		} else {
			transactions.add(new Transaction(-amount, dateString, dFormat));
		}
	}

	public double interestEarnedToDate(double transAmount, Date transDate) {
		double amount = 0.0;
		for (int i = DateUtil.getNofDays(transDate, DateProvider.getInstance().now()); i > 0; i--) {
			amount += interestEarned(transAmount);
		}
		return amount;
	}

	public double interestEarned() {
		double amount = 0.0;
		for (Transaction t : transactions) {
			amount += interestEarnedToDate(t.getAmount(), t.getTransactionDate());
		}
		return amount;
	}

	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.getAmount();
		return amount;
	}

	private boolean checkIfTransactionExist(Date date) {
		for (Transaction t : transactions) {
			if (DateUtil.compareDate(t.getTransactionDate(), date) == 0) {
				return true;
			}
		}
		return false;
	}

	public double getTransactionAmount(Date date) {
		double amount = 0.0;
		for (Transaction t : transactions) {
			if (DateUtil.compareDate(t.getTransactionDate(), date) == 0) {
				amount = t.getAmount();
			}
		}
		return amount;
	}

	public List<Transaction> getTransactions(Date date) {
		List<Transaction> trans = new ArrayList<Transaction>();
		for (Transaction t : transactions) {
			if (DateUtil.compareDate(t.getTransactionDate(), date) == 0)
				trans.add(t);
		}
		return trans;
	}

	public boolean checkIfWithdrawalExists(int noOfDays) {
		boolean withdrawalFlag = false;
		for (int i = noOfDays - 1; i > -1; i--) {
			Date date = DateUtil.convertfromStringToDate(DateUtil.getPreviousDate(i, dFormat), dFormat);
			if (checkIfTransactionExist(date)) {
				List<Transaction> transList = getTransactions(date);
				for (Transaction t : transList) {
					if (t.getTransactionType().equals("W"))
						return true;
				}
			}
		}
		return withdrawalFlag;
	}

}