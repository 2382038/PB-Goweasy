package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Struk extends AppCompatActivity {

    private TextView txtNama, txtJenis, txtLama, txtTotal, txtBayar, txtKembali;
    private Button btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);  // pastikan nama file XML-nya activity_struk.xml

        // Bind views
        txtNama    = findViewById(R.id.nama_penyewa);
        txtJenis   = findViewById(R.id.jenis_mobil);
        txtLama    = findViewById(R.id.lama_sewa);
        txtTotal   = findViewById(R.id.total);
        txtBayar   = findViewById(R.id.uang_bayar);
        txtKembali = findViewById(R.id.uang_kembali);
        btnBackHome= findViewById(R.id.btn_back_home);

        // Ambil data dari Intent
        Intent intent = getIntent();
        txtNama.setText(intent.getStringExtra("nama"));
        txtJenis.setText(intent.getStringExtra("jenis"));
        txtLama.setText(intent.getStringExtra("lama"));
        txtTotal.setText(intent.getStringExtra("total"));
        txtBayar.setText(intent.getStringExtra("bayar"));
        txtKembali.setText(intent.getStringExtra("kembali"));

        // Tombol kembali ke MainActivity
        btnBackHome.setOnClickListener(v -> {
            startActivity(new Intent(Struk.this, MainActivity.class));
            finish();
        });
    }
}
