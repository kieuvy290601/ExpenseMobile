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

    private static final String TABLE_NAME = "ExpenseApp";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESTINATION = "des";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_DESC = "description";
    public static final String COLUMN_RISK = "risk";

     DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESTINATION + " TEXT, " +
                COLUMN_DATE + " TEXT, "  +
                COLUMN_RISK + " TEXT, " +
                COLUMN_DESC + " TEXT)";
        db.execSQL(query);
    }



    public boolean addTrip(String name, String des, String date, String risk, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentSave = new ContentValues();

        contentSave.put(COLUMN_NAME, name);
        contentSave.put(COLUMN_DESTINATION, des);
        contentSave.put(COLUMN_DATE, date);
        contentSave.put(COLUMN_RISK, risk);
        contentSave.put(COLUMN_DESC, description);
        long result = db.insert(TABLE_NAME,null, contentSave);
        if(result == -1) {
            return false;
        }
        return  true;
    }
    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

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

         long result = db.update(TABLE_NAME, contentSave, "id=?", new String[] {row_id});
            if(result == -1) {
                return false;
            }
            return  true;
    }

    public boolean deleteARow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1) {
            return false;
        }
        return  true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
