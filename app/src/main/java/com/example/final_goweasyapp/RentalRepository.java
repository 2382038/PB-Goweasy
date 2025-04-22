package com.example.final_goweasyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RentalRepository {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public RentalRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void saveRental(Rental rental) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_BIKE_ID, rental.getBikeId());
        values.put(DatabaseHelper.COLUMN_START_DATE, rental.getStartDate());
        values.put(DatabaseHelper.COLUMN_DURATION, rental.getDuration());
        values.put(DatabaseHelper.COLUMN_PAYMENT_METHOD, rental.getPaymentMethod());
        values.put(DatabaseHelper.COLUMN_TOTAL_AMOUNT, rental.getTotalAmount());

        db.insert(DatabaseHelper.TABLE_RENTALS, null, values);
        db.close();
    }

    public Cursor getRentals() {
        db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_RENTALS, null);
    }
}
