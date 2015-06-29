package com.magicmed.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.magicmed.activity.MagicMedApplication;

public class DataBaseImpl extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static final String DB_NAME = "magicmed";
	public static final String TABLE_USER = "user";
	public static final String TABLE_USER_LOGIN_METHOD = "user_login_method";
	public static final String TABLE_MAGICMED_DATA = "magicmed_data"; //
	public static DataBaseImpl _impl;

	public DataBaseImpl(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	private DataBaseImpl(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	public static DataBaseImpl getDatabase() {
		if (_impl == null) {
			_impl = new DataBaseImpl(MagicMedApplication.getInstance());
		}
		return _impl;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ TABLE_USER_LOGIN_METHOD
				+ " (id INTEGER primary key autoincrement, user_id INTEGER, accessToken vchar(1000), open_id vchar(1000), refreshToken vchar(1000), type INTEGER)");

		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ TABLE_USER
				+ " (id INTEGER primary key, "
				+ "user_name INTEGER, "
				+ "path vchar(100), "
				+ "user_count vchar(30), password vchar(30), city integer, "
				+ "post_id INTEGER, alert_time INTEGER, mobilephone vchar(30), "
				+ "ecg_temp INTEGER, update_list vchar(1000))");

		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ TABLE_MAGICMED_DATA
				+ " (id INTEGER primary key autoincrement, "
				+ "user_id INTEGER, "
				+ "storage_path vchar(100), heart_rate INTEGER, sys_bp INTEGER, dia_bp INTEGER, mean_bp INTEGER, "
				+ "ABI INTEGER, PWV INTEGER, spo2 INTEGER, "
				+ "measure_begin_time INTEGER, post_id INTEGER,measure_end_time INTEGER,type INTEGER )");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void close() {
		super.close();
		_impl = null;
	}

	public void closeCursor(Cursor cursor) {
		if (!cursor.isClosed()) {
			cursor.close();
		}
	}

	public SQLiteDatabase getDb() {
		return this.getWritableDatabase();
	}

}
