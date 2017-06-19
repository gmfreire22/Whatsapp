package com.example.gmfre.whatsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<String, Integer, String>{

    TextView textView;
    ProgressBar progressBar;
    Context context;
    public MyAsyncTask(TextView textView, ProgressBar progressBar, Context context){
        this.textView = textView;
        this.progressBar = progressBar;
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s){
        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        textView.setText(s);
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... s) {
        for(int i=0;i<100;i++){
            if(isCancelled()){
                break;
            }
        publishProgress(i);
        }
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return s[0];
    }
    protected void onProgressUpdate(Integer... progress){
        progressBar.setProgress(progress[0]);
    }
    @Override
    protected void onPreExecute(){
        textView.setVisibility(View.GONE);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(context,"carregando mensagem...", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }
}
