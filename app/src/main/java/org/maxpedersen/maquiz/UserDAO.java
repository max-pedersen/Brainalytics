package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Insert
    void insertBatchUsers(List<User> users);

    @Query("SELECT first_name FROM User WHERE z_id LIKE :mZ_id")
    String getUserName(int mZ_id);

    @Query("SELECT COUNT(z_id) from User WHERE z_id LIKE :mZ_id")
    int checkExists(int mZ_id);

}
