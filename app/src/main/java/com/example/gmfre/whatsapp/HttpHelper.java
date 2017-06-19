package com.example.gmfre.whatsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HttpHelper {
    Context ctx;
    RequestQueue requestQueue = Volley.newRequestQueue(ctx);
    StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://www.google.com", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

        }
    }, new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError error){

        }
    });

    private static HttpHelper mInstance = new HttpHelper();
    private HttpHelper(){

    }
    private HttpHelper(Context ctx){
        this.ctx = ctx;
        this.requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if(null==requestQueue){
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }
    public static synchronized HttpHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new HttpHelper(context.getApplicationContext());
        }
        return mInstance;
    }
    public void getGoogleHTTP(final Response.Listener<String> listener) {
        String link = "http://www.google.com";
        stringRequest = new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new AlertDialog.Builder(ctx)
                        .setTitle("Connection Error!")
                        .setMessage(error.getMessage())
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();
            }
        });
        requestQueue.add(stringRequest);

    }

}
