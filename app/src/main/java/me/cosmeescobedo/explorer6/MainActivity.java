package me.cosmeescobedo.explorer6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEarth(View view) {
        startActivity(new Intent(this, EarthInfo.class));
    }
    public void startGraph(View view) {
        startActivity(new Intent(this, Graph.class));
    }

    public void sendOK(View v) {
        try {
            Log.d("HTTP REQUEST", "http://deskode.com/toxic/usuarios.php?email=comeoes@gmail.com&password=123456&etapa=2&completada=true");
            String respuesta = new ConexionServidor("http://deskode.com/toxic/usuarios.php?email=comeoes@gmail.com&password=123456&etapa=6&completada=true").execute().get();
            if(respuesta.startsWith("DATOS INVALIDOS")) {
                Toast.makeText(this, "Error en login", Toast.LENGTH_LONG).show();
            } else {
                JSONArray jsonArray = new JSONArray(respuesta);
                Usuario.init(jsonArray.getJSONObject(2).getJSONArray("usuario").getJSONObject(0));
                EstadoLanzamento.init(jsonArray.getJSONObject(1).getJSONObject("estado_lanzamiento"));
                startActivity(new Intent(this, MainActivity.class));
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
