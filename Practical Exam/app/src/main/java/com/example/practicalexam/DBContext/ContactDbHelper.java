package com.example.practicalexam.DBContext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.practicalexam.Model.ContactContract;

public class ContactDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactInfo.db";
    private static final int DATABASE_VERSION = 1;

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the Contact table when the database is created
        db.execSQL(ContactContract.ContactEntry.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here if needed
        // This method is called when DATABASE_VERSION is increased.
    }
}

