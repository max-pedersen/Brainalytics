package com.example.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuizDAO {
    @Query("SELECT * FROM Quiz")
    ArrayList<Quiz> getAllQuiz();

    @Query("SELECT * FROM Quiz " + " WHERE week LIKE :week ORDER BY RANDOM() LIMIT 10")
    ArrayList<Quiz> getSelectedQuiz(String week);


}
