package com.example.final_goweasyapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bikeRental.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RENTALS = "rentals";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BIKE_ID = "bikeId";
    public static final String COLUMN_START_DATE = "startDate";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_PAYMENT_METHOD = "paymentMethod";
    public static final String COLUMN_TOTAL_AMOUNT = "totalAmount";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RENTALS_TABLE = "CREATE TABLE " + TABLE_RENTALS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BIKE_ID + " TEXT,"
                + COLUMN_START_DATE + " TEXT,"
                + COLUMN_DURATION + " TEXT,"
                + COLUMN_PAYMENT_METHOD + " TEXT,"
                + COLUMN_TOTAL_AMOUNT + " REAL"
                + ")";
        db.execSQL(CREATE_RENTALS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENTALS);
        onCreate(db);
    }
}
