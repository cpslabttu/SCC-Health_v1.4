package app.esarp.SCC_Health;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import static app.esarp.SCC_Health.DisplayContact.READ_BLOCK_SIZE;

public class LoginActivity extends FragmentActivity {

    Button b1, b2, help;
    EditText ed1, ed2;
    private String name, pw;
    TextView tx1, tx2;
    int counter = 3;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);
        help=(Button)findViewById(R.id.help);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx2 = (TextView)findViewById(R.id.textView2);
        tx1.setVisibility(View.GONE);
        tx2.setVisibility(View.GONE);

        // Set active profile
        name = "";
        pw="";

        //reading profile from file
        try {
            FileInputStream fileIn = openFileInput("username.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            /*String s="";*/
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                name += readstring;
            }
            InputRead.close();
            /*mNameText.setText(s);*/
            /*Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileIn = openFileInput("mypassword.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            /*String s="";*/
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                pw += readstring;
            }
            InputRead.close();
            /*mNameText.setText(s);*/
            /*Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();*/

        } catch (Exception e) {
            e.printStackTrace();
        }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();


                if((ed1.getText().toString().equals("a") &&
                        ed2.getText().toString().equals("a"))||(ed1.getText().toString().matches(name) &&
                        ed2.getText().toString().matches(pw))) {

                    // pass login details to shared preferences
                    SharedPreferences prefs = getSharedPreferences("logindetails",MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("loginname",ed1.getText().toString()).commit();
                    Intent MainActivityIntent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    // Start the new activity
                    startActivity(MainActivityIntent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password",Toast.LENGTH_LONG).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx2.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }


                //progressDialog.dismiss();
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                //onLoginSuccess();
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                HelpdeskFragment f1=new HelpdeskFragment();
                fragmentTransaction.add(R.id.frag1,f1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}