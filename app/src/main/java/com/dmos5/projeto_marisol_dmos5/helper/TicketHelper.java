package com.dmos5.projeto_marisol_dmos5.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dmos5.projeto_marisol_dmos5.model.Ticket;

public class TicketHelper {

    private SQLiteHelper helper;

    public TicketHelper (Context ctx) {
        helper = new SQLiteHelper(ctx);
    }

    public long ticketInsert(Ticket ticket) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.COLUMN_ID, ticket.getTicketID());
        cv.put(SQLiteHelper.COLUMN_TICKET_NUMBER, ticket.getTicketNumber());
        cv.put(SQLiteHelper.COLUMN_CREATE_TIME, ticket.getCreated());
        cv.put(SQLiteHelper.COLUMN_STATE, ticket.getState());
        cv.put(SQLiteHelper.COLUMN_PRIORITY, ticket.getPriority());
        cv.put(SQLiteHelper.COLUMN_QUEUE, ticket.getQueue());
        cv.put(SQLiteHelper.COLUMN_CUSTOMER, (String) ticket.getCustomerID());

        long id = db.insert(SQLiteHelper.TICKET_TABLE, null, cv);

        db.close();
        return id;
    }

    public Ticket ticketGet(String ticketID) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM "+ SQLiteHelper.TICKET_TABLE;
        String[] argumentos = null;
        if (ticketID != null) {
            sql += " WHERE "+ SQLiteHelper.COLUMN_ID +" = ?";
            argumentos = new String[]{ ticketID };
        }

        Cursor cursor = db.rawQuery(sql, argumentos);
        Ticket ticket = new Ticket();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID));
            ticket.setTicketID(Integer.toString(id));

            String ticketNumber = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TICKET_NUMBER));
            ticket.setTicketNumber(ticketNumber);

            String createTime = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CREATE_TIME));
            ticket.setCreated(createTime);

            String ticketState = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_STATE));
            ticket.setState(ticketState);

            String ticketPriority = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_PRIORITY));
            ticket.setPriority(ticketPriority);

            String ticketQueue = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_QUEUE));
            ticket.setQueue(ticketQueue);

            String ticketCustomerID = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CUSTOMER));
            ticket.setCustomerID(ticketCustomerID);
        }

        cursor.close();
        db.close();
        return ticket;
    }
}
