package com.example.maquiz;

import android.arch.persistence.room.PrimaryKey;

import java.sql.Blob;

public class User {
    @PrimaryKey
    private int id;
    private int z_id;
    private String first_name;
    private Blob user_image;
}
