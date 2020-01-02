package com.minesweepers.bohni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 999;

    private TextView statusTextView;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = findViewById(R.id.statusTextView);
        loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(this);

        healthCheck();
    }

    private void healthCheck() {
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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {

            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.ic_launcher_background)
                            .setTheme(R.style.Theme_MaterialComponents_DayNight)
                            .build(),
                    RC_SIGN_IN);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                statusTextView.setText("logged in as ".concat(user.getDisplayName()));
                statusTextView.setTextColor(Color.parseColor("#008302"));

            } else {
                statusTextView.setText("Failed to login");
                statusTextView.setTextColor(Color.parseColor("#ff0000"));
            }
        }
    }

}
