package com.abc;

import java.util.Date;

public class Transaction {
	private final double amount;
	private final Date transactionDate;
	private final String transactionType;
	private static String dFormat = "yyyyMMdd";

	public Transaction(double amount) {
		this(amount, DateUtil.convertFromDateToString(DateProvider.getInstance().now(), dFormat), dFormat);
	}

	public Transaction(double amount, String dateString, String dFormat) {
		this.amount = amount;
		this.transactionDate = DateUtil.convertfromStringToDate(dateString, dFormat);
		if (amount < 0) {
			this.transactionType = "W";
		} else
			this.transactionType = "D";
	}

	public double getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

}
