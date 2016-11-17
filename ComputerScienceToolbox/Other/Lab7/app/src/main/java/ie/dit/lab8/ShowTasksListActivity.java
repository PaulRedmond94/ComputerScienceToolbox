package ie.dit.lab8;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ShowTasksListActivity extends ListActivity {
    String[] columns = {"task_name", "task_desc", "complete_status"};
    int[] to = {R.id.task_name, R.id.task_desc, R.id.complete_status};
    Cursor mCursor;
    MyDBManager db;
    Intent listItemIntent;
    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks_list);

        listItemIntent = new Intent(this, ListItemActivity.class);
        db = new MyDBManager(this);

        try {
            db.open();
            mCursor = db.getAllTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAdapter = new SimpleCursorAdapter(this, R.layout.row, mCursor, columns, to, 0);

        setListAdapter(mAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor row;
        row = (Cursor)mAdapter.getItem(position);
        listItemIntent.putExtra("complete_status", row.getInt(3));

        startActivity(listItemIntent);
    }

}
