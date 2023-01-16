package com.example.softwareengineering.record;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.example.softwareengineering.emotion_database.EmotionDataBase;
import com.example.softwareengineering.emotion_database.EmotionInfo;
import com.example.softwareengineering.R;

/**
 * RecordActivityViewModel 과 연결된 Model
 */
public class RecordModel {
    
    // 감정 기록을 할 때 필요한 정보들
    private int emotionId = 0;
    private String emotionTitle = "";
    private String customEmotionImage = "";
    private String customEmotionTitle = "";
    private String customEmotionDescription = "";
    private String similarEmotion = "";

    public RecordModel(Intent previousIntent, Resources r) {
        emotionId = previousIntent.getIntExtra(r.getString(R.string.emotionId), 0);
        emotionTitle = previousIntent.getStringExtra(r.getString(R.string.emotionTitle));
        customEmotionImage = previousIntent.getStringExtra(r.getString(R.string.customEmotionImage));
    }

    public int getEmotionId() { return emotionId; }
    public String getEmotionTitle() { return emotionTitle; }
    public String getCustomEmotionImage() { return customEmotionImage; }
    public String getCustomEmotionTitle() { return customEmotionTitle; }

    public void setCustomEmotion(Intent previousIntent, Resources r) {
        customEmotionTitle = previousIntent.getStringExtra(r.getString(R.string.customEmotionTitle));
        customEmotionDescription = previousIntent.getStringExtra(r.getString(R.string.customEmotionDescription));
        similarEmotion = previousIntent.getStringExtra(r.getString(R.string.similarEmotion));
    }

    public void setDB(Context context, String emotionImage, String keyword1, String keyword2, String emotionDescription) {
        String emotionType = getEmotionType(context.getResources());
        String eTitle = getEmotionTitle(emotionType, context.getResources());

        EmotionDataBase.getInstance(context).emotionInfoDao().insert(
                new EmotionInfo(emotionType, emotionImage, eTitle, customEmotionDescription,
                                similarEmotion, keyword1, keyword2, emotionDescription)
        );
    }

    /**
     * 기본 감정으로 셋팅하였는지, 커스텀 감정으로 셋팅하였는지를 String 으로 return
     */
    private String getEmotionType(Resources r) {
        String emotionType;
        if (emotionId != 0 && customEmotionImage.contentEquals(r.getText(R.string.plus)) && customEmotionTitle.equals("")) {
            emotionType = r.getText(R.string.default_emotion).toString();
        } else {
            emotionType = r.getText(R.string.custom_emotion).toString();
        }

        return emotionType;
    }

    /**
     * 기본 감정인지 커스텀 감정인지에 따라 (인자로 들어온 emotionType 으로 판별)
     * 감정 제목(title)을 return
     */
    private String getEmotionTitle(String emotionType, Resources r) {
        String eTitle;
        if (emotionType.contentEquals(r.getText(R.string.default_emotion))) {
            eTitle = emotionTitle;
        } else {
            eTitle = customEmotionTitle;
        }
        return eTitle;
    }
}
