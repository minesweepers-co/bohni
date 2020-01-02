package com.minesweepers.bohni;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = findViewById(R.id.statusTextView);

        NetworkApi.getInstance().healthCheck(new NetworkApi.NetworkApiListener() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                       statusTextView.setText("firebase available");
                       statusTextView.setTextColor(Color.parseColor("#008302"));
                    }
                });
            }

            @Override
            public void onFailure(final Exception e) {
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        statusTextView.setText("firebase unavailable - ".concat(e.getLocalizedMessage()));
                        statusTextView.setTextColor(Color.parseColor("#ff0000"));
                    }
                });
            }
        });
    }


}
