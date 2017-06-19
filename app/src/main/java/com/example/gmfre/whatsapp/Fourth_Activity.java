package com.example.gmfre.whatsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;

public class Fourth_Activity extends AppCompatActivity {
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);
    //    Response.Listener googleListener = new Response.Listener<String>(){
  //          @Override
//            public void onResponse(String response){

          //  }
        //};
        //HttpHelper.getInstance(this).getGoogleHTTP(googleListener);
        nome = getIntent().getStringExtra("dado_nome");
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.carregando);
        final TextView textView = (TextView) findViewById(R.id.mensagem);
        MyAsyncTask myAsyncTask = new MyAsyncTask(textView,  progressBar,  this);
        myAsyncTask.execute(nome + ", diz:");
    }
}
