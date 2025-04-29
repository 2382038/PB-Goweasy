package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
        String idUser = getIntent().getStringExtra("id_user");
        Toast.makeText(Struk.this, idUser, Toast.LENGTH_LONG).show();
        txtNama.setText(intent.getStringExtra("nama"));
        txtJenis.setText(intent.getStringExtra("jenis"));
        txtLama.setText(intent.getStringExtra("lama"));
        txtTotal.setText(intent.getStringExtra("total"));
        txtBayar.setText(intent.getStringExtra("bayar"));
        txtKembali.setText(intent.getStringExtra("kembali"));

        tambahHistori(idUser);

        // Tombol kembali ke MainActivity
        btnBackHome.setOnClickListener(v -> {
            Intent intentBackHome = new Intent(Struk.this, MainActivity.class);
            intentBackHome.putExtra("is_logged_in", true);
            intentBackHome.putExtra("id_user", idUser);
            startActivity(intentBackHome);
            finish();
        });
    }

    private void tambahHistori(String idUser) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.8.14.82/android/goweasy/tambahHistori.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // Siapkan data untuk dikirim
                String nama = txtNama.getText().toString();
                String jenis = txtJenis.getText().toString();
                String lama = txtLama.getText().toString().replace(" jam", "");
                String total = txtTotal.getText().toString().replace("Rp ", "");
                String bayar = txtBayar.getText().toString().replace("Rp ", "");
                String kembali = txtKembali.getText().toString().replace("Rp ", "");
                String tanggalSewa = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

                String data = "user_id=" + URLEncoder.encode(idUser, "UTF-8") +
                        "&nama_penyewa=" + URLEncoder.encode(nama, "UTF-8") +
                        "&jenis_sepeda=" + URLEncoder.encode(jenis, "UTF-8") +
                        "&lama_sewa=" + URLEncoder.encode(lama, "UTF-8") +
                        "&harga_total=" + URLEncoder.encode(total, "UTF-8") +
                        "&uang_bayar=" + URLEncoder.encode(bayar, "UTF-8") +
                        "&uang_kembali=" + URLEncoder.encode(kembali, "UTF-8") +
                        "&tanggal_sewa=" + URLEncoder.encode(tanggalSewa, "UTF-8");

                // Kirim data
                OutputStream os = conn.getOutputStream();
                os.write(data.getBytes());
                os.flush();
                os.close();

                // Periksa response
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    runOnUiThread(() -> {
                        // Format data yang dikirim untuk Toast
                        String dataSent = "Data yang disimpan:\n" +
                                "ID: " + idUser + "\n" +
                                "Nama: " + nama + "\n" +
                                "Jenis: " + jenis + "\n" +
                                "Lama: " + lama + " jam\n" +
                                "Total: Rp" + total + "\n" +
                                "Bayar: Rp" + bayar + "\n" +
                                "Kembali: Rp" + kembali;

                        // Gabungkan response server dengan data yang dikirim
                        String toastMessage = "Server: " + response.toString() + "\n\n" + dataSent;

                        // Buat Toast custom yang lebih besar
                        Toast toast = Toast.makeText(Struk.this, toastMessage, Toast.LENGTH_LONG);
                        toast.show();
                    });
                } else {
                    runOnUiThread(() ->
                            Toast.makeText(Struk.this, "Error: HTTP " + responseCode, Toast.LENGTH_LONG).show()
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    String errorMsg = "Error: " + e.getMessage() + "\n\nData yang gagal dikirim:\n" +
                            "Nama: " + txtNama.getText().toString() + "\n" +
                            "Jenis: " + txtJenis.getText().toString();
                    Toast.makeText(Struk.this, errorMsg, Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }
}
