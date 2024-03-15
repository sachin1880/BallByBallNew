package com.wapss.ballbyballnew.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wapss.ballbyballnew.R;

public class TomorrowFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View tomorrow = inflater.inflate(R.layout.fragment_tomorrow, container, false);

        return tomorrow;
    }
}