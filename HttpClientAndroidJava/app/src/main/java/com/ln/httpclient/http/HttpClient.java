package com.ln.httpclient.http;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ln.httpclient.MainActivity;
import com.ln.httpclient.models.Manga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    /*
        A http client implem
     */
    private static String TAG= "API QUERY";
    private static String URL_API= "http://localhost:3000/api/mangas/";

    //GET
    public static void getMangas(Context context, final HttpListener listener){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API+"mangas",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Gson gson = new Gson();
                        Manga[] tempArray = gson.fromJson(response, Manga[].class);
                        listener.receiveMangas(new ArrayList<Manga>(Arrays.asList(tempArray)));
                        Log.d(TAG, "Ok je suis la");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,  "error GET" );
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    //POST
    public static void createManga(final Context context, final Manga manga){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_API + "mangas", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Enregistré", Toast.LENGTH_LONG);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage() );
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("title", manga.getTitle());
                params.put("author", manga.getAuthor());
                return params;
            }
        };
        queue.add(postRequest);
    }


    public static void updateManga(final Context context, final Manga manga){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_API + "mangas/"+manga.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Enregistré", Toast.LENGTH_LONG);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage() );
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("title", manga.getTitle());
                params.put("author", manga.getAuthor());
                return params;
            }
        };
        queue.add(putRequest);
    }

    public  static void deleteManga(final Context context, int idManga){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, URL_API + "mangas/"+idManga, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "supprimé!", Toast.LENGTH_LONG);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage() );
            }
        });
        queue.add(deleteRequest);
    }


}
