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

            //insert ascii table elements into the database
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(0,0,'[NULL]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(1,1,'[Start Of Heading]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(2,2,'[Start Of Text]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(3,3,'[End Of Text]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(4,4,'[End Of Transmission]')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(5,5,'[Enquiry]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(6,6,'[Acknowledge]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(7,7,'[Bell]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(8,8,'[Backspace]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(9,9,'[Horizontal Tab]')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(10,10,'[New Line Feed]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(11,11,'[Vertical Tab]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(12,12,'[New Form Feed]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(13,13,'[Carriage Return]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(14,14,'[Shift Out]')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(15,15,'[Shift In]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(16,16,'[Data Link Escape]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(17,17,'[Device Control 1]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(18,18,'[Device Control 2]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(19,19,'[Device Control 3]')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(20,20,'[Device Control 4]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(21,21,'[Negative Acknowledge]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(22,22,'[Synchronous Idle]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(23,23,'[End of Trans. Block]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(24,24,'[Cancel]')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(25,25,'[End of Medium]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(26,26,'[Substitute]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(27,27,'[Escape]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(28,28,'[File Seperator]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(29,29,'[Group Seperator]')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(30,30,'[Record Seperator]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(31,31,'[Unit Seperator]')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(32,32,' ')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(33,33,'!')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(34,34,'\"')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(35,35,'#')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(36,36,'$')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(37,37,'%')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(38,38,'&')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(39,39,'''')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(40,40,'(')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(41,41,')')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(42,42,'*')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(43,43,'+')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(44,44,',')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(45,45,'-')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(46,46,'.')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(47,47,'/')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(48,48,'0')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(49,49,'1')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(50,50,'2')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(51,51,'3')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(52,52,'4')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(53,53,'5')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(54,54,'6')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(55,55,'7')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(56,56,'8')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(57,57,'9')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(58,58,':')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(59,59,';')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(60,60,'<')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(61,61,'=')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(62,62,'>')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(63,63,'?')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(64,64,'@')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(65,65,'A')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(66,66,'B')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(67,67,'C')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(68,68,'D')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(69,69,'E')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(70,70,'F')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(71,71,'G')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(72,72,'H')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(73,73,'I')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(74,74,'J')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(75,75,'K')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(76,76,'L')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(77,77,'M')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(78,78,'N')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(79,79,'O')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(80,80,'P')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(81,81,'Q')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(82,82,'R')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(83,83,'S')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(84,84,'T')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(85,85,'U')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(86,86,'V')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(87,87,'W')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(88,88,'X')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(89,89,'Y')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(90,90,'Z')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(91,91,'[')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(92,92,'\\')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(93,93,']')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(94,94,'^')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(95,95,'_')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(96,96,'`')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(97,97,'a')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(98,98,'b')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(99,99,'c')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(100,100,'d')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(101,101,'e')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(102,102,'f')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(103,103,'g')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(104,104,'h')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(105,105,'i')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(106,106,'j')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(107,107,'k')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(108,108,'l')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(109,109,'m')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(110,110,'n')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(111,111,'o')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(112,112,'p')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(113,113,'q')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(114,114,'r')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(115,115,'s')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(116,116,'t')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(117,117,'u')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(118,118,'v')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(119,119,'w')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(120,120,'x')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(121,121,'y')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(122,122,'z')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(123,123,'{')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(124,124,'|')");
            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(125,125,'}')");

            db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(126,126,'~')");

        }//end onCreate

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

            //db.execSQL("insert into " + DATABASE_NAME + "(" + KEY_ROWID + ","+ KEY_ASCII_DECIMAL_VAL + "," + KEY_ASCII_CHARACTER + ") values(91,'90','Z')");

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
                    KEY_ASCII_CHARACTER
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

    //getItem Function for int user input
    public Cursor getItem(int userInput) {

        Cursor mCursor = null;

        try{
            mCursor = db.query(true, DATABASE_TABLE, new String[]{
                                    KEY_ROWID,
                                    KEY_ASCII_DECIMAL_VAL,
                                    KEY_ASCII_CHARACTER
                            },
                            KEY_ROWID + "=" + userInput,
                            null,
                            null,
                            null,
                            null,
                            null);


            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            else{
                Log.d("NOTE: " , "No result found");

            }

        }catch (SQLException e){
            Log.e("error", "Failed to get task: " + e);
        }

        return mCursor;
    }//end getItem

    //getItemFunction for int user input
    public Cursor getItem(String userInput) {

        Cursor mCursor = null;

        try{
            //set it to only get case sensitive results:
            db.execSQL("PRAGMA case_sensitive_like=ON");

            mCursor =
                    db.query(true, DATABASE_TABLE, new String[]{
                                    KEY_ROWID,
                                    KEY_ASCII_DECIMAL_VAL,
                                    KEY_ASCII_CHARACTER
                            },
                            KEY_ASCII_CHARACTER + " LIKE '" + userInput+ "' ",
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

    //function to update item
    public boolean updateItem(long rowId,  String characterValue){
        ContentValues args = new ContentValues();
        args.put(KEY_ASCII_DECIMAL_VAL, rowId);
        args.put(KEY_ASCII_CHARACTER, characterValue);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId,null)>0;

    }//end updateItem

    //function to delete an item
    public boolean deleteItem(long rowId){
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId,null)>0;

    }

}
