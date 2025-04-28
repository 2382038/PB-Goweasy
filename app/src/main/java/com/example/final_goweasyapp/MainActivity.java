// MainActivity.java
package com.example.final_goweasyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    public static final String SHARED_PREF_NAME = "user_login";
    public static final String KEY_IS_LOGGED_IN = "is_logged_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cek apakah sudah login atau belum
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean(KEY_IS_LOGGED_IN, false);

        if (!isLoggedIn) {
            // Belum login, pindah ke LoginActivity
            Intent intent = new Intent(MainActivity.this, Login.class); // Sesuaikan nama activity
            startActivity(intent);
            finish();
            return;
        }

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
