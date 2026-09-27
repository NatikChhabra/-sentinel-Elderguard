package com.example.elderguard.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Medication.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicationDao dao();
}