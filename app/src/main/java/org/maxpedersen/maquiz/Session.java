package org.maxpedersen.maquiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity= User.class,
        parentColumns="z_id",
        childColumns ="z_id",
        onDelete = CASCADE))

public class Session {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Date metadata_start_time;

    private int z_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMetadata_start_time() {
        return metadata_start_time;
    }

    public void setMetadata_start_time(Date metadata_start_time) {
        this.metadata_start_time = metadata_start_time;
    }

    public int getZ_id() {
        return z_id;
    }

    public void setZ_id(int z_id) {
        this.z_id = z_id;
    }
}



