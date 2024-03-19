package com.wapss.ballbyballnew.lottery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.activity.MainActivity;
import com.wapss.ballbyballnew.activity.SplashActivity;

public class LotteryHomeActivity extends AppCompatActivity {
    ImageView mainApp;
    TextView tv_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_home);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.orange));
        initi();
        mainApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LotteryHomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tv_winner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LotteryHomeActivity.this, LotteryResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initi() {
        mainApp = findViewById(R.id.mainApp);
        tv_winner = findViewById(R.id.tv_winner);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LotteryHomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}