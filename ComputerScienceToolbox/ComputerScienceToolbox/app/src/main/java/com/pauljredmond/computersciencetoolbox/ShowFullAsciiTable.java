package com.pauljredmond.computersciencetoolbox;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.widget.Toast;

public class ShowFullAsciiTable extends ListActivity{

    //Declare varaibles for database
    String[] columns= {"ascii_decimal_val", "ascii_character"};
    int[] columnsLayout = {R.id.ascii_decimal_val, R.id.ascii_character_val};
    Cursor myCursor;
    MyDBManager db;
    SimpleCursorAdapter myCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_ascii_table);

        //assign db to this plage
        db = new MyDBManager(this);

        //try to open database
        try{
            db.open();
            myCursor = db.getAllItems();

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Error opening database! Please try later!", Toast.LENGTH_SHORT).show();

        }//try/catch

        myCursorAdapter = new SimpleCursorAdapter(this, R.layout.row, myCursor, columns, columnsLayout,0);

        setListAdapter(myCursorAdapter);

    }//end onCreate

}
