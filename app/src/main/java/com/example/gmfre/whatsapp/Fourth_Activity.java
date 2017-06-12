package com.example.gmfre.whatsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Fourth_Activity extends AppCompatActivity {
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);
        nome = getIntent().getStringExtra("dado_nome");
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.carregando);
        final TextView textView = (TextView) findViewById(R.id.mensagem);
        MyAsyncTask myAsyncTask = new MyAsyncTask(textView,  progressBar,  this);
        myAsyncTask.execute(nome + ", diz:");
    }
}
