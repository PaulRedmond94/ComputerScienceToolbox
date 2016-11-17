package ie.dit.lab8;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyDBManager db;
    Cursor curs;
    EditText et1, et2, et3;
    Intent showListIntent;
    Button btnAdd, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.taskName);
        et2 = (EditText) findViewById(R.id.taskDesc);
        et3 = (EditText) findViewById(R.id.completeStatus);

        showListIntent = new Intent(this, ShowTasksListActivity.class);

        btnAdd = (Button) findViewById(R.id.addRow);
        btnShowList = (Button) findViewById(R.id.showTasks);

        db = new MyDBManager(this);
        try {
            db.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAdd.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String row1 = et1.getText().toString();
                        String row2 = et2.getText().toString();
                        String row3 = et3.getText().toString();
                        int row33 = Integer.parseInt(row3);
                        db.insertTask(row1, row2, row33);

                    }
                }
        );

        btnShowList.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(showListIntent);
                    }
                }
        );


    }
}
