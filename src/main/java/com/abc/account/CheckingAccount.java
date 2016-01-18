package com.abc.account;

import java.util.ArrayList;

import com.abc.transaction.Transaction;

public class CheckingAccount extends AbstractAccount {

	public CheckingAccount() {
		this.transactions = new ArrayList<Transaction>();
	}

	public double interestEarned(double amount) {
		return amount * 0.001;
	}

	public int getAccountType() {
		return CHECKING;
	}
}
