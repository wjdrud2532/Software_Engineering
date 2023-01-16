package com.example.softwareengineering.emotion_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EmotionInfo.class}, version = 1)
public abstract class EmotionDataBase extends RoomDatabase {
    public abstract EmotionInfoDao emotionInfoDao();

    private static EmotionDataBase database;
    private static final String DATABASE_NAME = "emotionInfo";

    public synchronized static EmotionDataBase getInstance(Context context)
    {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), EmotionDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
