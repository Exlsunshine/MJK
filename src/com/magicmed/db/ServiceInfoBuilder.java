package com.magicmed.db;

import android.content.ContentValues;
import android.database.Cursor;

public class ServiceInfoBuilder implements DatabaseBuilder<ServiceInfoModel>{

	@Override
	public ServiceInfoModel build(Cursor query) {
		ServiceInfoModel model = new ServiceInfoModel();
		long service_info_time = query.getLong(query.getColumnIndex("serviceInformation_time"));
		String content =  query.getString(query.getColumnIndex("service_information"));
		model.setServiceTime(service_info_time);
		model.setInfoContent(content);
		return model;
	}

	@Override
	public ContentValues deconstruct(ServiceInfoModel t) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("serviceInformation_time", t.getServiceTime());
		contentValues.put("service_information", t.getInfoContent());
		return contentValues;
	}

}
