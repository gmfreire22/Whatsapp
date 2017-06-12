package com.example.gmfre.whatsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Third_Activity extends AppCompatActivity{
    Intent callIntent;
    String numeroLigacao;
    boolean ligar = false;
    Intent intent;
    Bitmap b;
    TextView nome;
    TextView numero;
    Intent intent_mensagem;
    Button mensagem;

    public void Ligar(){
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+numeroLigacao));
        startActivity(callIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        if (ContextCompat.checkSelfPermission(Third_Activity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Third_Activity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
                ligar=true;
            }
        if (getIntent().hasExtra("nome")) {
            nome = (TextView) findViewById(R.id.nome);
            nome.setText(getIntent().getStringExtra("nome"));
        }
        else{
            Log.e("Opa!", "Sem nome");
        }
        if (getIntent().hasExtra("numero")) {
            numero = (TextView) findViewById(R.id.numero);
            numeroLigacao = getIntent().getStringExtra("numero");
            numero.setText(numeroLigacao);
        }
        else{
            Log.e("Opa!", "Sem numero");
        }
        b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("bits"),0,getIntent().getByteArrayExtra("bits").length);
        ImageView imageView = (ImageView) findViewById(R.id.imagem);
        imageView.setImageBitmap(b);
        ImageButton button = (ImageButton) findViewById(R.id.Ligar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ligar==true){
                    Ligar();
                }
                else{
                    Log.e("opa!", "ligar = false");
                }
            }
        });
        ImageButton voltar_botao = (ImageButton) findViewById(R.id.voltar);
        voltar_botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }});
        mensagem = (Button) findViewById(R.id.ir_pra);
        mensagem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Mensagear();
            }
        });
    }
    public void voltar(){
        intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent,1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
                if (resultCode == RESULT_OK){

                }
                }
        }
    public void Mensagear(){
        intent_mensagem = new Intent(Third_Activity.this, Fourth_Activity.class);
        String nome1 = nome.getText().toString();
        intent_mensagem.putExtra("dado_nome", nome1);
        Log.e("Info:", "é assim:" + nome1);
        intent_mensagem.putExtra("dado_numero", numero.getText());
        startActivityForResult(intent_mensagem, 1);


    }
}
