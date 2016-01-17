package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {
	private static final double DOUBLE_DELTA = 1e-15;
	private static final String dFormat = "yyyyMMdd";

	@Test
	public void withdrawalExist() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.MAXI_SAVINGS);
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);
		checkingAccount.deposit(3000.0, DateUtil.getPreviousDate(2, dFormat));
		checkingAccount.deposit(4000.0, DateUtil.getPreviousDate(2, dFormat));
		checkingAccount.withdraw(2000.0, DateUtil.getPreviousDate(10, dFormat));
		checkingAccount.deposit(3000.0, DateUtil.getPreviousDate(2, dFormat));
		checkingAccount.withdraw(2000.0, DateUtil.getPreviousDate(9, dFormat));

		assertEquals(true, checkingAccount.checkIfWithdrawalExists(10));
	}

	@Test
	public void withdrawalNotExist() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);
		checkingAccount.deposit(3000.0);
		checkingAccount.deposit(4000.0);
		checkingAccount.deposit(3000.0);
		assertEquals(false, checkingAccount.checkIfWithdrawalExists(10));
	}

	@Test
	public void withrawalExistPast10Days() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
		checkingAccount.deposit(3000.0, DateUtil.getPreviousDate(2, dFormat));
		checkingAccount.deposit(4000.0, DateUtil.getPreviousDate(2, dFormat));
		checkingAccount.withdraw(2000.0, DateUtil.getPreviousDate(10, dFormat));
		checkingAccount.deposit(3000.0, DateUtil.getPreviousDate(1, dFormat));
		assertEquals(false, checkingAccount.checkIfWithdrawalExists(10));
	}

	@Test
	public void maxi_savings_account_without_withdrawal() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(3000.0, DateUtil.getPreviousDate(1, dFormat));
		checkingAccount.deposit(4000.0, DateUtil.getPreviousDate(1, dFormat));
		assertEquals(350.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_with_withdrawal() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(3000.0, DateUtil.getPreviousDate(1, dFormat));
		checkingAccount.deposit(4000.0, DateUtil.getPreviousDate(1, dFormat));
		checkingAccount.withdraw(2000.0, DateUtil.getPreviousDate(1, dFormat));
		assertEquals(5.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
}
