package com.example.softwareengineering.weather;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * WeatherActivity 와 연결된 ViewModel
 */
public class WeatherActivityViewModel extends ViewModel {

    private WeatherModel model;

    // 날씨 정보
    private final MutableLiveData<String> weatherEmotion = new MutableLiveData<>();

    // Activity 생성 -> ViewModel 생성 -> model 에서 데이터 업데이트 -> LiveData 반영 -> 화면에 날씨 정보 반영
    public void init(Context context) {
        model = new WeatherModel();
        model.calculateScore(context);
        weatherEmotion.setValue(model.getWeatherEmotion());
    }

    public MutableLiveData<String> getWeatherEmotion() { return weatherEmotion; }
}
