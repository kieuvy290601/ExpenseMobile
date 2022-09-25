package com.example.demo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final  String DATABASE_NAME = "Expense_Management.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "ExpenseApp";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_DESC = "desc";
    private static final String COLUMN_RISK = "risk";

    public DatabaseHelper(@Nullable Context context)
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
                COLUMN_DESC + "TEXT, " +
                COLUMN_RISK + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}