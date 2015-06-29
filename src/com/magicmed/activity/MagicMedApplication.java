package com.magicmed.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class MagicMedApplication extends Application {
	public static final String PACKAGE_NAME = "com.magicmed.healthECG";
	private static MagicMedApplication sAppcation;
	private SharedPreferences mPreferences;

	public void onCreate() {
		super.onCreate();
		sAppcation = this;
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

	}

	public static float getDimen(int id) {
		return sAppcation.getResources().getDimension(id);
	}

	public static String getStringRes(int id, Object... obj) {
		return sAppcation.getResources().getString(id, obj);
	}

	public static String[] getStrings(int id) {
		return sAppcation.getResources().getStringArray(id);
	}

	public static SharedPreferences getPreferences() {
		return sAppcation.mPreferences;
	}

	public static MagicMedApplication getInstance() {
		return sAppcation;
	}

	public static int getScreenHeight() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager manager = (WindowManager) sAppcation
				.getSystemService(Context.WINDOW_SERVICE);
		manager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}

	public static int getScreenWidth() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager manager = (WindowManager) sAppcation
				.getSystemService(Context.WINDOW_SERVICE);
		manager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}

	public static String getSoftVersionName() {
		PackageManager manager = sAppcation.getPackageManager();
		try {
			PackageInfo packageInfo = manager.getPackageInfo(
					sAppcation.getPackageName(), PackageManager.GET_ACTIVITIES);
			String versionName = packageInfo.versionName == null ? ""
					: packageInfo.versionName;
			return versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String getSystemVersion() {
		String version = "";
		try {
			version = sAppcation.getPackageManager().getPackageInfo(
					sAppcation.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}

}
