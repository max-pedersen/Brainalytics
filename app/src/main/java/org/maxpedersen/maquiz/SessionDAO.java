package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SessionDAO {
    @Insert
    void insertUser(User ... users);

    @Query("SELECT MAX(metadata_start_time) FROM Session WHERE z_id LIKE :mZ_id")
    String getMostRecentSession(int mZ_id);




}