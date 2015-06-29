package com.magicmed.db;

import android.content.ContentValues;
import android.database.Cursor;

public interface DatabaseBuilder<T>{
	
	
	public T build(Cursor query);
	
	
	
	
	public ContentValues deconstruct(T t);
	

	
	
	
}
