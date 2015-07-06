package com.magicmed.trend;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hjrj.R;
import com.magicmed.fragment.BaseFragment;

public class FragmentTrend extends BaseFragment implements OnClickListener
{
	private static final String DEBUG_TAG = "______FragmentTrend";
	private LinearLayout up;
	private TextView statisticByDay;
	private TextView statisticByMonth;
	private TextView statisticByYear;
	private TextView statisticByBloodPressure;
	private TextView statisticByHeartRate;
	private TextView statisticByOxygen;
	
	private StatisticType statisticType;
	private StatisticObject statisticObject;
	
	@Override
	protected View onChildCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.frg, null);
		
		setupLayouts(v);
		setupListeners();
		initVariables();
		return v;
	}

	private void setupLayouts(View v)
	{
		up = (LinearLayout) v.findViewById(R.id.frg_actionbar_up);
		
		statisticByDay = (TextView) v.findViewById(R.id.frg_date_selection_day);
		statisticByMonth = (TextView) v.findViewById(R.id.frg_date_selection_month);
		statisticByYear = (TextView) v.findViewById(R.id.frg_date_selection_year);

		statisticByBloodPressure = (TextView) v.findViewById(R.id.frg_object_blood_pressure);
		statisticByHeartRate = (TextView) v.findViewById(R.id.frg_object_heart_rate);
		statisticByOxygen = (TextView) v.findViewById(R.id.frg_object_oxygen);
	}
	
	private void setupListeners()
	{
		up.setOnClickListener(this);
		
		statisticByDay.setOnClickListener(this);
		statisticByMonth.setOnClickListener(this);
		statisticByYear.setOnClickListener(this);
		
		statisticByBloodPressure.setOnClickListener(this);
		statisticByHeartRate.setOnClickListener(this);
		statisticByOxygen.setOnClickListener(this);
	}
	
	private void initVariables()
	{
		statisticType = new StatisticType();
		statisticObject = new StatisticObject();
	}
	
	@Override
	public void onClick(View arg0) 
	{
		int itemID = arg0.getId();
		
		switch(itemID)
		{
		case R.id.frg_actionbar_up:
			Log.i(DEBUG_TAG, "Back to home.");
			/*
			//Insert fake date to database.
			ArrayList<MagicMedRecord> records = GenerateFakeData.MakeData();
			for (int i = 0; i < records.size(); i++)
			{
				Log.d(DEBUG_TAG, "==============" + (i + 1) + "==============");
				Log.d(DEBUG_TAG, "Blood pressure\t" + records.get(i).get_mean_bp());
				Log.d(DEBUG_TAG, "Heart rate\t" + records.get(i).get_heart_rate());
				Log.d(DEBUG_TAG, "Oxygen\t" + records.get(i).get_spo2());
				Log.d(DEBUG_TAG, "Date\t" + DatabaseConverter.millisecondsToDate(records.get(i).get_measure_begin_time()));
				DatabaseHelper.getDatabaseHelper().InserData(records.get(i));
			}
			Log.d(DEBUG_TAG, "Total\t" + records.size());
			*/
			
			try 
			{
				JSONObject date = new JSONObject();
				date.put(StatisticDateTime.YEAR, 2011);
				date.put(StatisticDateTime.MONTH, 3);
				date.put(StatisticDateTime.DAY_OF_MONTH, 14);
				
				DatabaseConverter.getInstance().getDataFromDB(StatisticObject.STATISTIC_BY_BLOOD_PRESURE, 
						StatisticType.STATISTIC_BY_YEAR, date, new RetriveDataListener() 
				{
					@Override
					public void onFinishRetrivingData(String filePath)
					{
						Log.i(DEBUG_TAG, "Got it." + filePath);
					}
				});
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			break;
		case R.id.frg_date_selection_day:
			Log.i(DEBUG_TAG, "Select day.");
			statisticType.setStatisticType(StatisticType.STATISTIC_BY_DAY);
			
			statisticByDay.setBackgroundColor(Color.LTGRAY);
			statisticByMonth.setBackgroundColor(Color.WHITE);
			statisticByYear.setBackgroundColor(Color.WHITE);
			
			JSONObject jsonObj = new JSONObject();
			try {
				jsonObj.put(StatisticDateTime.YEAR, 2015);
				jsonObj.put(StatisticDateTime.MONTH, 06);
				jsonObj.put(StatisticDateTime.DAY_OF_MONTH, 17);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			DatabaseConverter.getInstance().getDataFromDB(1, 1, jsonObj, new RetriveDataListener()
			{
				@Override
				public void onFinishRetrivingData(String filePath) 
				{
					System.out.println("Got data.");
				}
			});
			
			break;
		case R.id.frg_date_selection_month:
			Log.i(DEBUG_TAG, "Select month.");
			statisticType.setStatisticType(StatisticType.STATISTIC_BY_MONTH);

			statisticByDay.setBackgroundColor(Color.WHITE);
			statisticByMonth.setBackgroundColor(Color.LTGRAY);
			statisticByYear.setBackgroundColor(Color.WHITE);
			break;
		case R.id.frg_date_selection_year:
			Log.i(DEBUG_TAG, "Select year.");
			statisticType.setStatisticType(StatisticType.STATISTIC_BY_YEAR);
			
			statisticByDay.setBackgroundColor(Color.WHITE);
			statisticByMonth.setBackgroundColor(Color.WHITE);
			statisticByYear.setBackgroundColor(Color.LTGRAY);
			break;
		case R.id.frg_object_blood_pressure:
			statisticObject.setStatisticObject(StatisticObject.STATISTIC_BY_BLOOD_PRESURE);
			
			statisticByBloodPressure.setBackgroundColor(Color.LTGRAY);
			statisticByHeartRate.setBackgroundColor(Color.WHITE);
			statisticByOxygen.setBackgroundColor(Color.WHITE);
			break;
		case R.id.frg_object_heart_rate:
			statisticObject.setStatisticObject(StatisticObject.STATISTIC_BY_HEART_RATE);
			
			statisticByBloodPressure.setBackgroundColor(Color.WHITE);
			statisticByHeartRate.setBackgroundColor(Color.LTGRAY);
			statisticByOxygen.setBackgroundColor(Color.WHITE);
			break;
		case R.id.frg_object_oxygen:
			statisticObject.setStatisticObject(StatisticObject.STATISTIC_BY_OXYGEN);

			statisticByBloodPressure.setBackgroundColor(Color.WHITE);
			statisticByHeartRate.setBackgroundColor(Color.WHITE);
			statisticByOxygen.setBackgroundColor(Color.LTGRAY);
			break;
		default:
			break;
		}
	}
}