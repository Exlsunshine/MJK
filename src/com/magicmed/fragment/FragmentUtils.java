package com.magicmed.fragment;

import com.example.hjrj.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by rei on 2015/6/15.
 */
public class FragmentUtils {

    public FragmentUtils(){

    }

    public void switchContent(Fragment from, Fragment to,  FragmentManager fragmentManager) {
        if (from != to) {
            FragmentTransaction transaction = fragmentManager. beginTransaction();
            if (!to.isAdded()) {    // ���ж��Ƿ�add��
                transaction.hide(from).add(R.id.container, to).addToBackStack(null).commit(); // ���ص�ǰ��fragment��add��һ����Activity��
            } else {
                transaction.hide(from).show(to).addToBackStack(null).commit(); // ���ص�ǰ��fragment����ʾ��һ��
            }
        }
    }
}
