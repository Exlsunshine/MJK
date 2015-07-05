package com.magicmed.trend;

public class AbstractStatisticData 
{
	private int year;
	private int month;
	private int dayOfMonth;
	private int hour;
	private int minute;
	private int second;
	private int data;
	private int objectType;
	
	public AbstractStatisticData(int year, int month, int dayOfMonth, 
			int hour, int minute, int second, 
			int data, int objectType)
	{
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.data = data;
		this.objectType = objectType;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}
}
