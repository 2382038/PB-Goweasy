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
                    if (username.equals("admin") && password.equals("admin")) {
                        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(KEY_USERNAME, username);
                        editor.putBoolean(KEY_IS_LOGGED_IN, true);
                        editor.apply();

                        Toast.makeText(Login.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                    }
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
