package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
	private volatile static DateProvider instance = null;

	public static DateProvider getInstance() {
		if (instance == null) {
			synchronized (DateProvider.class) {
				if (instance == null) {
					instance = new DateProvider();
				}
			}
		}
		return instance;
	}

	public Date now() {
		return Calendar.getInstance().getTime();
	}
}
