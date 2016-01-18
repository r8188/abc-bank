package com.abc;

import org.junit.Test;

import com.abc.transaction.Transaction;
import com.abc.util.DateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {
	private static final double DOUBLE_DELTA = 1e-15;
	private static final String dFormat = "yyyyMMdd";

	@Test
	public void transaction() {
		Transaction t = new Transaction(5);
		assertTrue(t instanceof Transaction);
	}

	@Test
	public void transaction_with_date() {
		Transaction t = new Transaction(500, "20160112", dFormat);
		assertTrue(t instanceof Transaction);
		assertEquals("20160112", DateUtil.convertFromDateToString(t.getTransactionDate(), dFormat));
		assertEquals(500.0, t.getAmount(), DOUBLE_DELTA);
	}
}
