package com.example.vhlapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String CONTACT_TABLE = "CONTACT_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CONTACT_NAME = "CONTACT_NAME";
    public static final String COLUMN_CONTACT_TYPE = "CONTACT_TYPE";
    public static final String COLUMN_CONTACT_NUMBER = "CONTACT_NUMBER";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CONTACT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CONTACT_NAME + " TEXT, " + COLUMN_CONTACT_TYPE + " TEXT, " + COLUMN_CONTACT_NUMBER + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, contact.getId());
        cv.put(COLUMN_CONTACT_NAME, contact.getName());
        cv.put(COLUMN_CONTACT_TYPE, contact.getType());
        cv.put(COLUMN_CONTACT_NUMBER, contact.getNumber());

        long insert = db.insert(CONTACT_TABLE, null, cv);

        if (insert == 1){
            return true;
        } else {
            return false;

        }
    }

    public ArrayList<Contact> getAll(){
        ArrayList<Contact> returnList = new ArrayList<>();
        //get data from DBfo
        String query = "SELECT * FROM " + CONTACT_TABLE + " ORDER BY " + COLUMN_ID + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null, null);
        if (cursor.moveToFirst()) {
            do {
                int contactID = cursor.getInt(0);
                String contactName = cursor.getString(1);
                String contactType = cursor.getString(2);
                String contactNumber = cursor.getString(3);

                Contact newContact = new Contact(contactID, contactName, contactType, contactNumber);
                returnList.add(newContact);
            } while (cursor.moveToNext());
        } else {
            //eh nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }


    public boolean deleteOne(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + CONTACT_TABLE + " WHERE " + COLUMN_ID + " = " + contact.getId();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }


    public Contact getByName(String name){
        //get data from DBfo
        Contact newContact = null;

        String Name = "'" + name + "'";
        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + COLUMN_CONTACT_NAME + " = " + Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int contactID = cursor.getInt(0);
                String contactName = cursor.getString(1);
                String contactType = cursor.getString(2);
                String contactNumber = cursor.getString(3);

                newContact = new Contact(contactID, contactName, contactType, contactNumber);
            } while (cursor.moveToNext());
        } else {
            //idk rip
        }

        cursor.close();
        db.close();

        return newContact;

    }

}
