package com.example.final_goweasyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    TextView textViewRegister; // <-- Tambahkan ini

    public static final String SHARED_PREF_NAME = "user_login";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_IS_LOGGED_IN = "is_logged_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Layout login kamu

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister); // Inisialisasi

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Username dan Password harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    // Contoh validasi manual - nanti ganti cek server
                    new Thread(() -> {
                        try {
                            URL url = new URL("http://10.8.14.82/android/goweasy/login.php");

                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);

                            String postData = "username=" + URLEncoder.encode(username, "UTF-8")
                                    + "&password=" + URLEncoder.encode(password, "UTF-8");

                            OutputStream os = conn.getOutputStream();
                            os.write(postData.getBytes("UTF-8"));
                            os.flush();
                            os.close();

                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            StringBuilder response = new StringBuilder();
                            String line;

                            while ((line = br.readLine()) != null) {
                                response.append(line);
                            }

                            br.close();

                            JSONObject jsonResponse = new JSONObject(response.toString());

                            String status = jsonResponse.getString("status");
                            if (status.equals("success")) {
                                String idUser = jsonResponse.getString("id");

                                runOnUiThread(() -> {
                                    Toast.makeText(Login.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtra("is_logged_in", true);
                                    intent.putExtra("id_user", idUser); // <--- kirim id_user ke MainActivity
                                    startActivity(intent);
                                    finish();
                                });
                            } else {
                                runOnUiThread(() -> {
                                    Toast.makeText(Login.this, "Login gagal: " + jsonResponse.optString("message", ""), Toast.LENGTH_SHORT).show();
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(() -> {
                                Toast.makeText(Login.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                            });
                        }
                    }).start();
                }
            }
        });

        // Ketika klik "Belum punya akun? Daftar di sini"
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class); // Pastikan Register.java sudah dibuat
                startActivity(intent);
            }
        });
    }
}
