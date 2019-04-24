package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface ResultDAO {
    @Insert
    void insertResult(Result ... results);

    @Insert
    void insertBatchResults(List<Result> results);

    @Query("SELECT SUM(score) FROM Result WHERE z_id LIKE :mZ_id")
    int getSummedScore(int mZ_id);
}
