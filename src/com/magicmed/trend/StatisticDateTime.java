package com.magicmed.trend;

import java.util.Date;

class StatisticDateTime
{
	public static final String YEAR = "FragmentTrend.year";
	public static final String MONTH = "FragmentTrend.month";
	public static final String DAY_OF_MONTH = "FragmentTrend.day";
	public static final String HOUR = "FragmentTrend.hour";
	public static final String MINUTE = "FragmentTrend.minute";
	public static final String SECONDS = "FragmentTrend.second";
	
	private int currentYear;
	private int currentMonth;
	private int currentDayOfMonth;

	public StatisticDateTime(int year, int month, int dayOfMonth)
	{
		currentYear = year;
		currentMonth = month;
		currentDayOfMonth = dayOfMonth;
	}
	
	/**
	 * Initiate a StatisticDateTime with current system time.
	 */
	@SuppressWarnings("deprecation")
	public StatisticDateTime()
	{
		Date date = new Date(System.currentTimeMillis());
		currentYear = date.getYear() + 1900;
		currentMonth = date.getMonth() + 1;
		currentDayOfMonth = date.getDate();
	}
	
	public void setYear(int year)
	{
		currentYear = year;
	}

	public void setMonth(int month)
	{
		currentMonth = month;
	}

	public void setDayOfMonth(int dayOfMonth)
	{
		currentDayOfMonth = dayOfMonth;
	}
	
	public int getYear()
	{
		return currentYear;
	}

	public int getMonth()
	{
		return currentMonth;
	}
	
	public int getDayOfMonth()
	{
		return currentDayOfMonth;
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * Check whether the current selected date is today.
	 * @return
	 */
	public boolean isToday()
	{
		Date date = new Date(System.currentTimeMillis());
		return (currentYear == date.getYear() + 1900) &&
			   (currentMonth == date.getMonth() + 1) &&
			   (currentDayOfMonth == date.getDate());
	}
	
	@Override
	public String toString()
	{
		return currentYear + "-" + currentMonth + "-" + currentDayOfMonth;
	}
}