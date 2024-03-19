package com.wapss.ballbyballnew.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView;

import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.activity.League_Page;
import com.wapss.ballbyballnew.activity.NotificationActivity;
import com.wapss.ballbyballnew.lottery.LotteryWelcomeActivity;

public class HomeFragment extends Fragment {

    ImageView btn_notification;
    TextView btn_view_all;
    ImageView lottery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View home = inflater.inflate(R.layout.fragment_home, container, false);
        Window window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getActivity().getWindow().getContext(), R.color.black));

        btn_notification = home.findViewById(R.id.btn_notification);
        btn_view_all = home.findViewById(R.id.btn_view_all);
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NotificationActivity.class));
            }
        });
        btn_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), League_Page.class));
            }
        });

        initi(home);

        lottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LotteryWelcomeActivity.class);
                startActivity(intent);
            }
        });

        return home;
    }

    private void initi(View home) {
        lottery = home.findViewById(R.id.lottery);
    }
}