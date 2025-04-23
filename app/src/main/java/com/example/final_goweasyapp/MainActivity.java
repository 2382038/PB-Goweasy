package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSearchBike, btnHistory;
    ImageView logoBike;
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        btnSearchBike = findViewById(R.id.btn_search_bike);
        btnHistory = findViewById(R.id.btn_history);
        logoBike = findViewById(R.id.logo_bike);
        titleText = findViewById(R.id.title_text);

        // Klik tombol Cari Sepeda
        btnSearchBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CariSepeda.class);
                startActivity(intent);
            }
        });

        // Klik tombol Histori Sewa
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoriSewa.class);
                startActivity(intent);
            }
        });
    }
}
