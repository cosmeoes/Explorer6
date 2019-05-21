package me.cosmeescobedo.explorer6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
