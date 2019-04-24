package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.provider.ContactsContract;

    public class DatabaseService {
        private static DatabaseService instance = null;
        private static AppDatabase appDatabase;

        private DatabaseService(Context context) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,
                    "Overall Database")
                    .allowMainThreadQueries()
                    .build();
        }

        public static DatabaseService getDbInstance(Context context) {
            if (instance == null) {
                instance = new DatabaseService(context);
            }
            return instance;
        }

        public AppDatabase getAppDatabase() {return appDatabase; }


    }
