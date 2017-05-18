package app.esarp.SCC_Health;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//git@gitlab.com:mjrahman/smartandconnected.git

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        /*ActionBar myActionBar = getSupportActionBar();
        myActionBar.show();*/

        /*Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);*/
        // receive user name and display in a text view
       /* Intent userIntent = getIntent();
        String userName=userIntent.getStringExtra("user");*/
        TextView displayUsername = (TextView) findViewById(R.id.uName);



        //Save the String value
        SharedPreferences prefs = getSharedPreferences("logindetails",MODE_PRIVATE);
        String Uname =  prefs.getString("loginname","Default");
        displayUsername.setText("\t\t:) "+Uname);


        // Find the View that shows the explorer
        TextView explorer = (TextView) findViewById(R.id.explore);

        // Set a click listener on that View
        explorer.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SleepApnea}
                Intent homeIntent = new Intent(WelcomeActivity.this, HomeActivity.class);

                // Start the new activity
                startActivity(homeIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
}