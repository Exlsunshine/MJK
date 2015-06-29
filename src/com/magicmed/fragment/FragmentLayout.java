package com.magicmed.fragment;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class FragmentLayout extends LinearLayout{
	private BaseFragment fragment;
	public FragmentLayout(Context context,BaseFragment fragment) {
		super(context);
		this.fragment = fragment;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		if(fragment == null  || fragment.getActivity() == null || ((HomeActivity)this.fragment.getActivity()).getSlidingMenu().isMenuShowing()){
//			return super.onInterceptTouchEvent(ev);
//		}
//		if(fragment.getParentFragment() != null && fragment.getParentFragment().getClass().equals(TabFragment.class)){
//			Class<?> position = ((TabFragment)fragment.getParentFragment()).getCurrentPosition();
//			if(position.equals(fragment.getClass())){
//				return super.onInterceptTouchEvent(ev);
//			}
//		}else{
//			Class<?> position = ((HomeActivity)this.fragment.getActivity()).getCurrentPosition();
//			if(position.equals(fragment.getClass())){
//				return super.onInterceptTouchEvent(ev);
//			}
//		}
		return super.onInterceptTouchEvent(ev);
	}
	
	
	

}
