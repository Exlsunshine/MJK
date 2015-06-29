package com.magicmed.db;

import android.content.ContentValues;
import android.database.Cursor;

public interface DataBaseHttpDataBuilder<T> extends DatabaseBuilder<T>{

	
	public T builderHttpData(Cursor query);
	
	public ContentValues update(int post_id, T t);
	
}
