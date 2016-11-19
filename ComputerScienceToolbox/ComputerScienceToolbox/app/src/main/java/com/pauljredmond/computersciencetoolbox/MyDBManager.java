package com.pauljredmond.computersciencetoolbox;

/**
 * Created by Paul on 11/19/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDBManager
{
    // These are the names of the columns the table will contain
    public static final String KEY_ROWID = "_id";
    public static final String KEY_ASCII_DECIMAL_VAL = "ascii_decimal_val";
    public static final String KEY_ASCII_CHARACTER = "ascii_character";
    private static final String DATABASE_NAME = "Ascii_Table";
    private static final String DATABASE_TABLE = "Ascii_Table";
    private static final int DATABASE_VERSION = 2;
    // This is the string containing the SQL database create statement
    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE +
                    " (" + KEY_ROWID + " integer primary key autoincrement, " +
                    KEY_ASCII_DECIMAL_VAL + " integer not null, " +
                    KEY_ASCII_CHARACTER + " text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // constructor for your class
    public MyDBManager(Context ctx) {
        // Context is a way that Android transfers info about Activities and apps.
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        // constructor for your dB helper class. This code is standard. You’ve set up the parameter values for the constructor already...database name,etc
        DatabaseHelper(Context context) {
            // This is the helper class that will create the dB if it doesn’t exist and upgrades it if
            // the structure has changed. It needs a constructor, an onCreate() method and an onUpgrade() method
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        // end of the help class


        @Override
        public void onCreate(SQLiteDatabase db) {
            // The “Database_create” string below needs to contain the SQL statement needed to create the dB
            db.execSQL(DATABASE_CREATE);
            //insert all the sql code for the ascii table
            //dec 0 - 4
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(1,'0','[NULL]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(2,'1','[Start of Heading]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(3,'2','[Start of Text]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(4,'3','[End of Text]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(5,'4','[End of Transmission]')");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    // If you want to change the structure of your database, e.g. Add a new column to a table,
    // the code will go head..
    //This method only triggers if the database version number has increased
    // from here on, include whatever methods will be used to access or change data in the database
    //---opens the database--- any activity that uses the dB will need to do this
    public MyDBManager open()
    {

        try{
            db = DBHelper.getWritableDatabase();

            //db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(45,'44',',')");


        }catch(SQLException e) {
            Log.e("error", "Failed to open database: " + e);
        }

        return this;
    }

    //---closes the database--- any activity that uses the dB will need to do this
    public void close()
    {
        DBHelper.close();
    }

    //---retrieves all the rows---
    public Cursor getAllItems(){
        Cursor mCursor = null;

        try{
            mCursor = db.query(DATABASE_TABLE, new String[]{
                    KEY_ROWID,
                    KEY_ASCII_DECIMAL_VAL,
                    KEY_ASCII_CHARACTER,
            },
            null, null, null, null, null);

            if(mCursor==null){
                Log.e("error","no results founds");

            }//end if

        }catch (SQLException e){
            Log.e("error", "Failed to get all entries: " + e);
        }

        return mCursor;
    }//end getAllItems

    public Cursor getItem(long rowId) {

        Cursor mCursor = null;

        try{
            mCursor =
                    db.query(true, DATABASE_TABLE, new String[]{
                                    KEY_ROWID,
                                    KEY_ASCII_DECIMAL_VAL,
                                    KEY_ASCII_CHARACTER
                            },
                            KEY_ROWID + "=" + rowId,
                            null,
                            null,
                            null,
                            null,
                            null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }

        }catch (SQLException e){
            Log.e("error", "Failed to get task: " + e);
        }

        return mCursor;
    }//end getItem

}