package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CariSepeda extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_sepeda);

        // Bind Back Button
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
    }

    // Dipanggil saat tombol "Sewa Sekarang" diklik
    public void hal_sewa(View view) {
        Intent intent = new Intent(CariSepeda.this, Penyewaan.class);
        startActivity(intent);
    }
}
