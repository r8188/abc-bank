package com.abc.account;

import java.util.ArrayList;

import com.abc.transaction.Transaction;

public class MaxiSavingsAccount extends AbstractAccount {

	public MaxiSavingsAccount() {
		this.transactions = new ArrayList<Transaction>();
	}

	public double interestEarned(double amount) {
		if (checkIfWithdrawalExists(withdrawalDaysRule)) {
			return amount * 0.001;
		} else {
			return amount * 0.05;
		}
	}

	public int getAccountType() {
		return MAXI_SAVINGS;
	}
}
