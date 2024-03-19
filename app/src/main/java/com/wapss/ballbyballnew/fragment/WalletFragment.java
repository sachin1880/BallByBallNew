package com.wapss.ballbyballnew.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.activity.Bank_Details;
import com.wapss.ballbyballnew.activity.NotificationActivity;
import com.wapss.ballbyballnew.activity.OtpActivity;


public class WalletFragment extends Fragment {

    LinearLayout btn_deposite,btn_withdrawal;
    ImageView btn_notification;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View wallet =  inflater.inflate(R.layout.fragment_wallet, container, false);

        btn_deposite = wallet.findViewById(R.id.btn_deposite);
        btn_withdrawal = wallet.findViewById(R.id.btn_withdrawal);
        btn_withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Bank_Details.class));
            }
        });
        btn_notification = wallet.findViewById(R.id.btn_notification);
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NotificationActivity.class));
            }
        });
        btn_deposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.deposit_layout,null);
                //builder1.setCancelable(false);
                builder1.setView(dialogView1);
                final androidx.appcompat.app.AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
                //alertDialog1.setCanceledOnTouchOutside(false);
            }
        });
        return wallet;
    }
}