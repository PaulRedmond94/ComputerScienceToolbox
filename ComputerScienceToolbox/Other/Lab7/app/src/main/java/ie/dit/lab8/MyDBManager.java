package ie.dit.lab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBManager
{
    // These are the names of the columns the table will contain
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TASK_NAME = "task_name";
    public static final String KEY_TASK_DESC = "task_desc";
    public static final String KEY_COMPLETE_STATUS = "complete_status";
    private static final String DATABASE_NAME = "TaskList";
    private static final String DATABASE_TABLE = "Task";
    private static final int DATABASE_VERSION = 1;
    // This is the string containing the SQL database create statement
    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE +
                    " (" + KEY_ROWID + " integer primary key autoincrement, " +
                    KEY_TASK_NAME + " text not null, " +
                    KEY_TASK_DESC + " text not null, " +
                    KEY_COMPLETE_STATUS + " integer not null);";

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

    //---insert a person into the database---
    public long insertTask(String taskName, String taskDesc, int completeStatus) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TASK_NAME, taskName);
        initialValues.put(KEY_TASK_DESC, taskDesc);
        initialValues.put(KEY_COMPLETE_STATUS, completeStatus);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular person---
    public boolean deleteTask(long rowId) {
        // delete statement. If any rows deleted (i.e. >0), returns true
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the rows---
    public Cursor getAllTasks()
    {
        Cursor mCursor = null;

        try{
            mCursor = db.query(DATABASE_TABLE, new String[]
                            {
                                    KEY_ROWID,
                                    KEY_TASK_NAME,
                                    KEY_TASK_DESC,
                                    KEY_COMPLETE_STATUS
                            },
                    null, null, null, null, null);

        }catch (SQLException e){
            Log.e("error", "Failed to get all tasks: " + e);
        }

        return mCursor;
    }

    //---retrieves all the rows---
    public Cursor getAllCompletedTasks()
    {

        Cursor mCursor = null;

        try{

            mCursor = db.query(DATABASE_TABLE, new String[]
                            {
                                    KEY_ROWID,
                                    KEY_TASK_NAME,
                                    KEY_TASK_DESC,
                                    KEY_COMPLETE_STATUS
                            },
                    KEY_COMPLETE_STATUS + " = 1", null, null, null, null);

        }catch (SQLException e){
            Log.e("error", "Failed to get completed tasks: " + e);
        }

        return mCursor;
    }

    //---retrieves a particular row---
    public Cursor getTask(long rowId) {

        Cursor mCursor = null;

        try{
            mCursor =
                    db.query(true, DATABASE_TABLE, new String[]{
                                    KEY_ROWID,
                                    KEY_TASK_NAME,
                                    KEY_TASK_DESC,
                                    KEY_COMPLETE_STATUS
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
    }

    //---updates a person---
    public boolean updateTask(long rowId, String taskName, String taskDesc, int completeStatus)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_TASK_NAME, taskName);
        args.put(KEY_TASK_DESC, taskDesc);
        args.put(KEY_COMPLETE_STATUS, completeStatus);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}