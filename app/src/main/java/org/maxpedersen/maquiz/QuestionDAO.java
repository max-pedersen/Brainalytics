package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM Question")
    List<Question> getAllQuiz();

    @Query("SELECT * FROM Question " + " WHERE week LIKE :week ORDER BY RANDOM() LIMIT 10")
    List<Question> getSelectedQuiz(String week);


}
