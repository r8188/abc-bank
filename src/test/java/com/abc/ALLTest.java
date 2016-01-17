package com.abc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountTest.class, BankTest.class, CustomerTest.class, DateUtilTest.class, TransactionTest.class })
public class ALLTest {

}
