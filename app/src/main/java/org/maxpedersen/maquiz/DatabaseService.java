package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.huma.room_for_asset.RoomAsset;


/* This class is based off the DatabaseService used in Oscar's Week 10 tutorial, as we had not previously
impemented it */
    public class DatabaseService {
        private static DatabaseService instance = null;
        private static AppDatabase appDatabase;

        private DatabaseService(Context context) {
            // "database_name.db" here refers to the pre-existing pre-populated database
            //appDatabase = Room.databaseBuilder(context, AppDatabase.class, "database_name.db")
                    appDatabase = RoomAsset.databaseBuilder(context, AppDatabase.class, "prpeopulated.db")
                            .allowMainThreadQueries().build();
                    Log.d( " from databaseservice", appDatabase.toString());

        }

        public static DatabaseService getDbInstance(Context context) {
            if (instance == null) {
                instance = new DatabaseService(context);
            }
            return instance;
        }

        public AppDatabase getAppDatabase() {return appDatabase; }
    }

    /*
    private void populateInitialData() {
        if (cheese().count() == 0) {
            Cheese cheese = new Cheese();
            beginTransaction();
            try {
                for (int i = 0; i < Cheese.CHEESES.length; i++) {
                    cheese.name = Cheese.CHEESES[i];
                    cheese().insert(cheese);
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
     */