package app.esarp.SCC_Health;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import static app.esarp.SCC_Health.DisplayContact.READ_BLOCK_SIZE;

public class FluActivity extends Activity {

String currentDateTime;
    double ratingOfEOI=0.0;
    Float severityRating;
    String s;
    Intent mIntent;

    //temp db start
    /** EditText field to enter the pet's name */
    private TextView mNameText;


    /** EditText field to enter the pet's date & time */
    private TextView mDateTime;

    /** EditText field to enter the pet's temperature */
    private TextView mTemperature;

    /** EditText field to enter the pet's EOI rating */
    private TextView mEoi;


    //temp db finish

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // ((cBaseApplication)this.getApplicationContext()).myBlueComms.BluetoothConnectionListener();

       /* if(bt.isBluetoothEnabled())
            Toast.makeText(this,"b on",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"b off",Toast.LENGTH_SHORT).show();
*/
        setContentView(R.layout.activity_body_temp);

        /*startService(new Intent(this, BluetoothSPP.class));
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);*/

        // Find all relevant views that we will need to read user input from

        mNameText = (TextView) findViewById(R.id.display_name);
        mDateTime = (TextView) findViewById(R.id.datetime);
        mTemperature = (TextView) findViewById(R.id.display_bdt);
        mEoi = (TextView) findViewById(R.id.display_eoi);



// Find the View that shows the save button
        Button save = (Button) findViewById(R.id.save);

        // Set a click listener on that View
        save.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Save record to database
                insertTemp();
                // Exit activity
                finish();

            }
        });


   // color line gradient


        TextView gradient = (TextView) findViewById(R.id.active_gradient);


        int[] colors = {Color.parseColor("#008000"),Color.parseColor("#FFFF00"),Color.parseColor("#FFA500"),Color.parseColor("#ff0000"),Color.parseColor("#800000") };
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, colors);
        gradient.setBackground(gd);


// Set active profile
       s="";

        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            /*String s="";*/
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            /*mNameText.setText(s);*/
            /*Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        mNameText.setText(s);

// Check History

        /*// Find the View that shows the history button
        Button history = (Button) findViewById(R.id.history);

        // Set a click listener on that View
        history.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SleepApnea}
                Intent temperatureHistoryIntent = new Intent(FluActivity.this, Temp_HistoryActivity.class);

                // Start the new activity
                startActivity(temperatureHistoryIntent);
            }
        });*/

        final TextView btnOpenPopup = (TextView) findViewById(R.id.info);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.info_bodytemp, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

            }
        });

        final TextView btnOpenInstruction = (TextView) findViewById(R.id.inst);
        btnOpenInstruction.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.inst_bodytemp, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

            }
        });

//EOI color ranking
        LinearLayout mainDisplay=(LinearLayout) findViewById(R.id.maindisplay);
        ImageView arrow1=(ImageView) findViewById(R.id.arrow1);
        ImageView arrow2=(ImageView) findViewById(R.id.arrow2);
        ImageView arrow3=(ImageView) findViewById(R.id.arrow3);
        ImageView arrow4=(ImageView) findViewById(R.id.arrow4);
        ImageView arrow5=(ImageView) findViewById(R.id.arrow5);
        ImageView arrow6=(ImageView) findViewById(R.id.arrow6);
        ImageView arrow7=(ImageView) findViewById(R.id.arrow7);
        ImageView arrow8=(ImageView) findViewById(R.id.arrow8);
        ImageView arrow9=(ImageView) findViewById(R.id.arrow9);
        ImageView arrow10=(ImageView) findViewById(R.id.arrow10);
        ImageView arrow11=(ImageView) findViewById(R.id.arrow11);

        mainDisplay.setVisibility(View.INVISIBLE);
  /*  @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();*/

        //Set the views for main display

        String currentDateTime="";
        TextView Vdatetime = (TextView) findViewById(R.id.datetime);
        String mealId = " ";
        TextView Textv = (TextView) findViewById(R.id.display_bdt);
        String eoiRating="0";
        TextView Veoi = (TextView) findViewById(R.id.display_eoi);
        String prompt = " ";
        TextView Vprompt = (TextView) findViewById(R.id.display_prompt);

        //receive bluetooth intent

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null) {
            currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
            mealId = bundle.getString("STRING_I_NEED");
            mainDisplay.setVisibility(View.VISIBLE);
            arrow1.setVisibility(View.INVISIBLE);
            arrow2.setVisibility(View.INVISIBLE);
            arrow3.setVisibility(View.INVISIBLE);
            arrow4.setVisibility(View.INVISIBLE);
            arrow5.setVisibility(View.INVISIBLE);
            arrow6.setVisibility(View.INVISIBLE);
            arrow7.setVisibility(View.INVISIBLE);
            arrow8.setVisibility(View.INVISIBLE);
            arrow9.setVisibility(View.INVISIBLE);
            arrow10.setVisibility(View.INVISIBLE);
            arrow11.setVisibility(View.INVISIBLE);

        }

// temperature processing begin

        float f = 0;
        try {
            f = Float.valueOf(mealId);
            severityRating=100*((f-97)/10);
            eoiRating = Float.toString(severityRating);


                DecimalFormat df2 = new DecimalFormat("###.##");
                float eoi2decimal=Float.valueOf(df2.format(eoiRating));


            if (f <= 97.5) {
                mealId += "°F\n";
                ratingOfEOI = 0;
                //eoiRating = "0";
                prompt = "Normal Temperature";
                arrow1.setVisibility(View.VISIBLE);
            } else if (f <= 98.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.1;
               // eoiRating = "0.1";
                prompt = "Normal Temperature";
                arrow2.setVisibility(View.VISIBLE);
            } else if (f <= 99.5) {
                mealId += "°F\n";
                //eoiRating = "0.2";
                ratingOfEOI = 0.2;
                prompt = "Normal Temperature";
                arrow3.setVisibility(View.VISIBLE);
            } else if (f <= 100.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.3;
                //eoiRating = "0.3";
                prompt = "Normal Temperature";
                arrow4.setVisibility(View.VISIBLE);
            } else if (f <= 101.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.4;
                //eoiRating = "0.4";
                prompt = "Low Fever,\nconsider consulting your doctor";
                arrow5.setVisibility(View.VISIBLE);
            } else if (f <= 102.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.5;
                //eoiRating = "0.5";
                prompt = "Medium Fever,\nConsult your doctor";
                arrow6.setVisibility(View.VISIBLE);
            } else if (f <= 103.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.6;
                //eoiRating = "0.6";
                prompt = "High Fever,\nConsult your doctor";
                arrow7.setVisibility(View.VISIBLE);
            } else if (f <= 104.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.7;
                //eoiRating = "0.7";
                prompt = "High Fever,\nConsult your doctor";
                arrow8.setVisibility(View.VISIBLE);
            } else if (f <= 105.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.8;
                //eoiRating = "0.8";
                prompt = "Very High Fever,\nConsult your doctor immediately";
                arrow9.setVisibility(View.VISIBLE);
            } else if (f <= 106.5) {
                mealId += "°F\n";
                ratingOfEOI = 0.9;
                //eoiRating = "0.9";
                prompt = "Very High Fever,\nConsult your doctor immediately";
                arrow10.setVisibility(View.VISIBLE);
            } else if (f >= 106.5) {
                mealId += "°F\n";
                ratingOfEOI = 1;
                //eoiRating = "1";
                prompt = "Extremely High Fever,\nConsult your doctor immediately";
                arrow11.setVisibility(View.VISIBLE);
            }

        } catch (NumberFormatException e) {

            prompt = "Invalid Data";
            gradient.setVisibility(View.INVISIBLE);
        }

// end temperature processing




// set the strings for main display

        Vdatetime.setText(currentDateTime);
        Textv.append(mealId);
        Vprompt.append(prompt);
        Veoi.append(eoiRating+"%");

// Temperature History start





        /*mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

            setupSpinner();*/
    }

        /**
         * Setup the dropdown spinner that allows the user to select the gender of the pet.
         */
    /*private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = PetEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = PetEntry.GENDER_FEMALE;
                    } else {
                        mGender = PetEntry.GENDER_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = PetEntry.GENDER_UNKNOWN;
            }
        });
    }*/

    /**
     * Get user input from editor and save new  into database.
     */
    private void insertTemp() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameText.getText().toString().trim();
        String dateString = mDateTime.getText().toString().trim();
        String valueString =mTemperature.getText().toString().trim();
        String eoiString= mEoi.getText().toString();


        // Create database helper
        TempDbHelper mDbHelper = new TempDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(TempContract.TempEntry.COLUMN_PATIENT_NAME, nameString);
        values.put(TempContract.TempEntry.COLUMN_DATE_TIME, dateString);
        values.put(TempContract.TempEntry.COLUMN_TEMP_VALUE, valueString);
        values.put(TempContract.TempEntry.COLUMN_EOI_RATING, eoiString);

        // Insert a new row  in the database, returning the ID of that new row.
        long newRowId = db.insert(TempContract.TempEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save record to database
                insertTemp();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Share to SCC" menu option
            case R.id.action_share:
                // Go to cloud activity

                Intent shareIntent = new Intent(FluActivity.this, CloudActivity.class);
                //Bundle extras = new Bundle();
                shareIntent.putExtra("DT", "BT");
                shareIntent.putExtra("profile", s);
                shareIntent.putExtra("EOI", s);
                shareIntent.putExtra("Time", s);
                shareIntent.putExtra("Algorithm", s);
                startActivity(shareIntent);

                /*Intent intent = new Intent(
                        FluActivity.this,
                        ShowWebChart.class);

                   *//* intent.putExtra("NUM1", getNum(num1));
                    intent.putExtra("NUM2", getNum(num2));
                    intent.putExtra("NUM3", getNum(num3));
                    intent.putExtra("NUM4", getNum(num4));
                    intent.putExtra("NUM5", getNum(num5));
*//*

                intent.putExtra("NUM1", 40);
                intent.putExtra("NUM2", 50);
                intent.putExtra("NUM3", 70);
                intent.putExtra("NUM4", 35);
                intent.putExtra("NUM5", 50);

                startActivity(intent);

                finish();*/

                return true;
            case R.id.action_sms:
                String messageToSend = "EOI:"+ratingOfEOI;
                String number = "9015157371";

                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
                finish();
                return true;
            case R.id.action_history:

// Create a new intent to open the {@link Temperature History}
                Intent temperatureHistoryIntent = new Intent(FluActivity.this, Temp_HistoryActivity.class);

                // Start the new activity
                startActivity(temperatureHistoryIntent);
                finish();
                return true;


/*
                // Create a new intent to open the {@link Temperature History}
                Intent temperatureHistoryIntent = new Intent(FluActivity.this, Temp_HistoryActivity.class);

                // Start the new activity
                startActivity(temperatureHistoryIntent);
                finish();
                return true;*/


            // Respond to a click on the "Up" arrow button in the app bar
           /* case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;*/

            case R.id.action_algorithm:
                Intent algIntent = new Intent(FluActivity.this, FluAlgorithm.class);
                startActivity(algIntent);
        }
        return super.onOptionsItemSelected(item);
    }




// end of Temperature data save


    /*@Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //unregister our receiver
        this.unregisterReceiver(this.mReceiver);
    }*/
}


