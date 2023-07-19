package com.example.practicalexam.DBContext;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.practicalexam.DBContext.ContactDbHelper;
import com.example.practicalexam.Model.Contact;
import com.example.practicalexam.Model.ContactContract;

import java.util.ArrayList;
import java.util.List;

public class ContactDataSource {
    private SQLiteDatabase database;
    private ContactDbHelper dbHelper;

    public ContactDataSource(Context context) {
        dbHelper = new ContactDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactContract.ContactEntry.COLUMN_EMP_NAME, contact.getEmpName());
        values.put(ContactContract.ContactEntry.COLUMN_EMP_EMAIL, contact.getEmpEmail());
        values.put(ContactContract.ContactEntry.COLUMN_EMP_ADDRESS, contact.getEmpAddress());
        values.put(ContactContract.ContactEntry.COLUMN_NOTES, contact.getNotes());

        return database.insert(ContactContract.ContactEntry.TABLE_NAME, null, values);
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] projection = {
                ContactContract.ContactEntry.COLUMN_EMP_ID,
                ContactContract.ContactEntry.COLUMN_EMP_NAME,
                ContactContract.ContactEntry.COLUMN_EMP_EMAIL,
                ContactContract.ContactEntry.COLUMN_EMP_ADDRESS,
                ContactContract.ContactEntry.COLUMN_NOTES
        };
        Cursor cursor = database.query(
                ContactContract.ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_EMP_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_EMP_NAME));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_EMP_EMAIL));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_EMP_ADDRESS));
                @SuppressLint("Range") String notes = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NOTES));

                Contact contact = new Contact(id, name, email, address, notes);
                contactList.add(contact);
            }
            cursor.close();
        }
        database.close();
        return contactList;
    }

    // Add methods for updating and deleting contacts if needed
}
