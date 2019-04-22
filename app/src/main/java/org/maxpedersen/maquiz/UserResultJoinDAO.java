package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;


    @Dao
    public interface UserResultJoinDAO {
        @Insert
        void insert(UserResultJoin userResultJoin);

        /*@Query("SELECT first_name " +
                "FROM User " +
                "INNER JOIN user_result_join " +
                "ON User.z_id=user_result_join.z_id WHERE user_result_join.resultId=:resultId")
                String getUserForResult(final int resultId);

        @Query("SELECT * FROM Result INNER JOIN user_result_join ON " +
                "Result.id=user_result_join.resultId WHERE user_result_join.userId=:userId")
                List<Result> getResultsforUser(final int userId); */


        @Query("SELECT u.first_name, u.z_id, SUM(r.score) as total_score FROM Result r INNER JOIN User u on u.z_id = r.z_id GROUP BY u.z_id ORDER BY SUM(r.score) DESC LIMIT 10")
                List<UserResultJoin> getTopUsers();

    }
