package com.example.david.asynctaskexample;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private ProgressBar progressBar;
    private EditText editText;
    private Integer length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar= (ProgressBar) findViewById(R.id.progressBar2);
        editText= (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                length= Integer.valueOf(editText.getText().toString());
                button.setEnabled(false);
                editText.setEnabled(false);
                new MyAsyncTask().execute();
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(length);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText("Готово");
            progressBar.setVisibility(View.INVISIBLE);
            button.setEnabled(true);
            editText.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            textView.setText(values[0].toString());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < length; i++) {
                publishProgress(i);
                SystemClock.sleep(1000);
            }
            return null;
        }
    }
}
