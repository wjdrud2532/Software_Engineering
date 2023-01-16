package com.example.softwareengineering.weather;

import android.content.Context;

import com.example.softwareengineering.R;
import com.example.softwareengineering.emotion_database.EmotionDataBase;
import com.example.softwareengineering.emotion_database.EmotionInfo;

import java.util.List;

/**
 * StatisticsActivityViewModel 과 연결된 Model
 */
public class WeatherModel {

    // 감정을 날씨로 표현한 정보
    private String weatherEmotion = "";
    int totalScore = 0;

    public String getWeatherEmotion() { return weatherEmotion; }

    public void calculateScore(Context context) {
        // DB 에서 탐색 후 전체 점수에 반영
        List<EmotionInfo> infos = EmotionDataBase.getInstance(context).emotionInfoDao().getAll();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getEmotionType().contentEquals(context.getText(R.string.default_emotion))) {
                calculateEmotion(infos.get(i).getEmotionName());
            } else {
                calculateEmotion(infos.get(i).getSimilarEmotion());
            }
        }

        // 점수를 토대로 날씨 설정
        if (totalScore <= 5 && totalScore >= -5) {
            weatherEmotion = context.getText(R.string.cloud).toString();
        } else if (totalScore > 5) {
            weatherEmotion = context.getText(R.string.sunny).toString();
        } else {
            weatherEmotion = context.getText(R.string.rain).toString();
        }
    }

    private void calculateEmotion(String emotion) {
        switch (emotion) {
            case "기쁨":
                totalScore += 1;
                break;
            case "슬픔":
                totalScore -= 1;
                break;
            case "화남":
                totalScore -= 3;
                break;
            case "설렘":
                totalScore += 2;
                break;
            case "신남":
                totalScore += 3;
                break;
            case "짜증":
                totalScore -= 2;
                break;
            default:
                break;
        }
    }
}
