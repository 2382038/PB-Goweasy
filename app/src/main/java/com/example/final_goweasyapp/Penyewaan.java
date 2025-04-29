package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Penyewaan extends AppCompatActivity {

    private ImageView btnBack;
    private EditText namaPenyewa, lamaSewa, uangBayar;
    private Spinner spinnerSepeda;
    private TextView hargaMobil;
    private Button btnOK, btnSewa, btnKeluar;

    // Data contoh: dua jenis sepeda dan tarif per jam
    private String[] daftarSepeda = { "Sepeda Listrik", "Sepeda Gunung" };
    private int[] tarifPerJam    = { 50000,             20000         };

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyewaan);

        // Bind semua view
        btnBack       = findViewById(R.id.btn_back);
        namaPenyewa   = findViewById(R.id.nama_penyewa);
        spinnerSepeda = findViewById(R.id.ad_listsepeda);
        lamaSewa      = findViewById(R.id.lama_sewa);
        hargaMobil    = findViewById(R.id.harga_mobil);
        btnOK         = findViewById(R.id.btn_ok);
        uangBayar     = findViewById(R.id.uangbayar);
        btnSewa       = findViewById(R.id.btn_sewa);
        btnKeluar     = findViewById(R.id.btn_keluar);

        // Setup spinner dengan daftarSepeda
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                daftarSepeda
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSepeda.setAdapter(adapter);

        // Kembali ke layar sebelumnya
        btnBack.setOnClickListener(v -> finish());

        // Hitung harga ketika OK ditekan
        btnOK.setOnClickListener(v -> hitungHarga());

        // Proses sewa ketika tombol SEWA ditekan
        btnSewa.setOnClickListener(v -> prosesSewa());

        // Kembali ke MainActivity ketika tombol KELUAR ditekan
        btnKeluar.setOnClickListener(v -> {
            Intent intent = new Intent(Penyewaan.this, MainActivity.class);
            intent.putExtra("is_logged_in", true);
            startActivity(intent);
            finish();
        });
    }

    // Menghitung harga berdasarkan lama sewa & jenis sepeda
    private void hitungHarga() {
        String sLama = lamaSewa.getText().toString().trim();
        if (sLama.isEmpty()) {
            Toast.makeText(this, "Masukkan lama sewa!", Toast.LENGTH_SHORT).show();
            return;
        }
        int jam = Integer.parseInt(sLama);
        int idx = spinnerSepeda.getSelectedItemPosition();
        int total = jam * tarifPerJam[idx];
        hargaMobil.setText(String.valueOf(total));
    }

    // Mengumpulkan data dan kirim ke StrukActivity
    private void prosesSewa() {
        String nama   = namaPenyewa.getText().toString().trim();
        String sLama  = lamaSewa.getText().toString().trim();
        String sHarga = hargaMobil.getText().toString().trim();
        String sBayar = uangBayar.getText().toString().trim();
        idUser = getIntent().getStringExtra("id_user");

        if (nama.isEmpty() || sLama.isEmpty() || sHarga.isEmpty() || sBayar.isEmpty()) {
            Toast.makeText(this, "Lengkapi semua data!", Toast.LENGTH_SHORT).show();
            return;
        }

        int total   = Integer.parseInt(sHarga);
        int bayar   = Integer.parseInt(sBayar);
        int kembali = bayar - total;
        if (kembali < 0) {
            Toast.makeText(this, "Uang bayar tidak cukup!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Penyewaan.this, Struk.class);
        intent.putExtra("id_user", idUser);
        intent.putExtra("nama", nama);
        intent.putExtra("jenis", daftarSepeda[spinnerSepeda.getSelectedItemPosition()]);
        intent.putExtra("lama", sLama);
        intent.putExtra("total", String.valueOf(total));
        intent.putExtra("bayar", String.valueOf(bayar));
        intent.putExtra("kembali", String.valueOf(kembali));
        startActivity(intent);
    }

    // Jika XML menggunakan onClick untuk method-method berikut,
    // panggil internal handler-nya:
    public void tmbl_OK(View view) {
        hitungHarga();
    }

    public void tombol_sewa2(View view) {
        prosesSewa();
    }

    public void hal_utama(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
