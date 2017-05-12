package app.esarp.SCC_Health;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_home);

        // Find the View that shows the  project information
        TextView about = (TextView) findViewById(R.id.about);

        // Set a click listener on that View

        about.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the lab category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link LabActivity}
                Intent aboutIntent = new Intent(app.esarp.SCC_Health.HomeActivity.this, About.class);

                // Start the new activity
                startActivity(aboutIntent);

            }
        });

        // Find the View that shows the lab category
        TextView lab = (TextView) findViewById(R.id.lab);

        // Set a click listener on that View

        lab.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the lab category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link LabActivity}
                Intent labIntent = new Intent(app.esarp.SCC_Health.HomeActivity.this, LabActivity.class);

                // Start the new activity
                startActivity(labIntent);

            }
        });


// Find the View that shows the profile category
        TextView setting = (TextView) findViewById(R.id.setting);

        // Set a click listener on that View
        setting.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the profile category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ProfileActivity}
                Intent settingIntent = new Intent(app.esarp.SCC_Health.HomeActivity.this, DB_login.class);

                // Start the new activity
                startActivity(settingIntent);

            }
        });

    }
}
