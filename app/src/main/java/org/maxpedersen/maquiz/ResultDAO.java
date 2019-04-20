package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


@Dao
public interface ResultDAO {
    @Insert
    void insertResult(Result ... results);

    @Query("SELECT SUM(score) FROM Result WHERE z_id LIKE :mZ_id")
    String getSummedScore(int mZ_id);
}
