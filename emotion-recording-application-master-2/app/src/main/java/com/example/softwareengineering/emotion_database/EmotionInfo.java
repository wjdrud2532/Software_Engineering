package com.example.softwareengineering.emotion_database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EmotionInfo {
    @PrimaryKey (autoGenerate = true) int id;
    @ColumnInfo (name = "emotionType") String emotionType;
    @ColumnInfo (name = "emotionImage") String emotionImage;
    @ColumnInfo (name = "emotionName") String emotionName;
    @ColumnInfo (name = "customEmotionDescription") String customEmotionDescription;
    @ColumnInfo (name = "similarEmotion") String similarEmotion;
    @ColumnInfo (name = "keyword1") String keyword1;
    @ColumnInfo (name = "keyword2") String keyword2;
    @ColumnInfo (name = "emotionDescriptionEditText") String emotionDescriptionEditText;

    public String getEmotionType() { return emotionType; }
    public String getEmotionName() { return emotionName; }
    public String getSimilarEmotion() { return similarEmotion; }

    public EmotionInfo(String emotionType, String emotionImage, String emotionName, String customEmotionDescription,
                       String similarEmotion, String keyword1, String keyword2, String emotionDescriptionEditText) {
        this.emotionType = emotionType;
        this.emotionImage = emotionImage;
        this.emotionName = emotionName;
        this.customEmotionDescription = customEmotionDescription;
        this.similarEmotion = similarEmotion;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.emotionDescriptionEditText = emotionDescriptionEditText;
    }
}
