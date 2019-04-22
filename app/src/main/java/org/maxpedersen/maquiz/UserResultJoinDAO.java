package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;


    @Dao
    public interface UserResultJoinDAO {
        @Insert
        void insert(UserResultJoin userResultJoin);

        @Query("SELECT * FROM User INNER JOIN user_result_join ON " +
                "User.z_id=user_result_join.userId WHERE user_result_join.resultId=:resultId")
                List<User> getUserForResult(final int resultId);

        @Query("SELECT * FROM Result INNER JOIN user_result_join ON " +
                "Result.id=user_result_join.resultId WHERE user_result_join.userId=:userId")
                List<Result> getResultsforUser(final int userId);
    }

