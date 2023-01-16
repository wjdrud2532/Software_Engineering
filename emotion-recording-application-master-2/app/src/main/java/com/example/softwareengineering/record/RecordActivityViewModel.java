package com.example.softwareengineering.record;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.softwareengineering.R;

/**
 * RecordActivity 와 연결된 ViewModel
 */
public class RecordActivityViewModel extends ViewModel {

    private RecordModel model;

    // 감정 기록 시 필요한 정보들
    private final MutableLiveData<String> emotionImage = new MutableLiveData<>();
    private final MutableLiveData<String> emotionName = new MutableLiveData<>();

    public MutableLiveData<String> getEmotionImage() { return emotionImage; }
    public MutableLiveData<String> getEmotionName() { return emotionName; }

    public void init(Intent previousIntent, Resources r) {
        model = new RecordModel(previousIntent, r);

        if (model.getEmotionId() != 0 && model.getCustomEmotionImage().contentEquals(r.getText(R.string.plus)) && model.getCustomEmotionTitle().equals("")) {
            // 기본 감정 중 선택하였을 때, 이미지와 제목 설정
            setEmotionImage(model.getEmotionId(), r);
            emotionName.setValue(model.getEmotionTitle());
        } else {
            // 커스텀 감정을 설정하였을 때, 부가적인 정보 저장 후 이미지와 제목 설정
            model.setCustomEmotion(previousIntent, r);
            emotionImage.setValue(model.getCustomEmotionImage());
            emotionName.setValue(model.getCustomEmotionTitle());
        }
    }

    // 기본 감정일 때, 감정 이모티콘 이미지 셋팅
    private void setEmotionImage(int emotionId, Resources r) {
        switch (emotionId) {
            case 1:
                emotionImage.setValue(r.getText(R.string.grinning_face_with_smiling_eyes).toString());
                break;
            case 2:
                emotionImage.setValue(r.getText(R.string.crying_face).toString());
                break;
            case 3:
                emotionImage.setValue(r.getText(R.string.angry_face).toString());
                break;
            case 4:
                emotionImage.setValue(r.getText(R.string.face_with_hand_over_mouth).toString());
                break;
            case 5:
                emotionImage.setValue(r.getText(R.string.star_struck).toString());
                break;
            case 6:
                emotionImage.setValue(r.getText(R.string.enraged_face).toString());
                break;
            default:
                break;
        }
    }

    public void setDB(Context context, String emotionImage, String keyword1, String keyword2, String emotionDescription) {
        model.setDB(context, emotionImage, keyword1, keyword2, emotionDescription);
    }
}
