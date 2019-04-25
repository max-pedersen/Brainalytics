package org.maxpedersen.maquiz;

import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.sql.Blob;
import java.util.Date;
@Entity
public class User {

    @PrimaryKey
    @ColumnInfo(name = "z_id")
    private int z_id;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "created_at")
    private Date created_at;

    @ColumnInfo(name = "score")
    private int score;

    public User(int z_id, String first_name
                //,Blob profile_image
                ) {
        this.z_id = z_id;
        this.first_name = first_name;
        this.created_at = new Date();
        this.score = score;
        //this.profile_image = profile_image;
        //TODO work out how on earth to construct with a Blob, don't think it's possible
    }

    public int getZ_id() {
        return z_id;
    }

    public void setZ_id(int z_id) {
        this.z_id = z_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = new Date();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

