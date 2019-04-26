package org.maxpedersen.maquiz;

import android.annotation.TargetApi;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

//This class establishes the databases which are used in application
    @Database(entities = {Question.class, Result.class, Session.class, User.class, UserResultJoin.class}
    , version = 2, exportSchema = false)
    // need to use version 2 if using RoomAsset library, otherwise version 1
    @TypeConverters({Converters.class})
    public abstract class AppDatabase extends RoomDatabase{

//        private static AppDatabase INSTANCE;
        public abstract QuestionDAO questionDAO();
        public abstract UserDAO userDAO();
        public abstract SessionDAO sessionDAO();
        public abstract ResultDAO resultDAO();
        public abstract UserResultJoinDAO userResultJoinDAO();

//    public synchronized static AppDatabase getInstance(Context context) {
//        if (INSTANCE == null) {
//            INSTANCE = buildDatabase(context);
//        }
//        return INSTANCE;
//    }
//
//    private static AppDatabase buildDatabase(final Context context) {
//        return Room.databaseBuilder(context,
//                AppDatabase.class,
//                "my-database")
//                .addCallback(new Callback() {
//                    @Override
//                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                        super.onCreate(db);
//                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                getInstance(context).questionDAO().insertQuestionBatch(getCSV());
//                            }
//                        });
//                    }
//                })
//                .build();
//    }
//    // All the Values we want to insert once into the DB is put in here, I'll call you about it
//
//    @TargetApi(Build.VERSION_CODES.O)
//    private static List<Question> getCSV(){
//
//        List<Question> questionsFromCSV = null;
//        try {
//            questionsFromCSV = QuizActivity.readCSV();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return questionsFromCSV;
//    }


}


