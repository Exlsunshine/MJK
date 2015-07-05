package com.magicmed.trend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.magicmed.data.MagicMedRecord;
import com.magicmed.data.MagicMedRecordBuilder;
import com.magicmed.db.DataBaseImpl;

@SuppressLint("DefaultLocale")
public class DatabaseConverter
{
	private static final String BASE_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Maijike/";
	private static final String DAY_OF_MONTH_HISTORY_RECORDS_CACHE_DIR = BASE_DIRECTORY + "/trend/day.dat";
	private static final String MONTH_HISTORY_RECORDS_CACHE_DIR = BASE_DIRECTORY + "/trend/month.dat";
	private static final String YEAR_HISTORY_RECORDS_CACHE_DIR = BASE_DIRECTORY + "/trend/year.dat";
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
			long lowerBoundTime = getLowerBoundTime(statisticType, dateInfo);
			long upperBoundTime = getUpperBoundTime(statisticType, dateInfo);
			ArrayList<MagicMedRecord> records = retriveData(lowerBoundTime, upperBoundTime);
			processAggregation(statisticObject, statisticType, records);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void SaveDataToFile(String filePath, ArrayList<String> data)
	{
		try 
		{
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            
            for (int i = 0; i < data.size(); i++)
            	writer.append(data.get(i) + "\n");
            
            writer.close();
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private void processAggregation(int statisticObject, int statisticType, ArrayList<MagicMedRecord> records)
	{
		switch (statisticType)
		{
		case StatisticType.STATISTIC_BY_DAY:
			SaveDataToFile(DAY_OF_MONTH_HISTORY_RECORDS_CACHE_DIR, aggregateByDay(statisticObject, records));
			break;
		case StatisticType.STATISTIC_BY_MONTH:
			SaveDataToFile(MONTH_HISTORY_RECORDS_CACHE_DIR, aggregateByMonth(statisticObject, records));
			break;
		case StatisticType.STATISTIC_BY_YEAR:
			SaveDataToFile(YEAR_HISTORY_RECORDS_CACHE_DIR, aggregateByYear(statisticObject, records));
			break;
		default:
			break;
		}
	}
	
	private ArrayList<String> aggregateByDay(int statisticObject, ArrayList<MagicMedRecord> records)
	{
		ArrayList<String> recordsData = new ArrayList<String>();
		
		for (int i = 0; i < records.size(); i++)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(records.get(i).get_measure_begin_time());
//			int year = calendar.get(Calendar.YEAR);
//			int month = calendar.get(Calendar.MONTH);
//			int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
//			int second = calendar.get(Calendar.SECOND);
			
			String line = String.format("%s:%s,%d",addPadding(hour),addPadding(minute),
					getObjectValue(statisticObject, records.get(i)));
			
			recordsData.add(line);
		}
		
		return recordsData;
	}
	
	private int getObjectValue(int statisticObject, MagicMedRecord record)
	{
		int value = 0;
		switch (statisticObject)
		{
		case StatisticObject.STATISTIC_BY_BLOOD_PRESURE:
			value = record.get_mean_bp();
			break;
		case StatisticObject.STATISTIC_BY_HEART_RATE:
			value = record.get_heart_rate();
			break;
		case StatisticObject.STATISTIC_BY_OXYGEN:
			value = record.get_spo2();
			break;
		default:
			break;
		}
		
		return value;
	}
	
	private String addPadding(int number)
	{
		return number >= 10 ? String.valueOf(number) : "0" + String.valueOf(number);
	}
	
	@SuppressLint("UseSparseArrays")
	private ArrayList<String> aggregateByMonth(int statisticObject, ArrayList<MagicMedRecord> records)
	{
		ArrayList<String> recordsData = new ArrayList<String>();
		HashMap<Integer, AssistStatisticCounter> map = new HashMap<Integer, AssistStatisticCounter>();
		
		for (int i = 0; i < records.size(); i++)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(records.get(i).get_measure_begin_time());
			int month = calendar.get(Calendar.MONTH);
			
			int value = getObjectValue(statisticObject, records.get(i));
			if (map.containsKey(month))
				map.get(month).appendData(value);
			else
				map.put(month, new AssistStatisticCounter(month, value));
		}
		
		for (int key : map.keySet())
		{
			String line = String.format("%s,%d", addPadding(key), map.get(key).getValue() / map.get(key).getCount());
			recordsData.add(line);
		}
		
		return recordsData;
	}
	
	private class AssistStatisticCounter
	{
		private int key;
		private int value;
		private int count;
		
		public AssistStatisticCounter(int key, int value)
		{
			this.setKey(key);
			this.setValue(value);
			this.setCount(1);
		}

		public void appendData(int value)
		{
			this.value += value;
			this.count += 1;
		}

		@SuppressWarnings("unused")
		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}
		
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
	}
	
	@SuppressLint("UseSparseArrays")
	private ArrayList<String> aggregateByYear(int statisticObject, ArrayList<MagicMedRecord> records)
	{
		ArrayList<String> recordsData = new ArrayList<String>();
		HashMap<Integer, AssistStatisticCounter> map = new HashMap<Integer, AssistStatisticCounter>();
		
		for (int i = 0; i < records.size(); i++)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(records.get(i).get_measure_begin_time());
			int year = calendar.get(Calendar.YEAR);
			
			int value = getObjectValue(statisticObject, records.get(i));
			if (map.containsKey(year))
				map.get(year).appendData(value);
			else
				map.put(year, new AssistStatisticCounter(year, value));
		}
		
		for (int key : map.keySet())
		{
			String line = String.format("%s,%d", addPadding(key), map.get(key).getValue() / map.get(key).getCount());
			recordsData.add(line);
		}
		
		return recordsData;
	}
	
	/**
	 * Get all history records respect to the given time from database.<br>
	 * @param lowerBoundTime
	 * @param upperBoundTime
	 * @return all history records that satisfy the given condition.
	 */
 	private ArrayList<MagicMedRecord> retriveData(long lowerBoundTime, long upperBoundTime)
	{
		ArrayList<MagicMedRecord> list = new ArrayList<MagicMedRecord>();

	    SQLiteDatabase db = DataBaseImpl.getDatabase().getDb();
	    Cursor cursor = null;
	    try 
	    {
	        String sql = "select * from " + DataBaseImpl.TABLE_MAGICMED_DATA + " "
	        		+ "where measure_begin_time >= ? and measure_begin_time <= ?";
	        cursor = db.rawQuery(sql, 
	        		new String[] {String.valueOf(lowerBoundTime), String.valueOf(upperBoundTime)});
	        while (cursor.moveToNext()) 
	        {
	            MagicMedRecordBuilder builder = new MagicMedRecordBuilder();
	            MagicMedRecord med = builder.build(cursor);
	            list.add(med);
	        }
	    } 
	    finally
	    {
	        if(cursor != null)
	            cursor.close();
	    }
	    
	    return list;
	}
	
	/**
	 * Get the upper bound time of the statistic data
	 * @param statisticType
	 * @param dateInfo
	 * @return the upper bound time<br>
	 * <b>-1</b> if it encounters error.
	 * @throws JSONException
	 */
	private long getUpperBoundTime(int statisticType, JSONObject dateInfo) throws JSONException
	{
		long upperBound = 0;
		
		switch (statisticType)
		{
		case StatisticType.STATISTIC_BY_DAY:
			upperBound = dateToMilLiseconds((Integer)dateInfo.get(StatisticDateTime.YEAR),
					(Integer)dateInfo.get(StatisticDateTime.MONTH),
					(Integer)dateInfo.get(StatisticDateTime.DAY_OF_MONTH),
					23, 59, 59);
			break;
		case StatisticType.STATISTIC_BY_MONTH:
			//I have to get the maximum days in a given month
			int year = (Integer)dateInfo.get(StatisticDateTime.YEAR);
			int month = (Integer)dateInfo.get(StatisticDateTime.MONTH);
			int day = 1;
			Calendar calendar = new GregorianCalendar(year, month, day);

			upperBound = dateToMilLiseconds(year, month, 
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
			break;
		case StatisticType.STATISTIC_BY_YEAR:
			upperBound = dateToMilLiseconds((Integer)dateInfo.get(StatisticDateTime.YEAR),
					12, 31, 23, 59, 59);
			break;
		default:
			upperBound = -1;
			break;
		}
		
		Log.i(DEBUG_TAG, "Got upper bound time.");
		return upperBound;
	}
	
	/**
	 * Get the lower bound time of the statistic data
	 * @param statisticType
	 * @param dateInfo
	 * @return the lower bound time<br>
	 * <b>-1</b> if it encounters error.
	 * @throws JSONException
	 */
	private long getLowerBoundTime(int statisticType, JSONObject dateInfo) throws JSONException
	{
		long lowerBound = 0;
		
		switch (statisticType)
		{
		case StatisticType.STATISTIC_BY_DAY:
			lowerBound = dateToMilLiseconds((Integer)dateInfo.get(StatisticDateTime.YEAR),
					(Integer)dateInfo.get(StatisticDateTime.MONTH),
					(Integer)dateInfo.get(StatisticDateTime.DAY_OF_MONTH),
					0, 0, 0);
			break;
		case StatisticType.STATISTIC_BY_MONTH:
			lowerBound = dateToMilLiseconds((Integer)dateInfo.get(StatisticDateTime.YEAR),
					(Integer)dateInfo.get(StatisticDateTime.MONTH),
					1, 0, 0, 0);
			break;
		case StatisticType.STATISTIC_BY_YEAR:
			lowerBound = dateToMilLiseconds((Integer)dateInfo.get(StatisticDateTime.YEAR),
					1, 1, 0, 0, 0);
			break;
		default:
			lowerBound = -1;
			break;
		}

		Log.i(DEBUG_TAG, "Got lower bound time.");
		return lowerBound;
	}
	
	/**
	 * Convert date to milliseconds format
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @param hour
	 * @param minute
	 * @param second
	 * @return the milliseconds of the given date.
	 */
	private long dateToMilLiseconds(int year, int month, int dayOfMonth, int hour, int minute, int second)
	{
		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, dayOfMonth, hour, minute, second);
		return date.getTime();
	}
}