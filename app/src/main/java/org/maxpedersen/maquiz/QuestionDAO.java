package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM Question")
    List<Question> getAllQuiz();

    @Query("SELECT * FROM Question " + " WHERE week LIKE :week ORDER BY RANDOM() LIMIT 11")
    List<Question> getSelectedQuiz(int week);


    @Insert
    void insertQuestion(Question ... questions);

    @Insert
    void insertQuestionBatch(List<Question> question);



}
