package com.talk2tail.common.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RoomExampleEntity.class}, version = 1)
public abstract class DatabaseRoom extends RoomDatabase {

    private static final String DATABASE_NAME = "RoomDatabase.db";

    private static volatile DatabaseRoom instance;

    public static synchronized DatabaseRoom getInstance() {
        if (instance == null) {
            throw new RuntimeException("Database have not been created");
        }
        return instance;
    }

    public static void create(Context context) {

//        final Migration migration1_2 = new Migration(1, 2) {
//            @Override
//            public void migrate(@NonNull SupportSQLiteDatabase database) {
//                Timber.d("Migration from 1 to 2");
//                database.execSQL("");
//            }
//        };

        if (instance == null) {
            instance = Room.databaseBuilder(
                    context, DatabaseRoom.class, DATABASE_NAME)
//                    .addMigrations(migration1_2)
                    .build();
        }
    }

    public abstract RoomExampleEntityDao getRoomExampleEntityDao();

}
