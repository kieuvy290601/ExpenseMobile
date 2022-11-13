package com.example.demo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final  String DATABASE_NAME = "Expense_Management.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TRIP_TABLE = "Trips";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESTINATION = "des";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_DESC = "description";
    public static final String COLUMN_RISK = "risk";

    private static final String EXPENSE_TABLE = "Expenses";
    private static final String COLUMN_ExID = "_id";
    private static final String COLUMN_TrID = "trId";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TIME = "time";



    DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TRIP_TABLE +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESTINATION + " TEXT, " +
                COLUMN_DATE + " TEXT, "  +
                COLUMN_RISK + " TEXT, " +
                COLUMN_DESC + " TEXT)";
        db.execSQL(query);

        String query1 = "CREATE TABLE " + EXPENSE_TABLE +
                " (" + COLUMN_ExID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TrID + " INTEGER, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, "  +
                COLUMN_TIME + " TEXT, " +
                "FOREIGN KEY (trId) REFERENCES Trips(id))";
        db.execSQL(query1);
    }



    public boolean addTrip(String name, String des, String date, String risk, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentSave = new ContentValues();

        contentSave.put(COLUMN_NAME, name);
        contentSave.put(COLUMN_DESTINATION, des);
        contentSave.put(COLUMN_DATE, date);
        contentSave.put(COLUMN_RISK, risk);
        contentSave.put(COLUMN_DESC, description);
        long result = db.insert(TRIP_TABLE,null, contentSave);
        if(result == -1) {
            return false;
        }
        return  true;
    }

    public boolean addExpense(String trId, String type, String amount, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentSave = new ContentValues();

        contentSave.put(COLUMN_TrID, trId);
        contentSave.put(COLUMN_TYPE, type);
        contentSave.put(COLUMN_AMOUNT, amount);
        contentSave.put(COLUMN_TIME, time);
        long result = db.insert(EXPENSE_TABLE,null, contentSave);
        if(result == -1) {
            return false;
        }
        return  true;
    }

    Cursor readData(){
        String query = "SELECT * FROM " + TRIP_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readExpenseData(String trId){
        String query = "SELECT * FROM " + EXPENSE_TABLE + " WHERE trId" + " = " + trId;;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean updateData(String row_id, String name, String des, String date,String risk, String description){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentSave = new ContentValues();

         contentSave.put(COLUMN_NAME, name);
         contentSave.put(COLUMN_DESTINATION, des);
         contentSave.put(COLUMN_DATE, date);
         contentSave.put(COLUMN_RISK, risk);
         contentSave.put(COLUMN_DESC, description);

         long result = db.update(TRIP_TABLE, contentSave, "id=?", new String[] {row_id});
            if(result == -1) {
                return false;
            }
            return  true;
    }

    public boolean deleteARow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TRIP_TABLE, "id=?", new String[]{row_id});
        if(result == -1) {
            return false;
        }
        return  true;
    }
    void deleteAllRow(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TRIP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TRIP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE);
        onCreate(db);
    }

}
