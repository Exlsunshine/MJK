package com.magicmed.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;


public abstract class BaseFragment extends Fragment implements BadgeFragmenyListener{
	public static final int BOTTOM_VISIBLE = 0x00000001;
	public static final int BACK_VISBLE = 0x00000010;
	public static final int TOP_INVISIBLE = 0x00000100;
    public static final int SHARE_VISIBLE = 0x00001000;
	protected int mStatus = 0x00000000;
    public BaseFragment() {
		super();
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        FragmentLayout fragmentLayout = new FragmentLayout(getActivity(), this);
		View child = onChildCreateView(inflater, container, savedInstanceState);
        addBadgeView();
        fragmentLayout.addView(child, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		return fragmentLayout;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
        removeBadgeView();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
        clearPref();
	}

	@Override
	/*public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}*/
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	protected abstract View onChildCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState);
	
	public void copy(BaseFragment baseFragment){
		//子类实现
	}
	
	public void updateView(){
		//子类实现
	}
	
	public void disappear(){
		
	}
	
	public void setStatus(int status){
		
	}
	
	public int getStatus(){
		return mStatus;
	}

    @Override
    public void clearPref() {

    }

    @Override
    public void removeBadgeView() {

    }

    @Override
    public void addBadgeView() {

    }

    public void shareClick(){

    }

}
