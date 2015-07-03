package com.magicmed.trend;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DatabaseConverter
{
	private static final String DEBUG_TAG = "______DatabaseConverter";
	private static DatabaseConverter databaseConverter = null;
	private DatabaseConverter() { }
	
	public static DatabaseConverter getInstance()
	{
		if (databaseConverter == null)
			databaseConverter = new DatabaseConverter();
		
		return databaseConverter;
	}
	
	public void getDataFromDB(int statisticObject, int statisticType, JSONObject dateInfo, RetriveDataListener callback)
	{
		try 
		{
			Log.i(DEBUG_TAG, String.valueOf(dateInfo.get(StatisticDateTime.YEAR)));
			Log.i(DEBUG_TAG, String.valueOf(dateInfo.get(StatisticDateTime.MONTH)));
			Log.i(DEBUG_TAG, String.valueOf(dateInfo.get(StatisticDateTime.DAY_OF_MONTH)));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
	}
}