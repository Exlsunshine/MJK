package com.magicmed.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.hjrj.R;
import com.magicmed.trend.FragmentTrend;

public class MainActivity extends FragmentActivity
{
	private FragmentManager _manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		_manager = getSupportFragmentManager();
		if (savedInstanceState == null)
		{
			FragmentTrend fragment = new FragmentTrend();
			_manager.beginTransaction().add(R.id.container, fragment, "ONE").commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		int id = item.getItemId();

		if (id == R.id.action_settings)
			return true;

		return super.onOptionsItemSelected(item);
	}
}