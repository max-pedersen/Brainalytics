package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;


    @Dao
    public interface UserResultJoinDAO {
        @Insert
        void insert(UserResultJoin userResultJoin);

        @Query("SELECT u.first_name, u.z_id, SUM(r.score) as total_score FROM Result r INNER JOIN User u on u.z_id = r.z_id GROUP BY u.z_id ORDER BY SUM(r.score) DESC LIMIT 10")
                List<UserResultJoin> getTopUsers();



        @Query("SELECT SUM(score) FROM Result r WHERE z_id LIKE :mZ_id")
        int getUserSummedScore(int mZ_id);

    }
