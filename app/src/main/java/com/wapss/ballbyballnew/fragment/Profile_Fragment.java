package com.wapss.ballbyballnew.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wapss.ballbyballnew.Game_Rules;
import com.wapss.ballbyballnew.HowToPlay;
import com.wapss.ballbyballnew.Match_Schedule;
import com.wapss.ballbyballnew.R;

public class Profile_Fragment extends Fragment {

    LinearLayout game_rules_layout,how_to_Play,match_schedule;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View profile = inflater.inflate(R.layout.fragment_profile_, container, false);
        initialize(profile);
        game_rules_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Game_Rules.class));
            }
        });
        how_to_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HowToPlay.class));
            }
        });
        match_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Match_Schedule.class));
            }
        });
        return profile;
    }

    private void initialize(View profile) {
        game_rules_layout = profile.findViewById(R.id.game_rules_layout);
        how_to_Play = profile.findViewById(R.id.how_to_Play);
        match_schedule = profile.findViewById(R.id.match_schedule);
    }
}