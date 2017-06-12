package com.example.gmfre.whatsapp;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class Adapter1 extends BaseAdapter{
    List<Contactos> contactos;
    LayoutInflater layoutInflater;
    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.textView);
            holder.numero = (TextView) convertView.findViewById(R.id.numero);
            holder.image =(ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Contactos contacto = contactos.get(position);
        holder.nome.setText(contacto.getNome());
        holder.numero.setText(contacto.getNumero());
        holder.image.setImageBitmap(contacto.getImagem());
        return convertView;
    }

    public Adapter1(Activity Context, List<Contactos> contactos) {
        this.contactos = contactos;
        layoutInflater=(LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class ViewHolder{
        TextView nome;
        ImageView image;
        TextView numero;
    }
}
