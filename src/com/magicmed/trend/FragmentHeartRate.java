package com.magicmed.trend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjrj.R;
import com.magicmed.fragment.BaseFragment;

public class FragmentHeartRate extends BaseFragment{

	@Override
	protected View onChildCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.frg2, null);
		return v;
	}
}
