package me.cosmeescobedo.explorer6;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
            txtTemp.setText("Anomalía media anual: " + value.text());

            respuesta = new ConexionServidor("https://climate.nasa.gov/vital-signs/carbon-dioxide/").execute().get();
            doc= Jsoup.parse(respuesta);
            primaryColumn = doc.getElementById("primary_column");
            value = primaryColumn.getElementsByClass("wysiwyg_content").first()
                    .getElementsByClass("latest_measurement").first().getElementsByClass("value").first();
            TextView txtC02 = findViewById(R.id.txtCO2);
            txtC02.setText("Medicion actual: " + value.text());

            Log.d("Respueta", "onCreate: "+value.text());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(this, Datos.imgUrl, Datos.years));
        ImageView imgC02 = findViewById(R.id.imgC02);
        Picasso.get().load("https://climate.nasa.gov/system/charts/15_co2_left_040518.gif").into(imgC02);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informacion Tierra");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }
}
