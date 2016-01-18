package com.abc.account;

import java.util.ArrayList;

import com.abc.transaction.Transaction;

public class SavingsAccount extends AbstractAccount {
	
	public SavingsAccount() {
		this.transactions = new ArrayList<Transaction>();
	}

	public double interestEarned(double amount) {
		if (amount <= 1000)
			return amount * 0.001;
		else
			return 1 + (amount - 1000) * 0.002;
	}

	public int getAccountType() {
		return SAVINGS;
	}

}
