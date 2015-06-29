package com.magicmed.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bin on 5/9/15.
 */
public class MagicMedRecord {
    private String _storage_path;       // 数据文件存储路径
    private int _heart_rate;          // 心率
    private int _sys_bp, _dia_bp, _mean_bp;    // 血压
    private int _ABI, _PWV, _spo2;             // ABI, PWV, spo2
    private long _measure_begin_time, _measure_end_time;      // 起始时间

    public MagicMedRecord(){
        _storage_path = null;
        _heart_rate = 0;
        _sys_bp = 0; _dia_bp = 0; _mean_bp = 0;
        _ABI = 0; _PWV = 0; _spo2 = 0;
        _measure_begin_time = 0; _measure_end_time = 0;
    }

    public void set_storage_path(String path){
        _storage_path = path;
    }

    public void set_heart_rate(int hr){
        _heart_rate = hr;
    }

    public void set_bp(int sys, int dia, int mean){
        _sys_bp = sys;
        _dia_bp = dia;
        _mean_bp = mean;
    }

    public void set_spo2(int spo2){
        _spo2 = spo2;
    }

    public void set_PWV(int PWV){
        _PWV = PWV;
    }

    public void set_ABI(int ABI){
        _ABI = ABI;
    }

    public void set_measure_begin_time(long t0){
        _measure_begin_time = t0;
    }

    public void set_measure_end_time(long t1){
        _measure_end_time = t1;
    }

    public int get_heart_rate(){ return _heart_rate;}
    public int get_sys_bp(){ return _sys_bp;}
    public int get_dia_bp(){ return _dia_bp;}
    public int get_mean_bp(){ return _mean_bp;}
    public int get_ABI(){ return _ABI;}
    public int get_PWV(){ return _PWV;}
    public int get_spo2(){ return _spo2;}
    public long get_measure_begin_time(){ return  _measure_begin_time;}
    public long get_measure_end_time(){ return  _measure_end_time;}
    public String get_storage_path(){ return _storage_path;}


}
