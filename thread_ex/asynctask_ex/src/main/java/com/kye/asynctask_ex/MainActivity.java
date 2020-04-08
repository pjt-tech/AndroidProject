package com.kye.asynctask_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BackgroundTask task;
    int count;
    TextView textView;
    boolean st = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.main_textView1);

    }


    public void startView(View view) {
        task = new BackgroundTask();
        task.execute();
    }

    public void pauseView(View view) {

        task.cancel(true);
        count = 10;
    }


    class BackgroundTask extends AsyncTask<Void,Integer,Integer>{
        @Override
        protected void onPreExecute() {
           count = 10;
        }
        @Override
        protected Integer doInBackground(Void... integers) {
            while (st){
                count--;
                if(count==0){
                    break;
                }else{
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
