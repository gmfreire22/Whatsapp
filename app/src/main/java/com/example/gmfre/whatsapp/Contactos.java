package com.example.gmfre.whatsapp;

import android.graphics.Bitmap;

public class Contactos {
    private String Numero;
    private Bitmap Imagem;
    private String Nome;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Bitmap getImagem() {
        return Imagem;
    }

    public void setImagem(Bitmap imagem) {
        Imagem = imagem;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String  numero) {
        Numero = numero;
    }


    public Contactos(String numero, Bitmap imagem, String nome) {
        Numero = numero;
        Imagem = imagem;
        Nome = nome;
    }


}
