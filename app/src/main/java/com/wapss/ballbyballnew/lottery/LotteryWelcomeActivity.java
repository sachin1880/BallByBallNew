package com.wapss.ballbyballnew.lottery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.activity.LoginActivity;
import com.wapss.ballbyballnew.activity.MainActivity;
import com.wapss.ballbyballnew.activity.SplashActivity;

import java.util.Objects;

public class LotteryWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_welcome);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.orange));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    startActivity(new Intent(LotteryWelcomeActivity.this, LoginActivity.class));
                    finish();
            }
        }, 3000);
    }
}