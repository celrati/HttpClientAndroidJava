package net.merryservices.androidtestapi.services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import net.merryservices.androidtestapi.MainActivity;
import net.merryservices.androidtestapi.R;
import net.merryservices.androidtestapi.model.Personnage;
import net.merryservices.androidtestapi.services.IListenerAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerApi {
    private static String TAG= "API REQUEST";
    private static String URL_API= "http://172.16.32.20/androidAPI/";

    public static void getPersonnages(Context context, final IListenerAPI listenerAPI){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API+"personnages",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Gson gson = new Gson();
                        Personnage[] tempArray = gson.fromJson(response, Personnage[].class);
                        listenerAPI.receivePersonnages(new ArrayList<Personnage>(Arrays.asList(tempArray)));
                        Log.d(TAG, "Ok je suis la");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,  "error" );
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static void loadImage(Context context, String url, final ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest(url,
            new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }, 0, 0, null,
            new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    imageView.setImageResource(R.drawable.ic_launcher_foreground);
                }
            });
        queue.add(request);
    }

    public static void createPersonnage(final Context context, final Personnage personnage){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_API + "personnages", new Response.Listener<String>() {
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
                params.put("name", personnage.getName());
                params.put("image", personnage.getImage());
                params.put("description", personnage.getDescription());
                return params;
            }
        };
        queue.add(postRequest);
    }

    public static void updatePersonnage(final Context context, final Personnage personnage){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_API + "personnages/"+personnage.getId(), new Response.Listener<String>() {
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
                params.put("name", personnage.getName());
                params.put("image", personnage.getImage());
                params.put("description", personnage.getDescription());
                return params;
            }
        };
        queue.add(putRequest);
    }

    public  static void deletePersonnage(final Context context, int idPersonnage){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, URL_API + "personnages/"+idPersonnage, new Response.Listener<String>() {
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
