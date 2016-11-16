package ie.dit.lab8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListItemActivity extends AppCompatActivity {
    Intent mIntent;
    int complete;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        tv = (TextView)findViewById(R.id.completed);

        mIntent = getIntent();
        complete = mIntent.getIntExtra("complete_status", 0);

        if (complete == 1)
        {
            tv.setText("This task has been completed!");
        }
        else
        {
            tv.setText("This task is not complete!");
        }
    }
}
