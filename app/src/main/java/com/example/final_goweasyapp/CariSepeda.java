package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CariSepeda extends AppCompatActivity {

    ImageView btnBack;

    String idUser;

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
        idUser = getIntent().getStringExtra("id_user");
        Intent intent = new Intent(CariSepeda.this, Penyewaan.class);
        intent.putExtra("id_user", idUser);
        startActivity(intent);
    }
}
