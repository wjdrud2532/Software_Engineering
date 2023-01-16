package com.example.softwareengineering.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.softwareengineering.R;
import com.example.softwareengineering.databinding.ActivityWeatherBinding;

/**
 * 기록된 감정을 확인하여 날씨로 확인할 수 있는 화면
 */
public class WeatherActivity extends AppCompatActivity {

    private WeatherActivityViewModel viewModel;

    private ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        binding.setActivity(this);

        viewModel = new ViewModelProvider(this).get(WeatherActivityViewModel.class);
        viewModel.init(this);

        // 날씨 view update
        viewModel.getWeatherEmotion().observe(this, weatherEmotion -> binding.weatherEmotion.setText(weatherEmotion));

        binding.backButton.setOnClickListener(v -> finish());

        // Intent 로 넘겨받은 내용을 토대로 "통계 내용" 에 반영
        binding.statisticsContentTextView1.setText(getIntent().getStringExtra(getText(R.string.content1).toString()));
        binding.statisticsContentTextView2.setText(getIntent().getStringExtra(getText(R.string.content2).toString()));
        binding.statisticsContentTextView3.setText(getIntent().getStringExtra(getText(R.string.content3).toString()));
        binding.statisticsContentTextView4.setText(getIntent().getStringExtra(getText(R.string.content4).toString()));
    }
}