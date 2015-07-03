package com.magicmed.trend;

/**
 * Set which object shall be shown in the chart.
 * @author EXLsunshine
 *
 */
class StatisticObject
{
	/**
	 * Statistic object #1 blood pressure.
	 */
	public static final int STATISTIC_BY_BLOOD_PRESURE = 0x01;
	/**
	 * Statistic object #2 heart rate.
	 */
	public static final int STATISTIC_BY_HEART_RATE = 0x02;
	/**
	 * Statistic object #3 oxygen.
	 */
	public static final int STATISTIC_BY_OXYGEN = 0x03;
	
	private int currentSelectedObject = STATISTIC_BY_BLOOD_PRESURE;
	
	public void setStatisticObject(int obj)
	{
		currentSelectedObject = obj;
	}
	
	public int getStatisticObject()
	{
		return currentSelectedObject;
	}
}
