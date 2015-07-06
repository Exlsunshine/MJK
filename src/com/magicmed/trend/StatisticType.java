package com.magicmed.trend;

/**
 * Set which type of the time unit should be used for the chart.<br>
 * <b>{@link #STATISTIC_BY_DAY}</b> will be applied by default.
 * @author EXLsunshine
 *
 */
class StatisticType
{
	/**
	 * Time unit #1 Draw chart by day.
	 */
	public static final int STATISTIC_BY_DAY = 0x01;
	/**
	 * Time unit #2 Draw chart by month.
	 */
	public static final int STATISTIC_BY_MONTH = 0x02;
	/**
	 * Time unit #3 Draw chart by year.
	 */
	public static final int STATISTIC_BY_YEAR = 0x03;
	
	private int currentSelectedType = STATISTIC_BY_DAY;
	
	public void setStatisticType(int type)
	{
		currentSelectedType = type;
	}
	
	public int getStatisticType()
	{
		return currentSelectedType;
	}
}
