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

