package com.magicmed.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.magicmed.db.DatabaseBuilder;

/**
 * Created by bin on 5/9/15.
 */
public class MagicMedRecordBuilder implements DatabaseBuilder<MagicMedRecord> {
    @Override
    public MagicMedRecord build(Cursor query){

        MagicMedRecord data = new MagicMedRecord();

        String path = query.getString(query.getColumnIndex("storage_path"));
        int heart_data = query.getInt(query.getColumnIndex("heart_rate"));
        int sys_bp = query.getInt(query.getColumnIndex("sys_bp"));
        int dia_bp = query.getInt(query.getColumnIndex("dia_bp"));
        int mean_bp = query.getInt(query.getColumnIndex("mean_bp"));
        int ABI = query.getInt(query.getColumnIndex("ABI"));
        int PWV = query.getInt(query.getColumnIndex("PWV"));
        int spo2 = query.getInt(query.getColumnIndex("spo2"));

        long begin_time = query.getLong(query.getColumnIndex("measure_begin_time"));
        long end_time = query.getLong(query.getColumnIndex("measure_end_time"));

        data.set_storage_path(path);
        data.set_heart_rate(heart_data);
        data.set_bp(sys_bp, dia_bp, mean_bp);
        data.set_ABI(ABI);
        data.set_PWV(PWV);
        data.set_spo2(spo2);
        data.set_measure_begin_time(begin_time);
        data.set_measure_end_time(end_time);

        return data;
    }


    @Override
    public ContentValues deconstruct(MagicMedRecord t)
    {
        ContentValues values = new ContentValues();

        values.put("storage_path", t.get_storage_path() );
        values.put("heart_rate", t.get_heart_rate() );
        values.put("sys_bp", t.get_sys_bp());
        values.put("dia_bp", t.get_dia_bp());
        values.put("mean_bp", t.get_mean_bp() );
        values.put("ABI",t.get_ABI());
        values.put("PWV",t.get_PWV());
        values.put("spo2",t.get_spo2());
        values.put("measure_begin_time",t.get_measure_begin_time());
        values.put("measure_end_time",t.get_measure_end_time());

        return values;
    }
}
