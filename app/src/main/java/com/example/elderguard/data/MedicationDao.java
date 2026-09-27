package com.example.elderguard.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MedicationDao {
    @Query("SELECT * FROM Medication")
    List<Medication> getAll();

    @Insert
    void insert(Medication m);
}