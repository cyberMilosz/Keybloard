package com.withsecure.keybloard;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HTTPRequestHandler extends AsyncTask<String,Void,Void> {


    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("User-Agent", "Keybloard says: " + params[1]);
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write("hello".getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            urlConnection.getResponseCode();
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
