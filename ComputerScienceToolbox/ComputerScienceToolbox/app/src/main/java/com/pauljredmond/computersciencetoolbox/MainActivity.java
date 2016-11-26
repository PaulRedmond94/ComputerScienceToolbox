package com.pauljredmond.computersciencetoolbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        try{
            int id = item.getItemId();
            Intent intent = null;

            if(id == R.id.menu_number_base_calculator){
                intent = new Intent(MainActivity.this, NumberBaseCalculator.class);

            }//end else if base calculator
            else if(id == R.id.menu_ascii_table){
                intent = new Intent(MainActivity.this,AsciiTable.class);
                finish();

            }//end else if
            else if(id == R.id.menu_binary_text_convertor){
                intent = new Intent(MainActivity.this,BinaryTextConvertor.class);

            }//end else if
            else if(id == R.id.menu_bitwise_calculator){
                intent = new Intent(MainActivity.this,BitwiseCalculator.class);

            }//end else if

            else if(id == R.id.menu_donate){
                //Reference: The following code is from: http://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=ZK8Y4R47QKGCC"));
                //reference complete

            }//end else if

            else if(id == R.id.menu_github_link){
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.github.com/PaulRedmond94"));

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);


        }catch(NullPointerException exception){
            Toast.makeText(this,"You found an error! Lucky you!",Toast.LENGTH_SHORT).show();

        }//end try/catch to catch errors for null pointers for menu
        return true;
    }//end on navigation item selcted
}
