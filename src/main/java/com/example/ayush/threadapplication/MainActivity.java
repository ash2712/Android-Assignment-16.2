 package com.example.ayush.threadapplication;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 public class MainActivity extends AppCompatActivity {
     //declaring buttons, Textview and EditText
     Button add, delete;
     TextView textview;
     EditText editText;
     String filedata;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         //associating components with their ids
         add = (Button) findViewById(R.id.button);
         editText = (EditText) findViewById(R.id.editText);
         delete = (Button) findViewById(R.id.button2);
         textview = (TextView) findViewById(R.id.textView);
         filedata = editText.getText().toString();
         //on click listener for add button
         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //executing the asyncTask
                 new addFileData().execute();
             }
         });
         //on click listener for delete button
         delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 File myFile = new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "test.txt");
                 //deletes file
                 boolean deleted = myFile.delete();
                 if (deleted) {
                     Toast.makeText(MainActivity.this, "File Deleted", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(MainActivity.this, "Unable to delete file", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }

     private class addFileData extends AsyncTask<String, String, String> {


         @Override
         protected void onPreExecute() {
             Toast.makeText(MainActivity.this, "Data adding to file", Toast.LENGTH_SHORT).show();
             super.onPreExecute();
         }

         @Override
         protected String doInBackground(String... strings) {
             File txtFile = new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "test.txt");
             try {   //output stream for writing data to file
                 FileOutputStream fout = new FileOutputStream(txtFile);
                 OutputStreamWriter output = new OutputStreamWriter(fout);
                 //adding data to file
                 output.append(filedata);
                 output.close();
                 fout.close();
                 Toast.makeText(MainActivity.this, "Data added Successfully!", Toast.LENGTH_SHORT).show();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return null;
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             try {
                 File myFile = new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "test.txt");
                 StringBuilder text = new StringBuilder();
                 //obtaining input bytes from file
                 FileInputStream fIn = new FileInputStream(myFile);
                 BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                 String line;
                 //reading text from file
                 while ((line = myReader.readLine()) != null) {
                     text.append(line);
                     text.append('\n');
                 }
                 //setting  text to textView
                 textview.setText(text.toString());
                 myReader.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

     }

 }*/

 public class MainActivity extends AppCompatActivity {
     ProgressBar pb1;
     ProgressBar pb2;
     ProgressBar pb3;
     ProgressBar pb4;
     int whichdownloads = 0;
     //determines whether bottom 2 or top 2 progress bars are to be used.

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         Button bt1 = (Button) findViewById(R.id.button);
         pb1 = (ProgressBar) findViewById(R.id.progressBar1);
         pb2 = (ProgressBar) findViewById(R.id.progressBar2);
         pb3 = (ProgressBar) findViewById(R.id.progressBar3);
         pb4 =  (ProgressBar) findViewById(R.id.progressBar4);
         bt1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (whichdownloads == 0){
                     download download1 = new download(pb1,pb2);
                     download1.execute();
                     //starts executing progress for 2 progress bars simultaneously
                     whichdownloads = 1;
                     Toast.makeText(MainActivity.this, "Press button again to activate next progress bars", Toast.LENGTH_SHORT).show();
                 }
                 else if (whichdownloads == 1){
                     download download2= new download(pb3,pb4 );
                     download2.execute();
                     whichdownloads = 0;
                 }
             }
         });
         Log.e("Status", String.valueOf(pb1.getProgress()));

     }

     class download extends AsyncTask<Void,Integer,Void> {
         ProgressBar progressBar;
         ProgressBar progressBar2;

         public download(ProgressBar target, ProgressBar secondTarget) {
             progressBar = target;
             progressBar2 = secondTarget;
         }

         @Override
         protected Void doInBackground(Void... params) {
             //setting delay and publishing progress on progressbar
             for (int i = 0; i < 11; i++) {
                 sleep();
                 publishProgress(i * 10);
             }
             return null;
         }

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
         }

         @Override
         protected void onProgressUpdate(Integer... values) {
             //updating progress of the progress bar
             progressBar.setProgress(values[0]);
             progressBar2.setProgress(values[0]);
             super.onProgressUpdate(values);
         }

         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);
             //displaying a toast on completion of the task
             Toast.makeText(MainActivity.this, "Download completed!", Toast.LENGTH_SHORT).show();
         }

         public void sleep() {
             try {
                 Thread.sleep(500);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }

 }

