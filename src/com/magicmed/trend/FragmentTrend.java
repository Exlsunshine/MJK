package com.magicmed.trend;

import java.util.Date;

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
	private TextView statisticByBloodPressure;
	private TextView statisticByHeartRate;
	private TextView statisticByOxygen;
	private TextView statisticByDay;
	private TextView statisticByMonth;
	private TextView statisticByYear;
	
	private TextView masterDate;
	private TextView slaveDate;
	private TextView previous;
	private TextView next;
	
	
	private StatisticType statisticType;
	private StatisticObject statisticObject;
	private StatisticDateTime statisticDateTime;
	
	@Override
	protected View onChildCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.frg, null);
		
		setupLayouts(v);
		setupListeners();
		initVariables();
		initUI();
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
		
		masterDate = (TextView) v.findViewById(R.id.frg_master_date);
		slaveDate = (TextView) v.findViewById(R.id.frg_slave_date);
		
		previous = (TextView) v.findViewById(R.id.frg_previous);
		next = (TextView) v.findViewById(R.id.frg_next);
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
		
		previous.setOnClickListener(this);
		next.setOnClickListener(this);
	}
	
	private void initVariables()
	{
		statisticType = new StatisticType();
		statisticObject = new StatisticObject();
		statisticDateTime = new StatisticDateTime();
	}
	
	private void initUI()
	{
		updateSelectDateUI();
	}
	
	@SuppressWarnings("deprecation")
	private void updateSelectDateUI()
	{
		switch (statisticType.getStatisticType()) 
		{
		case StatisticType.STATISTIC_BY_DAY:
			masterDate.setText(statisticDateTime.getYear() + "年 " + statisticDateTime.getMonth() + "月");
			slaveDate.setText(statisticDateTime.isToday() ? "今天" : statisticDateTime.getDayOfMonth() + "日");
			
			Date previousDate = new Date(statisticDateTime.getYear() - 1900, 
					statisticDateTime.getMonth() - 1, 
					statisticDateTime.getDayOfMonth() + -1);
			Date nextDate = new Date(statisticDateTime.getYear() - 1900, 
					statisticDateTime.getMonth() - 1, 
					statisticDateTime.getDayOfMonth() + 1);
			
			previous.setText(previousDate.getDate() + "号");
			next.setText(nextDate.getDate() + "号");
			break;
		case StatisticType.STATISTIC_BY_MONTH:
			masterDate.setText(statisticDateTime.getYear() + "年");
			slaveDate.setText(statisticDateTime.getMonth() + "月");
			
			int previousMonth = statisticDateTime.getMonth() - 1;
			previousMonth = previousMonth < 1 ? 12 : previousMonth;
			int nextMonth = statisticDateTime.getMonth() + 1;
			nextMonth = nextMonth > 12 ? 1 : nextMonth;
			
			previous.setText(previousMonth + "月");
			next.setText(nextMonth + "月");
			break;
		case StatisticType.STATISTIC_BY_YEAR:
			masterDate.setText("");
			slaveDate.setText(statisticDateTime.getYear() + "年");
			
			previous.setText((statisticDateTime.getYear() - 1) + "年");
			next.setText((statisticDateTime.getYear() + 1) + "年");
			break;
		default:
			break;
		}
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
			onDaySelect();
			updateSelectDateUI();
			break;
		case R.id.frg_date_selection_month:
			onMonthSelect();
			updateSelectDateUI();
			break;
		case R.id.frg_date_selection_year:
			onYearSelect();
			updateSelectDateUI();
			break;
		
		case R.id.frg_object_blood_pressure:
			onBloodPressureSelect();
			break;
		case R.id.frg_object_heart_rate:
			onHeartRateSelect();
			break;
		case R.id.frg_object_oxygen:
			onOxygenSelect();
			break;
			

		case R.id.frg_previous:
			updateSelectDate(-1);
			updateSelectDateUI();
			break;
		case R.id.frg_next:
			updateSelectDate(1);
			updateSelectDateUI();
			break;
		default:
			break;
		}
	}
	
	@SuppressWarnings("deprecation")
	private void updateSelectDate(int increment)
	{
		switch (statisticType.getStatisticType())
		{
		case StatisticType.STATISTIC_BY_DAY:
			Date date = new Date(statisticDateTime.getYear() - 1900, 
					statisticDateTime.getMonth() - 1, 
					statisticDateTime.getDayOfMonth() + increment);
			
			statisticDateTime.setYear(date.getYear() + 1900);
			statisticDateTime.setMonth(date.getMonth() + 1);
			statisticDateTime.setDayOfMonth(date.getDate());
			break;
		case StatisticType.STATISTIC_BY_MONTH:
			int year = statisticDateTime.getYear();
			int month = statisticDateTime.getMonth() + increment;
			int dayOfMonth = statisticDateTime.getDayOfMonth();
			
			if (month < 1)
			{
				year--;
				month = 12;
				dayOfMonth = 31;
			}
			else if(month > 12)
			{
				year++;
				month = 1;
				dayOfMonth = 1;
			}
			statisticDateTime.setYear(year);
			statisticDateTime.setMonth(month);
			statisticDateTime.setDayOfMonth(dayOfMonth);
			break;
		case StatisticType.STATISTIC_BY_YEAR:
			statisticDateTime.setYear(statisticDateTime.getYear() + increment);
			break;

		default:
			break;
		}
	}
	
	public void onDaySelect()
	{
		Log.i(DEBUG_TAG, "Select day.");
		statisticType.setStatisticType(StatisticType.STATISTIC_BY_DAY);
		
		statisticByDay.setBackgroundColor(Color.LTGRAY);
		statisticByMonth.setBackgroundColor(Color.WHITE);
		statisticByYear.setBackgroundColor(Color.WHITE);
	}
	
	public void onMonthSelect()
	{
		Log.i(DEBUG_TAG, "Select month.");
		statisticType.setStatisticType(StatisticType.STATISTIC_BY_MONTH);

		statisticByDay.setBackgroundColor(Color.WHITE);
		statisticByMonth.setBackgroundColor(Color.LTGRAY);
		statisticByYear.setBackgroundColor(Color.WHITE);
	}
	
	public void onYearSelect()
	{
		Log.i(DEBUG_TAG, "Select year.");
		statisticType.setStatisticType(StatisticType.STATISTIC_BY_YEAR);
		
		statisticByDay.setBackgroundColor(Color.WHITE);
		statisticByMonth.setBackgroundColor(Color.WHITE);
		statisticByYear.setBackgroundColor(Color.LTGRAY);
	}
	
	public void onBloodPressureSelect()
	{
		statisticObject.setStatisticObject(StatisticObject.STATISTIC_BY_BLOOD_PRESURE);
		
		statisticByBloodPressure.setBackgroundColor(Color.LTGRAY);
		statisticByHeartRate.setBackgroundColor(Color.WHITE);
		statisticByOxygen.setBackgroundColor(Color.WHITE);
	}
	
	public void onHeartRateSelect()
	{
		statisticObject.setStatisticObject(StatisticObject.STATISTIC_BY_HEART_RATE);
		
		statisticByBloodPressure.setBackgroundColor(Color.WHITE);
		statisticByHeartRate.setBackgroundColor(Color.LTGRAY);
		statisticByOxygen.setBackgroundColor(Color.WHITE);
	}
	
	public void onOxygenSelect()
	{
		statisticObject.setStatisticObject(StatisticObject.STATISTIC_BY_OXYGEN);

		statisticByBloodPressure.setBackgroundColor(Color.WHITE);
		statisticByHeartRate.setBackgroundColor(Color.WHITE);
		statisticByOxygen.setBackgroundColor(Color.LTGRAY);
	}
}