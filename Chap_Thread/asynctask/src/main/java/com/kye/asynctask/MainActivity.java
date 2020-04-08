package com.kye.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    int value;
    BackgroundTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        Button btn_start = findViewById(R.id.button3);
        Button btn_stop = findViewById(R.id.button4);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new BackgroundTask();
                task.execute();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=0;
                task.cancel(true);
            }
        });

    }

    class BackgroundTask extends AsyncTask<Void,Integer,Integer>{

        @Override
        protected void onPreExecute() {
            value = 0;
            progressBar.setProgress(0);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            while (isCancelled()==false){
                value++;
                if(value>=100){
                    break;
                }else{
                    publishProgress(value);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            textView.setText("Current Value : "+values[0]+"%");

        }


        @Override
        protected void onPostExecute(Integer integer) {
            progressBar.setProgress(0);
            textView.setText("Finished : " + integer+"%");
        }


        @Override
        protected void onCancelled(Integer integer) {
            progressBar.setProgress(0);
            textView.setText("Canceled");

        }

    }
}
