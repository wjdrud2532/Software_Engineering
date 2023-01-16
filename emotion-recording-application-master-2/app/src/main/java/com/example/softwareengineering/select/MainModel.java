package com.example.softwareengineering.select;

import android.content.res.Resources;

import com.example.softwareengineering.R;

/**
 * MainActivityViewModel 과 연결된 Model
 * title, versionArray 정보는 ViewModel 과 같음
 */
public class MainModel {

    private String title = "";
    private String[] versionArray;

    public String getTitle() { return title; }
    public String[] getVersionArray() { return versionArray; }

    public MainModel(Resources r) {
        versionArray = new String[]{r.getText(R.string.grinning_face_emoji).toString(), r.getText(R.string.grinning_face_with_big_eyes).toString(), r.getText(R.string.face_with_tears_of_joy).toString(), r.getText(R.string.smiling_face_with_halo).toString(),
                r.getText(R.string.winking_face).toString(), r.getText(R.string.smiling_face_with_heart_eyes).toString(), r.getText(R.string.sleepy_face).toString()};
    }

    // 메인화면 기본 감정 선택 부분에서 체크되어 있는 부분을 확인하여 알맞은 제목 설정
    public int checkSelectedCheckbox(boolean[] isChecked, Resources r) {
        int checkId = 0;

        if (isChecked[0]) {
            checkId = 1;
            title = r.getText(R.string.pleasure).toString();
        } else if (isChecked[1]) {
            checkId = 2;
            title = r.getText(R.string.sadness).toString();
        } else if (isChecked[2]) {
            checkId = 3;
            title = r.getText(R.string.aggro).toString();
        } else if (isChecked[3]) {
            checkId = 4;
            title = r.getText(R.string.flutter).toString();
        } else if (isChecked[4]) {
            checkId = 5;
            title = r.getText(R.string.excited).toString();
        } else if (isChecked[5]) {
            checkId = 6;
            title = r.getText(R.string.annoyance).toString();
        }

        return checkId;
    }
}
