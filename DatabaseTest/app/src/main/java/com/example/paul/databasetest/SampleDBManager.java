package com.example.paul.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class SampleDBManager {

    public static final String KEY_ROWID = "_id";
    public static final String TASKNAME = "taskname";
    public static final String TASKDESCRIPTION = "taskDescription";

    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table Tasks(_id integer primary key autoincrement, " +
                    "taskname text not null, " + "taskDescription text not null)";

    private static String DATABASE_TABLE="Tasks";
    private final Context context;
    private MyDatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private static String DBName ="MyTasks";

    public SampleDBManager(Context ctx) {
        this.context = ctx;
        DBHelper = new MyDatabaseHelper(context);
    }

    public static class MyDatabaseHelper extends SQLiteOpenHelper {

        MyDatabaseHelper(Context context) {
            super(context, DBName, null, DATABASE_VERSION);
        }


        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL(DATABASE_UPDATE);
        }
    }

    public SampleDBManager open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public long insertSomething(String TaskName, String TaskDesc)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put(TASKNAME, TaskName);
        initialValues.put(TASKDESCRIPTION, TaskDesc);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public Cursor selectSomething(long rowId) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{
                KEY_ROWID,
                TASKNAME,
                TASKDESCRIPTION
        }, KEY_ROWID + "="+ rowId, null, null, null, null, null);

        return mCursor;
    }

    public Cursor getSomeThing(long rowId) throws SQLException
    {
        Cursor mCursor= db.query(true, DATABASE_TABLE, new String[]{
                KEY_ROWID,
                TASKNAME,
                TASKDESCRIPTION
        }, KEY_ROWID + "="+ rowId, null, null, null, null, null);

        if(mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


}


