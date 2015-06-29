package com.magicmed.trend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.hjrj.R;
import com.magicmed.fragment.BaseFragment;


public class FragmentTrend extends BaseFragment implements OnClickListener
{

	@Override
	protected View onChildCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.frg, null);
		return v;
	}

	@Override
	public void onClick(View arg0)
	{
	}
}