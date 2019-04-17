package com.example.maquiz;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

    @Database(entities = {Quiz.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase{
        public abstract QuizDAO quizDao();
    }


