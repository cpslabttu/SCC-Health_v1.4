package app.esarp.SCC_Health;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FluAlgorithm extends AppCompatActivity {
    private long enqueue;
    private DownloadManager dm;
    private TextView tv1;
    private String s;
    Activity context;
    private TextView txtview;
    Button b1;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flu_algorithm);

        // show action bar
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.show();

        tv1=(TextView) findViewById(R.id.textDownload);
        txtview=(TextView)findViewById(R.id.textview);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(
                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    Query query = new Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {

                            /*ImageView view = (ImageView) findViewById(R.id.imageView1);
                            String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                            view.setImageURI(Uri.parse(uriString));*/
                        }
                    }
                }
            }
        };

        registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        /*BackTask bt=new BackTask();
        bt.execute("http://www.textfiles.com/news/bucks.txt");*/
    }

    public void onStart(){
        super.onStart();

       BackTask bt=new BackTask();
        bt.execute("http://www.textfiles.com/ufo/3-19disc.txt");


    }

    //background process to download the file from internet
    private class BackTask extends AsyncTask<String,Integer,Void> {
        String text="";
        protected void onPreExecute(){
            super.onPreExecute();
            //display progress dialog
           /* pd = new ProgressDialog(context);
            pd.setTitle("Reading the text file");
            pd.setMessage("Please wait.");
            pd.setCancelable(true);
            pd.setIndeterminate(false);
            pd.show();*/

        }



        protected Void doInBackground(String...params){
            URL url;
            try {
                //create url object to point to the file location on internet
                //url = new URL(params[0]);
                url = new URL(params[0]);
                //make a request to server
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                //get InputStream instance
                InputStream is=con.getInputStream();
                //create BufferedReader object
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line;
                //read content of the file line by line
                while((line=br.readLine())!=null){
                    text+=line;

                }

                br.close();

            }catch (Exception e) {
                e.printStackTrace();
                //close dialog if error occurs
                if(pd!=null) pd.dismiss();
            }

            return null;

        }


        protected void onPostExecute(Void result){
            //close dialog
            if(pd!=null)
                pd.dismiss();
            //display read text in TextVeiw
            txtview.setText(text);
            txtview.append("end");

        }


    }

    public void onClick(View view) {
       dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse("http://thewalter.net/stef/software/rtfx/sample.rtf"));

        enqueue = dm.enqueue(request);

    }

    public void showDownload(View view) {
        Intent i = new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(i);
    }
}