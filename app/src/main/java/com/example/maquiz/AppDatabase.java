package com.example.maquiz;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

    @Database(entities = {Quiz.class}, version = 1, exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase{
        public abstract QuizDAO quizDao();
    }


