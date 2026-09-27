package com.example.elderguard.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.elderguard.R;
import com.example.elderguard.data.AppDatabase;
import com.example.elderguard.data.Medication;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MedicationActivity extends AppCompatActivity {
    AppDatabase db;
    ArrayAdapter<String> ad;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_medication);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "m_db").build();
        EditText n = findViewById(R.id.medName);
        EditText t = findViewById(R.id.medTime);
        Button add = findViewById(R.id.btnAdd);
        ListView lv = findViewById(R.id.medList);
        ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(ad);
        add.setOnClickListener(v -> {
            Medication med = new Medication();
            med.name = n.getText().toString();
            med.time = t.getText().toString();
            Executors.newSingleThreadExecutor().execute(() -> {
                db.dao().insert(med);
                load();
            });
        });
        load();
    }

    void load() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Medication> all = db.dao().getAll();
            data.clear();
            for (Medication m : all) data.add(m.name + " at " + m.time);
            runOnUiThread(() -> ad.notifyDataSetChanged());
        });
    }
}