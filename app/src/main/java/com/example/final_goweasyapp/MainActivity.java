package com.example.final_goweasyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSearchBike;
    private Button btnHistory;
    private Button btnScanQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearchBike = findViewById(R.id.btn_search_bike);
        btnHistory = findViewById(R.id.btn_history);
        btnScanQR = findViewById(R.id.btn_scan_qr);

        // OnClickListeners
        btnSearchBike.setOnClickListener(view -> {
            // Logic for searching bike
        });

        btnHistory.setOnClickListener(view -> {
            // Logic for opening rental history
        });

        btnScanQR.setOnClickListener(view -> {
            // Open QR Scanner Activity
            Intent intent = new Intent(MainActivity.this, ScanQRActivity.class);
            startActivity(intent);
        });
    }
}
