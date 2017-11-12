package com.example.emre.hcoder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AlinanMesaj extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alinan_mesaj);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.GONDERILEN_MESAJ);

        TextView textView = (TextView) findViewById(R.id.alinanMesaj);
        textView.setText(message);
    }
}
