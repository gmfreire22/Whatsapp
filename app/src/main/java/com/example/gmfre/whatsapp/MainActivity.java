package com.example.gmfre.whatsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ListView listaVisão;
    Bitmap imagem_padrao;
    Intent i;
    ArrayList<Contactos> contactos = new ArrayList<>();
    EditText search;
    Adapter1 adapter;
    int TextLength = 0;
    ArrayList<Contactos> array_sort;
    Button novo_contacto;
    String phone;
    String contact_display_name;
    Intent intent;
    boolean ler_os_contactos=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        else{
            ler_os_contactos = true;
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        setContentView(R.layout.activity_main);
        Log.e("=)", "Você está vendo o meu Log");
        imagem_padrao = BitmapFactory.decodeResource(getResources(), R.drawable.imagem);
        listaVisão = (ListView) findViewById(R.id.ListView);
        contactos.add(new Contactos("+5511943749019", imagem_padrao, "Ricardo"));
        contactos.add(new Contactos("+5511943749019", imagem_padrao, "Jaquim"));
        contactos.add(new Contactos("+5511944749019", imagem_padrao, "João"));
        contactos.add(new Contactos("+5511943749019", imagem_padrao, "Zé Manel"));
        contactos.add(new Contactos("+55119437492019", imagem_padrao, "Ana"));
        if(ler_os_contactos) {
            getAndroidContacts();
        }

        adapter = new Adapter1(this, contactos);
        listaVisão.setAdapter(adapter);
        array_sort = new ArrayList<>();
        Collections.sort(contactos, new Comparator<Contactos>() {
            @Override
            public int compare(Contactos lhs, Contactos rhs) {
                return lhs.getNome().compareToIgnoreCase(rhs.getNome());
            }});
        if(getIntent().hasExtra("byteArray")&&((getIntent().hasExtra("addContatoNome"))||(getIntent().hasExtra("addContatoNumero")))){
            Bitmap b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            contactos.add(new Contactos(getIntent().getStringExtra("addContatoNumero"), b, getIntent().getStringExtra("addContatoNome")));
            adapter = new Adapter1(this, contactos);
            listaVisão.setAdapter(adapter);
            Collections.sort(contactos, new Comparator<Contactos>() {
                @Override
                public int compare(Contactos lhs, Contactos rhs) {
                    return lhs.getNome().compareToIgnoreCase(rhs.getNome());
                }
            });
        }
        else if((getIntent().hasExtra("addContatoNome"))||(getIntent().hasExtra("addContatoNumero"))){
            contactos.add(new Contactos(getIntent().getStringExtra("addContatoNumero"), imagem_padrao, getIntent().getStringExtra("addContatoNome")));
            adapter = new Adapter1(this, contactos);
            listaVisão.setAdapter(adapter);
            Collections.sort(contactos, new Comparator<Contactos>() {
                @Override
                public int compare(Contactos lhs, Contactos rhs) {
                    return lhs.getNome().compareToIgnoreCase(rhs.getNome());
                }
            });
        }
        else{
            Log.e("Opa!", "Nada da second activity");
        }
        search = (EditText) findViewById(R.id.editText);
        novo_contacto = (Button) findViewById(R.id.newContact);
        intent = new Intent(MainActivity.this, Third_Activity.class);
        search.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextLength = search.getText().length();
                array_sort.clear();
                for (int i = 0; i < contactos.size(); i++) {
                    if (TextLength <= contactos.get(i).getNome().length()) {
                        contactos.get(i).getNome().toLowerCase().trim();
                        if (contactos.get(i).getNome().toLowerCase().trim().contains(search.getText().toString().toLowerCase().trim())) {
                            array_sort.add(contactos.get(i));
                        } else if (contactos.get(i).getNumero().toLowerCase().trim().contains(search.getText().toString().toLowerCase().trim())) {
                            array_sort.add(contactos.get(i));
                        }
                        adapter = new Adapter1(MainActivity.this, array_sort);
                        listaVisão.setAdapter(adapter);
                    }
                }
                if (array_sort.size() == 0) {
                    novo_contacto.setVisibility(View.VISIBLE);
                } else {
                    novo_contacto.setVisibility(View.GONE);
                }
            }
        });
        listaVisão.setClickable(true);
        listaVisão.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Log.e("posicao", String.valueOf(position));
                if ((array_sort.size() != 0)&&(array_sort.get(position).getNome()!= "")){
                    intent.putExtra("nome", array_sort.get(position).getNome());
                } else if (contactos.size() != 0) {
                    intent.putExtra("nome", contactos.get(position).getNome());
                }
                if (array_sort.size() != 0) {
                    intent.putExtra("numero", array_sort.get(position).getNumero());
                } else if (contactos.size() != 0) {
                    intent.putExtra("numero", contactos.get(position).getNumero());
                }
                    Bitmap bitmap = contactos.get(position).getImagem();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    String bytes_em_string = byteArray.toString();
                    intent.putExtra("bits", byteArray);
                    Log.e("Debugar", bytes_em_string);
                    startActivityForResult(intent, 2);
    }});

        novo_contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, Second_activity.class);
                startActivityForResult(i,1);
            }
        });

    }
    public void getAndroidContacts(){
        Cursor cursor_Android_contacts;
        cursor_Android_contacts = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
                while (cursor_Android_contacts.moveToNext()) {
                    contact_display_name = cursor_Android_contacts.getString(cursor_Android_contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    phone = cursor_Android_contacts.getString(cursor_Android_contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactos.add(new Contactos(phone, imagem_padrao, contact_display_name));
                    }
                    cursor_Android_contacts.close();
        adapter = new Adapter1(this, contactos);
        listaVisão.setAdapter(adapter);
                }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        novo_contacto.setVisibility(View.GONE);
        if(requestCode == 1){
            if (resultCode == RESULT_OK){

            }
            else{
                Log.e("error", "error");
            }
        }
    }
    }

