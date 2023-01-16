package com.example.softwareengineering.emotion_database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmotionInfoDao {
    @Query("SELECT * FROM emotionInfo")
    List<EmotionInfo> getAll();

    @Insert
    void insert(EmotionInfo emotionInfo);
}
