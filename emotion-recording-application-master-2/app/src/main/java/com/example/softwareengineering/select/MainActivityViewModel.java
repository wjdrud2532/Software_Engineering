package com.example.softwareengineering.select;

import android.content.res.Resources;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * MainActivity 와 연결된 ViewModel
 *
 * versionArray : custom 감정 선택 중 유사한 감정 선택 이모티콘 정보
 * title : 기본 감정 중 선택한 감정의 제목 ( 예) 기쁨 )
 * checkId : 기본 감정 중 선택한 감정의 Id (맨 앞에서부터 1 ~ 6, 미선택 시 0)
 */
public class MainActivityViewModel extends ViewModel {

    private MainModel model;

    private final MutableLiveData<String[]> versionArray = new MutableLiveData<>();
    private final MutableLiveData<String> title = new MutableLiveData<>();

    private int checkId;

    public MutableLiveData<String[]> getVersionArray() { return versionArray; }
    public MutableLiveData<String> getTitle() { return title; }
    public int getCheckId() { return checkId; }

    public void init(Resources r) {
        model = new MainModel(r);
    }

    public void initVersionArray() {
        versionArray.setValue(model.getVersionArray());
    }

    // 메인화면 기본 감정 선택 부분에서 체크되어 있는 부분을 확인하여 알맞은 제목 설정
    public void checkSelectedCheckbox(boolean[] isChecked, Resources r) {
        checkId = model.checkSelectedCheckbox(isChecked, r);
        title.setValue(model.getTitle());
    }
}
