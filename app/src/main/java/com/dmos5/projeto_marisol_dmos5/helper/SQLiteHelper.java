package com.dmos5.projeto_marisol_dmos5.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbOTRS";
    private static final int DB_VERSION = 1;
    public static final String  TICKET_TABLE = "ticket";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CREATE_TIME = "create_time";
    public static final String COLUMN_TICKET_NUMBER = "ticket_number";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_QUEUE = "queue";
    public static final String COLUMN_CUSTOMER = "customer_id";

    public SQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String ticketTable = "CREATE TABLE " + TICKET_TABLE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TICKET_NUMBER + " TEXT NOT NULL, " +
                COLUMN_STATE + " TEXT NOT NULL, " +
                COLUMN_PRIORITY + " TEXT NOT NULL, " +
                COLUMN_QUEUE + " TEXT NOT NULL , " +
                COLUMN_CUSTOMER + " TEXT, " +
                COLUMN_CREATE_TIME + " TEXT NOT NULL ) ;";

        sqLiteDatabase.execSQL(ticketTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
