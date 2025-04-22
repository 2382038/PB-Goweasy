package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScanQR extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvScanTitle;
    private TextView tvPetunjukScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        btnBack = findViewById(R.id.btn_back);
        tvScanTitle = findViewById(R.id.tvScanTitle);
        tvPetunjukScan = findViewById(R.id.tvPetunjukScan);

        // Button Back
        btnBack.setOnClickListener(view -> {
            // Finish this activity and go back
            finish();
        });

        // Here you can implement QR scanning logic (can use libraries like ZXing or ML Kit)
    }
}
