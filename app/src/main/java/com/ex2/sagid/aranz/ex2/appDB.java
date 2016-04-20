package com.ex2.sagid.aranz.ex2;
import android.provider.BaseColumns;

/**
 * DataBase Stracture
 */
public class appDB {

    public static abstract class LocationsEntry implements BaseColumns{
        public static final String TABLE_NAME = "locations";
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String TIME_STAMP = "timeStamp";
        public static final String ADDRESS = "address";
    }
}