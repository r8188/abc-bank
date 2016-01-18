package com.abc;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import com.abc.bank.Bank; 
import com.abc.customer.Customer;
import com.abc.util.DateUtil;

import static org.junit.Assert.assertEquals;

public class AccountTest {
	private static final double DOUBLE_DELTA = 1e-15;
	private static final String dFormat = "yyyyMMdd";

	@Test
	public void withdrawalExist() {
		Bank bank = new Bank();
		Account checkingAccount = new CheckingAccount();
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
		Account checkingAccount = new CheckingAccount();
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
		Account savingsAccount = new SavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));
		savingsAccount.deposit(3000.0, DateUtil.getPreviousDate(2, dFormat));
		savingsAccount.deposit(4000.0, DateUtil.getPreviousDate(2, dFormat));
		savingsAccount.withdraw(2000.0, DateUtil.getPreviousDate(10, dFormat));
		savingsAccount.deposit(3000.0, DateUtil.getPreviousDate(1, dFormat));
		assertEquals(false, savingsAccount.checkIfWithdrawalExists(10));
	}

	@Test
	public void maxi_savings_account_without_withdrawal() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new MaxiSavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(3000.0, DateUtil.getPreviousDate(1, dFormat));
		maxiSavingsAccount.deposit(4000.0, DateUtil.getPreviousDate(1, dFormat));
		assertEquals(350.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_with_withdrawal() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new MaxiSavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(3000.0, DateUtil.getPreviousDate(1, dFormat));
		maxiSavingsAccount.deposit(4000.0, DateUtil.getPreviousDate(1, dFormat));
		maxiSavingsAccount.withdraw(2000.0, DateUtil.getPreviousDate(1, dFormat));
		assertEquals(5.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_with_withdrawal2() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new MaxiSavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(3000.0, DateUtil.getPreviousDate(1, dFormat));
		maxiSavingsAccount.deposit(4000.0, DateUtil.getPreviousDate(1, dFormat));
		maxiSavingsAccount.withdraw(2000.0, DateUtil.getPreviousDate(1, dFormat));
		assertEquals(5.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
}
