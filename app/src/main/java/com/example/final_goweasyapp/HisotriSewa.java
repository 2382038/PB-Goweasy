package com.example.final_goweasyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HisotriSewa extends AppCompatActivity {

    LinearLayout containerHistori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_sewa);

        containerHistori = findViewById(R.id.container_histori);
        tampilkanDataHistori();
    }

    private void tampilkanDataHistori() {
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://IP_ADDRESS_MU/get_histori.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder hasil = new StringBuilder();
                String baris;
                while ((baris = reader.readLine()) != null) {
                    hasil.append(baris);
                }

                JSONArray array = new JSONArray(hasil.toString());

                runOnUiThread(() -> {
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            JSONObject obj = array.getJSONObject(i);

                            String isi = "Nama: " + obj.getString("nama_penyewa") + "\n"
                                    + "Sepeda: " + obj.getString("jenis_sepeda") + "\n"
                                    + "Lama Sewa: " + obj.getString("lama_sewa") + " jam\n"
                                    + "Total: Rp " + obj.getString("total") + "\n"
                                    + "Uang Bayar: Rp " + obj.getString("uang_bayar") + "\n"
                                    + "Uang Kembali: Rp " + obj.getString("uang_kembali") + "\n";

                            TextView textView = new TextView(HisotriSewa.this);
                            textView.setText(isi);
                            textView.setTextSize(14);
                            textView.setPadding(20, 20, 20, 20);
                            textView.setBackgroundColor(0xFFE0E0E0); // abu terang
                            textView.setTextColor(0xFF000000); // hitam

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            params.setMargins(0, 0, 0, 30);
                            textView.setLayoutParams(params);

                            containerHistori.addView(textView);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(HisotriSewa.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                );
            }
        });
        thread.start();
    }
}
