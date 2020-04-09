package com.kye.asynctask_timer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    BackgroundTask task;
    int count;
    TextView textView;
    boolean st = true;
    boolean cnacel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.main_textView1);

    }


    public void startView(View view) {
        st = true;
        task = new BackgroundTask();
        task.execute();
    }

    public void pauseView(View view) {

        task.cancel(true);
        count = 10;
        st = false;
    }


    class BackgroundTask extends AsyncTask<Void, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            count = 10;
        }

        @Override
        protected Integer doInBackground(Void... integers) {
            while (st==true) {

                if (count == 0) {
                    break;
                } else {
                    count--;
                    publishProgress(count);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return count;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            textView.setText("Finished");

        }

        @Override
        protected void onCancelled() {
            textView.setText("Cancelled");

        }

        @Override
        protected void onProgressUpdate(Integer... integers) {
            textView.setText(String.valueOf(count));
        }

    }
}