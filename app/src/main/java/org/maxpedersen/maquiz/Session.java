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

}
