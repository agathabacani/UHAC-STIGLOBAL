package com.example.jacechristian.uhac_stiglobal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingScreen extends AppCompatActivity {
    protected boolean active = true;
    protected int splashTime = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (active && (waited < splashTime)) {
                        sleep(100);
                        if (active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {

                    startActivity(new Intent(LoadingScreen.this,
                            MainActivity.class));
                    finish();
                }
            };
        };
        splashTread.start();

    }
}
