package com.example.elderguard.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medication {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String time;
}