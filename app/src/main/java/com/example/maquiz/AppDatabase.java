package com.example.maquiz;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
//This class establishes the databases which are used in application
    @Database(entities = {Quiz.class}, version = 1, exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase{
        public abstract QuizDAO quizDao();
    }


