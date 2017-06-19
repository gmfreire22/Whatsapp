package com.example.gmfre.whatsapp;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Saving_data {
    Context context;
    String fileName;
    ArrayList<Contactos> contactos;

    public Saving_data(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public void addTodosContatos(ArrayList<Contactos> contactos){
        this.contactos = contactos;
    }

    public boolean nao_tem_dados(){
        return contactos==null;
    }

    public void saveFile() throws IOException{
        Log.e("salvo","teste");
        FileOutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream o = new ObjectOutputStream(out);
        o.writeObject(contactos);
        out.flush();
        Log.e("salvo","ok");
        out.close();
    }
    public ArrayList<Contactos> getData() throws IOException, ClassNotFoundException {
        Log.e("Tem dados","teste");
        InputStream in = context.openFileInput(fileName);
        if (in == null){
            return null;
        }
        ObjectInputStream oin = new ObjectInputStream(in);
        Object obj = oin.readObject();
        if(obj==null)
            Log.e("Tem dados","nao");
        else{
            Log.e("Tem dados","sim");
        }
        return (ArrayList<Contactos>) obj;
    }
    public void adicionarContactos(Contactos contatoNovo){
        contactos.add(contatoNovo);
    }
}
