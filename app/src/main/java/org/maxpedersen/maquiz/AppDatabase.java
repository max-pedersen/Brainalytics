package org.maxpedersen.maquiz;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

//This class establishes the databases which are used in application
    @Database(entities = {Question.class, User.class}, version = 1, exportSchema = false)
    @TypeConverters({Converters.class})
    public abstract class AppDatabase extends RoomDatabase{
        public abstract QuestionDAO questionDAO();
        public abstract UserDAO userDAO();
    }


