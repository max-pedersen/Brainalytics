package org.maxpedersen.maquiz;

import android.annotation.TargetApi;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;


//This class establishes the databases which are used in application
    @Database(entities = {Question.class, Result.class, Session.class, User.class, UserResultJoin.class}
    , version = 2, exportSchema = false)
    // Since the RoomAsset library is used in DatabaseService, version = 2 has to be used here
    @TypeConverters({Converters.class})
    public abstract class AppDatabase extends RoomDatabase{

//        private static AppDatabase INSTANCE;
        public abstract QuestionDAO questionDAO();
        public abstract UserDAO userDAO();
        public abstract SessionDAO sessionDAO();
        public abstract ResultDAO resultDAO();
        public abstract UserResultJoinDAO userResultJoinDAO();



}


