package me.cosmeescobedo.explorer6;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.concurrent.ExecutionException;

public class EarthInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_info);


        try {
            String respuesta = new ConexionServidor("https://climate.nasa.gov/vital-signs/global-temperature/").execute().get();
            Document doc= Jsoup.parse(respuesta);
            Element primaryColumn = doc.getElementById("primary_column");
            Element value = primaryColumn.getElementsByClass("wysiwyg_content").first()
                    .getElementsByClass("latest_measurement").first().getElementsByClass("value").first();
            TextView txtTemp = findViewById(R.id.txtTemp);
            txtTemp.setText(value.text());

            respuesta = new ConexionServidor("https://climate.nasa.gov/vital-signs/carbon-dioxide/").execute().get();
            doc= Jsoup.parse(respuesta);
            primaryColumn = doc.getElementById("primary_column");
            value = primaryColumn.getElementsByClass("wysiwyg_content").first()
                    .getElementsByClass("latest_measurement").first().getElementsByClass("value").first();
            TextView txtC02 = findViewById(R.id.txtCO2);
            txtC02.setText(value.text());

            Log.d("Respueta", "onCreate: "+value.text());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informacion Tierra");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }
}
