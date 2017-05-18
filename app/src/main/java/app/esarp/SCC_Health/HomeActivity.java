package app.esarp.SCC_Health;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_home);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.show();


        // Find the View that shows the  intro information
        TextView intro = (TextView) findViewById(R.id.espeech);
        intro.setVisibility(View.INVISIBLE);


        // Find the View that shows the  project information
        TextView about = (TextView) findViewById(R.id.about);
        String aboutString="About \n\t- Know the project";
        SpannableString ss1=  new SpannableString(aboutString);
        ss1.setSpan(new RelativeSizeSpan(2.0f), 0,5, 0); // set size
        about.setText(ss1);
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
        String diagnosticString="Diagnostic \n\t- Diagnose flu, arrythmia, sleep apnea & COPD";
        SpannableString ss3=  new SpannableString(diagnosticString);
        ss3.setSpan(new RelativeSizeSpan(2.0f), 0,10, 0); // set size
        lab.setText(ss3);
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


// Find the View that shows the setting category
        TextView setting = (TextView) findViewById(R.id.setting);
        String settingString="Settings \n\t- Create user, manage profiles & setup network";
        SpannableString ss2=  new SpannableString(settingString);
        ss2.setSpan(new RelativeSizeSpan(2.0f), 0,8, 0); // set size
        setting.setText(ss2);
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
