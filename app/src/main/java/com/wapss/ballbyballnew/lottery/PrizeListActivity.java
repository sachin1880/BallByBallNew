package com.wapss.ballbyballnew.lottery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wapss.ballbyballnew.R;

public class PrizeListActivity extends AppCompatActivity {
    ImageView back;
    LinearLayout ll_view_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize_list);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.orange));

        initi();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrizeListActivity.this, LotteryResultActivity.class);
                startActivity(intent);
            }
        });
        ll_view_winner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrizeListActivity.this, PrizeViewListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initi() {
        back = findViewById(R.id.back);
        ll_view_winner = findViewById(R.id.ll_view_winner);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PrizeListActivity.this, LotteryResultActivity.class);
        startActivity(intent);
    }
}