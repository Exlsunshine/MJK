package com.magicmed.data;

import android.content.ContentValues;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import com.magicmed.db.*;


/**
 * Created by bin on 5/9/15.
 */
public class DatabaseHelper {

    private static DatabaseHelper _helper;

    private DatabaseHelper(){

    }

    public static DatabaseHelper getDatabaseHelper() {
        if (_helper == null) {
            _helper = new DatabaseHelper();
        }
        return _helper;
    }

    public void InserData( MagicMedRecord data){
        User user = new User();
        MagicMedRecordBuilder builder = new MagicMedRecordBuilder();
        ContentValues values = builder.deconstruct(data);
        int user_id = user.get_user_id();
        values.put("user_id", user_id);

        SQLiteDatabase db = DataBaseImpl.getDatabase().getDb();
        db.insert(DataBaseImpl.TABLE_MAGICMED_DATA, null, values);
    }

    public void DeleteData(int data_id){
        SQLiteDatabase db = DataBaseImpl.getDatabase().getDb();
        User user = new User();
        String[] args = {String.valueOf(user.get_user_id())};
   /*     Cursor cursor;
        String sql = "select * from " + DataBaseImpl.TABLE_MAGICMED_DATA + " ";
        cursor = db.rawQuery(sql , new String[]{});

        String[] args={String.valueOf(cursor.getCount())};
       // String[] args={String.valueOf(data_id)};
        db.delete(DataBaseImpl.TABLE_MAGICMED_DATA,"id=?",args);*/
        db.delete(DataBaseImpl.TABLE_MAGICMED_DATA,"user_id=?",args);
    }

    public ArrayList<MagicMedRecord>  getAllData(){
        ArrayList list = new ArrayList<MagicMedRecord>();

        SQLiteDatabase db = DataBaseImpl.getDatabase().getDb();
        Cursor cursor = null;
        try {
            String sql = "select * from " + DataBaseImpl.TABLE_MAGICMED_DATA + " ";
            cursor = db.rawQuery(sql , new String[]{});
            while (cursor.moveToNext()) {
                MagicMedRecordBuilder builder = new MagicMedRecordBuilder();
                MagicMedRecord med = builder.build( cursor);
                list.add( med);
            }
        } finally {
            if( cursor != null)
                cursor.close();
        }
        return list;
    }
}
