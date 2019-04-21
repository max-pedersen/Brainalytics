package org.maxpedersen.maquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM Question")
    List<Question> getAllQuiz();

    @Query("SELECT * FROM Question " + " WHERE week LIKE :week ORDER BY RANDOM() LIMIT 10")
    List<Question> getSelectedQuiz(int week);


    @Insert
    void insertQuestion(Question ... questions);

    @Insert
    void insertQuestionBatch(List<Question> question);


    /*@Query("INSERT INTO Question VALUES(1, '645789', 'tim@email.com', 'Understand and Create'," +
            "'Refine', 'Present and Practice', 'Re-design', 'option_4', 'multiple_choice', 'https://www.youtube.com/watch?v=IGXVaVWD_3I'," +
            "'Good Charts Ch1-4')")
    void random();*/




}
