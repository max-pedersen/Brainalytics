package org.maxpedersen.maquiz;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

//Results class to instantiate result objects
@Entity(foreignKeys = @ForeignKey(entity=User.class,
        parentColumns="z_id",
        childColumns ="z_id",
        onDelete = CASCADE))

    public class Result {
        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "score")
        private int score;

        private Date metadata_created_at;

        private Date time_taken;

        private String source_origin;
        // this will denote whether the XP has come from a Quiz activity or from a Content activity

        private int z_id;

    public Result(int id, int score, int z_id) {
        this.id = id;
        this.score = score;
        // new Date() to pull exact time of creation
        this.metadata_created_at = new Date();
        this.time_taken = new Date();
        this.z_id = z_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getMetadata_created_at() {
        return metadata_created_at;
    }

    public void setMetadata_created_at(Date metadata_created_at) {
        this.metadata_created_at = metadata_created_at;
    }

    public Date getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(Date time_taken) {
        this.time_taken = time_taken;
    }

    public int getZ_id() {
        return z_id;
    }

    public void setZ_id(int z_id) {
        this.z_id = z_id;
    }

    public String getSource_origin() {
        return source_origin;
    }

    public void setSource_origin(String source_origin) {
        this.source_origin = source_origin;
    }
}

