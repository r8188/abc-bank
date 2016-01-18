package com.abc.account;

import java.util.Date;
import java.util.List;

import com.abc.transaction.Transaction;

public interface Account {
	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	// deposit with current date if no date is passed.
	void deposit(double amount);

	void deposit(double amount, String dateString);

	// withdraw with current date if no date is passed.
	void withdraw(double amount);

	void withdraw(double amount, String dateString);

	List<Transaction> getTransactions();

	void setTransactions(List<Transaction> transactions);

	double interestEarnedToDate(double transAmount, Date transDate);

	double interestEarned();

	double sumTransactions();

	double getTransactionAmount(Date date);

	List<Transaction> getTransactions(Date date);

	boolean checkIfWithdrawalExists(int noOfDays);

	double interestEarned(double amount);

	int getAccountType();
}