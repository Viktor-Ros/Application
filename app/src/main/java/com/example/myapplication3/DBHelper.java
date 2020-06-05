package com.example.myapplication3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyApplication3";
    public static final String TABLE_NAME = "products";
    public static final String KEY_ID = "_id";
    public static final String EATING_NAME = "eatingName";
    public static final String PRODUCT_NAME = "productName";
    public static final String PRODUCT_MASS = "productMass";
    public static final String PRODUCT_PROTEINS = "protein";
    public static final String PRODUCT_FATS = "fat";
    public static final String PRODUCT_CARHYDS = "carbohydrat";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "("

                + KEY_ID + " integer primary key,"
                + EATING_NAME + " text,"
                + PRODUCT_NAME + " text,"
                + PRODUCT_MASS + " text,"
                + PRODUCT_PROTEINS + " text,"
                + PRODUCT_FATS + " text,"
                + PRODUCT_CARHYDS + " text"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
