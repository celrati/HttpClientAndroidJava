package com.ln.httpclient.http;

import com.ln.httpclient.models.Manga;

import java.util.ArrayList;

public interface HttpListener {
    public  void receiveMangas(ArrayList<Manga> personnages);
}
