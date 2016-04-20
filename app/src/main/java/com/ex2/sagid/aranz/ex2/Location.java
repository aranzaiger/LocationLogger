package com.ex2.sagid.aranz.ex2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by aranz on 19-Apr-16.
 */
public class Location {

    protected int id;
    protected float lat, lon;
    protected  String address;
    protected long timestamp;


    public Location(int id, float lat, float lon, String address, long timestamp){
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.timestamp = timestamp;

    }

    public float getLon() {
        return lon;
    }


    public float getLat() {
        return lat;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void save(SQLiteOpenHelper dbHelper, Context context){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(appDB.LocationsEntry.ADDRESS, this.address);

        String where = appDB.LocationsEntry._ID + " =?";
        String[] whereArgs = {Integer.toString(this.id)};

        db.update(appDB.LocationsEntry.TABLE_NAME, values, where, whereArgs);

        db.close();

        Toast.makeText(context, "Address saved!", Toast.LENGTH_LONG).show();
    }

    public static Cursor getAll(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] selectionArgs = {};

        return db.rawQuery("SELECT * FROM "+appDB.LocationsEntry.TABLE_NAME, selectionArgs);
    }
}
