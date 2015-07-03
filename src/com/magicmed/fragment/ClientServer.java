package com.magicmed.fragment;

import com.magicmed.app.EvnCallback;
import com.magicmed.app.MagicClient;
import com.magicmed.app.MagicClientImp;
import com.magicmed.app.util.DateUtil;

import java.util.Date;

/**
 * Created by rei on 2015/7/1.
 */
public class ClientServer
{
	public ClientServer() { }

	public MagicClient server = null;

	public void init()
	{
		this.server = new MagicClientImp();
		this.server.setEvnCallback(new EvnCallback() 
		{
			@Override
			public boolean isNetworkConnected() {
				return true;
			}

			@Override
			public String getSystem() {
				return null;
			}

			@Override
			public String getSystemVersion() {
				return null;
			}

			@Override
			public String getDeviceModel() {
				return null;
			}

			@Override
			public int getAppid() {
				return 0;
			}

			@Override
			public String getAppVersion() {
				return null;
			}

			@Override
			public String getFirmwareVersion() {
				return null;
			}

			@Override
			public double getLongitude() {
				return 0d;
			}

			@Override
			public double getLatitude() {
				return 0d;
			}

			@Override
			public String getString(String key) {
				return null;
			}

			@Override
			public String setString(String key, String value) {
				return null;
			}

		});

		this.server.setLog(new com.magicmed.app.Log() {

			@Override
			public void i(String tag, String msg) {
				System.out.println(DateUtil.formatDate(new Date(),
						DateUtil.SDF_yyyy_MM_dd_HH_mm_ss_SSS)
						+ ":"
						+ tag
						+ " : " + msg);
			}

			@Override
			public void d(String tag, String msg) {
				System.out.println(DateUtil.formatDate(new Date(),
						DateUtil.SDF_yyyy_MM_dd_HH_mm_ss_SSS)
						+ ":"
						+ tag
						+ " : " + msg);
			}

			@Override
			public void e(String tag, String msg) {
				System.err.println(DateUtil.formatDate(new Date(),
						DateUtil.SDF_yyyy_MM_dd_HH_mm_ss_SSS)
						+ ":"
						+ tag
						+ " : " + msg);
			}

		});
		// server.setHost("http://127.0.0.1/js");
		this.server.setHost("http://testdata.magicmed.com.cn");
		// server.setHost("http://127.0.0.1:8080/js");
	}
}
