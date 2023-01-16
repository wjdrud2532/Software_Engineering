package com.example.softwareengineering.statistics;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.RadarData;

import java.util.List;

/**
 * StatisticsActivity 와 연결된 ViewModel
 */
public class StatisticsActivityViewModel extends ViewModel {

    private StatisticsModel model;

    // 감정들의 점수를 기록한 List
    private final MutableLiveData<List<Score>> scoreList = new MutableLiveData<>();

    // 차트를 만들 때 필요한 정보를 만들어 저장
    private final MutableLiveData<RadarData> radarData = new MutableLiveData<>();
    private final MutableLiveData<PieData> pieData = new MutableLiveData<>();
    private final MutableLiveData<BarData> barData = new MutableLiveData<>();

    // Activity 생성 -> ViewModel 생성 -> model 에서 데이터 업데이트 -> LiveData 반영 -> 화면에 통계 정보 반영
    public void init(Context context) {
        model = new StatisticsModel();
        model.calculateScore(context);

        scoreList.setValue(model.getScoreList());
        radarData.setValue(model.getRadarData());
        pieData.setValue(model.getPieData());
        barData.setValue(model.getBarData());
    }

    public MutableLiveData<List<Score>> getScore() { return scoreList; }
    public MutableLiveData<RadarData> getRadarData() { return radarData; }
    public MutableLiveData<PieData> getPieData() { return pieData; }
    public MutableLiveData<BarData> getBarData() { return barData; }

    public String[] getLabels() { return model.getLabels(); }
}
