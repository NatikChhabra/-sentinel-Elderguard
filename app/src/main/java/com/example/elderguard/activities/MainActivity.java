package com.example.elderguard.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.elderguard.R;
import com.example.elderguard.services.MonitorService;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.btnStart);
        Button test = findViewById(R.id.btnTest);
        Button nav = findViewById(R.id.btnNav);
        Button meds = findViewById(R.id.btnMeds);

        start.setOnClickListener(v -> {
            Intent i = new Intent(this, MonitorService.class);
            startForegroundService(i);
        });

        test.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://api.whatsapp.com/send?phone=YOUR_NUMBER&text=TEST_FALL_DETECTED"));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });

        nav.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=Kempegowda+International+Airport+Bengaluru");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        meds.setOnClickListener(v -> {
            startActivity(new Intent(this, MedicationActivity.class));
        });
    }
}