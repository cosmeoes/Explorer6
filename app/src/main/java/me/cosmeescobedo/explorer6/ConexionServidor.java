package me.cosmeescobedo.explorer6;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexionServidor extends AsyncTask<String, String, String> {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_DATA = "";
    private String servidor;

    public ConexionServidor(String servidor) {
        this.servidor = servidor;
    }


    @Override
    protected String doInBackground(String... strings) {
        String respuesta = "";

        URL url = null;
        try {
            url = new URL(servidor);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            int codigo = connection.getResponseCode();
            if(codigo == HttpURLConnection.HTTP_OK) {
                //Todo bien
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String linea;
                StringBuffer res = new StringBuffer();
                while ((linea = in.readLine()) != null){
                    res.append(linea);
                }
                in.close();
                Log.e("Respuesta", res.toString());
                respuesta = res.toString();

            } else {
                System.out.println(codigo);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }



}
