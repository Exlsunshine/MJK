package com.magicmed.trend;

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
}