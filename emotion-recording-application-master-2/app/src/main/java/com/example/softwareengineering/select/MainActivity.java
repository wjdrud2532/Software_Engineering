package com.example.softwareengineering.select;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.softwareengineering.R;
import com.example.softwareengineering.statistics.StatisticsActivity;
import com.example.softwareengineering.databinding.ActivityMainBinding;
import com.example.softwareengineering.record.RecordActivity;

/**
 * 메인 첫 화면으로 감정을 기록의 첫 시작을 할 수 있는 화면
 * 기본 감정, 커스텀 감정 등을 기록하고, RecordActivity 로 전환
 */
public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dlg;

    private CheckBox[] cb_array = null;
    private boolean isToggle = true;

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    private String customSimilarEmotion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        cb_array = new CheckBox[]{binding.checkbox1, binding.checkbox2, binding.checkbox3, binding.checkbox4, binding.checkbox5, binding.checkbox6};

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init(getResources());

        // Dialog init 과정 : 유사한 감정 선택 이모티콘 셋팅
        viewModel.getVersionArray().observe(this, versionArray -> dlg.setSingleChoiceItems(versionArray, 0, (dialog, which) -> binding.addEmotionButton.setText(versionArray[which])));

        // "Next" 버튼 클릭 -> ViewModel 에서 Title 업데이트 -> observe -> Intent 에 감정 이미지, 제목, 커스텀 감정 정보 등을 담아서 activity 전환
        viewModel.getTitle().observe(this, this::startRecordActivity);

        initDialog();
        initButton();
        initCheckBox();
    }

    // 유사한 감정 선택 dialog init
    private void initDialog() {
        dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle(getText(R.string.select_emotion)); //제목
        dlg.setPositiveButton(getText(R.string.confirm), (dialog, which) -> Toast.makeText(MainActivity.this, getText(R.string.complete_select_emotion), Toast.LENGTH_SHORT).show());
        viewModel.initVersionArray();
    }

    @SuppressLint("NonConstantResourceId")
    private void initButton() {
        // toggle 버튼 기능 구현 (custom 감정 기록 부분 보이기, 숨기기)
        binding.recordActivityToggleButton.setOnClickListener(view -> {
            if (isToggle) {
                binding.recordActivityToggleButton.setText(getText(R.string.up_arrow));
                binding.emotionAddLayout.setVisibility(View.VISIBLE);
                isToggle = false;
            } else {
                binding.recordActivityToggleButton.setText(R.string.down_arrow);
                binding.emotionAddLayout.setVisibility(View.GONE);
                isToggle = true;
            }
        });

        // '+' 모양의 이미지 클릭시 유사한 감정 고르기 기능 (Alertdialog 를 띄워서 감정 모양 선택)
        binding.addEmotionButton.setOnClickListener(view -> dlg.show());
        
        // "Next" 버튼 클릭 시 ViewModel -> Model 에서 체크박스 체크 여부에 따라 알맞은 title 저장 -> observe
        binding.nextButton.setOnClickListener(view -> {
            // 기본 감정 체크 여부
            boolean[] isChecked = {binding.checkbox1.isChecked(), binding.checkbox2.isChecked(), binding.checkbox3.isChecked(),
                    binding.checkbox4.isChecked(), binding.checkbox5.isChecked(), binding.checkbox6.isChecked()};
            viewModel.checkSelectedCheckbox(isChecked, getResources());
        });

        // 비슷한 감정 선택 부분 기본 설정 (처음 라디오 버튼이 자동 선택되어있도록 함)
        binding.closestEmotion.check(binding.radioButton1.getId());

        // "Home" 버튼 클릭 시
        binding.homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        });

        binding.closestEmotion.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId) {
                case R.id.radioButton1:
                    customSimilarEmotion = getText(R.string.pleasure).toString();
                    break;
                case R.id.radioButton2:
                    customSimilarEmotion = getText(R.string.sadness).toString();
                    break;
                case R.id.radioButton3:
                    customSimilarEmotion = getText(R.string.aggro).toString();
                    break;
                case R.id.radioButton4:
                    customSimilarEmotion = getText(R.string.flutter).toString();
                    break;
                case R.id.radioButton5:
                    customSimilarEmotion = getText(R.string.excited).toString();
                    break;
                case R.id.radioButton6:
                    customSimilarEmotion = getText(R.string.annoyance).toString();
                    break;
            }
        });
    }

    // checkbox 가 최대 한 개만 선택 가능하도록 설정
    private void initCheckBox() {
        for (int i = 0; i < 6; i++) {
            int num = i;
            cb_array[i].setOnClickListener(view -> {
                for (int j = 0; j < 6; j++) {
                    cb_array[j].setChecked(false);
                }
                cb_array[num].setChecked(true);
            });
        }
    }

    // Intent 로 정보들을 담아서 RecordActivity 로 전환
    private void startRecordActivity(String title) {
        String customEmotionImage = binding.addEmotionButton.getText().toString();
        String customEmotionTitle = binding.addEmotionName.getText().toString();
        String customEmotionDescription = binding.editTextTextPersonName.getText().toString();

        if (viewModel.getCheckId() == 0 && (customEmotionTitle.equals("") || customEmotionImage.equals(getText(R.string.plus)))) {
            // 감정 1~6 중 선택되지 않고, 커스텀 감정 제목이나 이미지가 기록되지 않았을 때
            Toast.makeText(this, getText(R.string.select_emotion), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra(getText(R.string.emotionId).toString(), viewModel.getCheckId());
        intent.putExtra(getString(R.string.emotionTitle), title);

        intent.putExtra(getText(R.string.customEmotionImage).toString(), customEmotionImage);
        intent.putExtra(getText(R.string.customEmotionTitle).toString(), customEmotionTitle);
        intent.putExtra(getText(R.string.customEmotionDescription).toString(), customEmotionDescription);
        intent.putExtra(getText(R.string.similarEmotion).toString(), customSimilarEmotion);
        startActivity(intent);
    }
}