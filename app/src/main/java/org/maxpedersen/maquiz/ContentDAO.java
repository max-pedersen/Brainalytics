package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;

import java.util.List;

@Dao
public interface ContentDAO {
    @Query("SELECT * FROM Content ORDER BY week ASC")
    List<Content> getContents();
}