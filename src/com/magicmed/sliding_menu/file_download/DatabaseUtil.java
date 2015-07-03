package com.magicmed.sliding_menu.file_download;

import com.magicmed.app.Measure;
import com.magicmed.data.DatabaseHelper;
import com.magicmed.data.MagicMedRecord;

import java.util.ArrayList;

/**
 * Created by rei on 2015/7/1.
 */
public class DatabaseUtil 
{
	public DatabaseUtil() { }

	public void insert_data(Measure measure) 
	{
		MagicMedRecord record = new MagicMedRecord();
		record.set_storage_path(measure.getWaveFile());
		record.set_heart_rate(measure.getBpPlus());
		record.set_bp(measure.getBpHigh(), measure.getBpLow(), measure.getBpMean());
		record.set_ABI(measure.getAbi());
		record.set_PWV(measure.getPwv());
		record.set_spo2(measure.getSpo2());
		record.set_measure_begin_time(System.currentTimeMillis());
		record.set_measure_end_time(System.currentTimeMillis() + 1000);

		DatabaseHelper.getDatabaseHelper().InserData(record);
		System.out.println("-----------------inset dataBase success---------------\n");
	}

	public void delete_data()
	{
		DatabaseHelper.getDatabaseHelper().DeleteData(1);
	}

	public void read_data()
	{
		ArrayList<MagicMedRecord> list = new ArrayList<MagicMedRecord>();

		list = DatabaseHelper.getDatabaseHelper().getAllData();

		for (int i = 0; i < list.size(); i++)
		{
			MagicMedRecord record = new MagicMedRecord();
			String str;

			record = (MagicMedRecord) list.get(i);
			str = "heart_rate:" + String.valueOf(record.get_heart_rate())
					+ " sys_bp:" + String.valueOf(record.get_sys_bp())
					+ " dia_bp:" + String.valueOf(record.get_dia_bp())
					+ " mean_bp:" + String.valueOf(record.get_mean_bp())
					+ " ABI:" + String.valueOf(record.get_ABI()) + " PWV:"
					+ String.valueOf(record.get_PWV()) + " spo2:"
					+ String.valueOf(record.get_spo2()) + " begin_time:"
					+ String.valueOf(record.get_measure_begin_time())
					+ " end_time:"
					+ String.valueOf(record.get_measure_end_time()).split("");

			System.out.println("---No." + i + ":  " + str + "\n");
		}
	}
}