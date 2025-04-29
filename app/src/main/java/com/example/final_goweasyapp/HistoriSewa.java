package com.example.final_goweasyapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HistoriSewa extends AppCompatActivity {

    LinearLayout containerHistori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_sewa);

        String idUser = getIntent().getStringExtra("id_user");
        Toast.makeText(HistoriSewa.this, idUser, Toast.LENGTH_SHORT).show();

        containerHistori = findViewById(R.id.container_histori);
        tampilkanDataHistori(idUser);
    }

    private void tampilkanDataHistori(String idUser) {
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://10.8.14.82/android/goweasy/tampilHistoriPengguna.php?user_id=" + idUser);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder hasil = new StringBuilder();
                String baris;
                while ((baris = reader.readLine()) != null) {
                    hasil.append(baris);
                }

                JSONObject jsonObject = new JSONObject(hasil.toString());
                JSONArray array = jsonObject.getJSONArray("result");

                runOnUiThread(() -> {
                    if (array.length() == 0) {
                        Toast.makeText(HistoriSewa.this, "Tidak ada histori ditemukan.", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = 0; i < array.length(); i++) {
                            try {
                                JSONObject obj = array.getJSONObject(i);
                                String isi = "Nama: " + obj.getString("nama") + "\n"
                                        + "Sepeda: " + obj.getString("jenis_sepeda") + "\n"
                                        + "Lama Sewa: " + obj.getInt("lama_sewa") + " jam\n"
                                        + "Total: Rp " + obj.getInt("harga_total") + "\n"  // Ganti "total" -> "harga_total"
                                        + "Uang Bayar: Rp " + obj.getInt("uang_bayar") + "\n"
                                        + "Uang Kembali: Rp " + obj.getInt("uang_kembali") + "\n"
                                        + "Tanggal: " + obj.getString("tanggal_sewa") + "\n";  // Tambahkan tanggal jika perlu

                                TextView textView = new TextView(HistoriSewa.this);
                                textView.setText(isi);
                                textView.setTextSize(14);
                                textView.setPadding(20, 20, 20, 20);
                                textView.setBackgroundColor(0xFFE0E0E0);
                                textView.setTextColor(0xFF000000);

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                params.setMargins(0, 0, 0, 30);
                                textView.setLayoutParams(params);

                                containerHistori.addView(textView);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(HistoriSewa.this, "Error parsing data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
//                runOnUiThread(() ->
//                        Toast.makeText(HistoriSewa.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
//                );

                String errorMessage = e.getMessage(); // atau e.toString() untuk lebih lengkap
                runOnUiThread(() ->
                        Toast.makeText(HistoriSewa.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show()
                );
            }
        });
        thread.start();
    }
}
