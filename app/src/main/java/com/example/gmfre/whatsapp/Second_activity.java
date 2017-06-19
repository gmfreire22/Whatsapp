package com.example.gmfre.whatsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.io.ByteArrayOutputStream;


public class Second_activity  extends AppCompatActivity {

    final int REQUEST_CODE_CONSTANT = 1;
    Button cancelar;
    Button guardar;
    Intent intent;
    EditText nome;
    EditText numero;
    ImageButton thumbnail;
    final int REQUEST_IMAGE_CAPTURE = 1;
    String nome_em_String;
    String numero_em_String;
    Bitmap imageBitmap;
    Saving_data saving_data;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            thumbnail.setImageBitmap(imageBitmap);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        nome = (EditText) findViewById(R.id.Nome);
        numero = (EditText) findViewById(R.id.Numero);
        intent = new Intent(Second_activity.this, MainActivity.class);
        thumbnail = (ImageButton) findViewById(R.id.Nova_Imagem);
        thumbnail.setImageResource(R.drawable.imagem);
        onClick();
        cancelar = (Button) findViewById(R.id.Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        guardar = (Button) findViewById(R.id.Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome_em_String = nome.getText().toString();
                numero_em_String = numero.getText().toString();
                intent.putExtra("addContatoNome", nome_em_String);
                intent.putExtra("addContatoNumero",numero_em_String);
                if(imageBitmap != null) {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                    intent.putExtra("byteArray", bs.toByteArray());

                }
                else{
                    Log.e("Opa!", "Nenhuma imagem");
                    Bitmap bitmap = ((BitmapDrawable)thumbnail.getDrawable()).getBitmap();
                }

                if(intent.resolveActivity(getPackageManager()) != null ) {
                    startActivityForResult(intent, REQUEST_CODE_CONSTANT);
                }
                finish();
            }
        });
}
             public void onClick() {
                 ImageButton new_imagem = (ImageButton) findViewById(R.id.Nova_Imagem);
                 new_imagem.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         dispatchTakePictureIntent();
                     }
                 });
             }
        }