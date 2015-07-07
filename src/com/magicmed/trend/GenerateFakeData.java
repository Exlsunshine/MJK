package com.magicmed.trend;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;

import com.magicmed.data.MagicMedRecord;

@SuppressLint("SimpleDateFormat")
public class GenerateFakeData
{
	public static ArrayList<MagicMedRecord> MakeData()
	{
		ArrayList<MagicMedRecord> records = new ArrayList<MagicMedRecord>();
		String DATABASE_FILE_PATH = "Magicmed/user1/1.dat";

		for (int year = 2010; year <= 2012; year++) 
		{
			for (int month = 0; month < 12; month++) 
			{
				for (int day = 1; day < 28; day++) 
				{
					MagicMedRecord record = new MagicMedRecord();
					record.set_ABI((short) (100 + (short) (Math.random() % 5)));
					record.set_bp((short) (120 + (int) (Math.random() % 15)), (short) (80 + (int) (Math.random() % 10)), (short) 93);
					record.set_heart_rate((short) (80 + (short) (Math.random() % 5)));
					record.set_PWV((short) (90 + (short) (Math.random() % 5)));
					record.set_spo2((short) (99 + (short) (Math.random() % 5)));
					record.set_storage_path(DATABASE_FILE_PATH);
					record.set_measure_begin_time(dateToMilLiseconds(year, month, day, 10, 23, 30));
					record.set_measure_end_time(dateToMilLiseconds(year, month, day, 10, 25, 30));
					
					records.add(record);
				}
			}
		}
		
		return records;
	}

	private static long dateToMilLiseconds(int year, int month, int dayOfMonth, int hour, int minute, int second)
	{
		GregorianCalendar date = new GregorianCalendar(year, month - 1, dayOfMonth, hour, minute, second);
		return date.getTimeInMillis();
	}
}
