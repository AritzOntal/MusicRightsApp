package com.svalero.musicrightsapp.Db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.svalero.musicrightsapp.database.ConcertDao;

import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.util.Constants;

@Database(entities = {Concert.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ConcertDao concertDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Constants.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
