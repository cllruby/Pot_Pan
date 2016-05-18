package com.example.ziyang.potpan.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ziyang on 2016/4/13.
 */
public class MaterialDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "MaterialDB";
    public static final String ID = "_id";
    public static final String MATERIALNAME = "materialname";
    public static final String MATERIALURL = "materialurl";

    public MaterialDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "MaterialDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MATERIALNAME + " VARCHAR(20) NOT NULL, " +
                        MATERIALURL + " STRING NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
