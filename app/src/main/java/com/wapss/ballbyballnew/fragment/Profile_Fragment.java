package com.wapss.ballbyballnew.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wapss.ballbyballnew.activity.Game_Rules;
import com.wapss.ballbyballnew.activity.HowToPlay;
import com.wapss.ballbyballnew.activity.LoginActivity;
import com.wapss.ballbyballnew.activity.MainActivity;
import com.wapss.ballbyballnew.activity.Match_Schedule;
import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.activity.NotificationActivity;
import com.wapss.ballbyballnew.activity.Pages;
import com.wapss.ballbyballnew.activity.Refer_Earn;
import com.wapss.ballbyballnew.activity.RegistrationActivity;
import com.wapss.ballbyballnew.activity.Update_Profile;
import com.wapss.ballbyballnew.apiService.ApiService;
import com.wapss.ballbyballnew.response.GetProfile;
import com.wapss.ballbyballnew.response.LogOutResponse;
import com.wapss.ballbyballnew.response.RegistrationResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Fragment extends Fragment {

    LinearLayout game_rules_layout, how_to_Play, match_schedule, ll_logOut,notification_layout,settelment_layout,
            payment_policy_layout,about_layout,share_layout;
    TextView name, phone_number;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    String deviceToken;
    LinearLayout ll_profile;
    private Dialog noInternetDialog;


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
        notification_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NotificationActivity.class));
            }
        });
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
        settelment_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Pages.class));
            }
        });
        match_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Match_Schedule.class));
            }
        });
        payment_policy_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Pages.class));
            }
        });
        about_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Pages.class));
            }
        });
        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Update_Profile.class);
                startActivity(intent);
            }
        });
        share_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Refer_Earn.class);
                startActivity(intent);
            }
        });

        ll_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noInternetDialog = new Dialog(getContext());
                noInternetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                noInternetDialog.setContentView(R.layout.logout_layout);
                noInternetDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                noInternetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                noInternetDialog.show();
                TextView et_yes = (TextView) noInternetDialog.findViewById(R.id.et_yes);
                TextView et_cancel = (TextView) noInternetDialog.findViewById(R.id.et_cancel);
                et_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences preferences = getContext().getSharedPreferences("login_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                et_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        noInternetDialog.dismiss();
                    }
                });
                noInternetDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                noInternetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

                callLogOutAPI();
            }
        });

        //callApi
        callProfileAPI();
        return profile;
    }

    private void callLogOutAPI() {
        progressDialog.show();
        String Token = "Bearer " + deviceToken;
        Call<LogOutResponse> login_apiCall = ApiService.apiHolders().getLogOut(Token);
        login_apiCall.enqueue(new Callback<LogOutResponse>() {
            @Override
            public void onResponse(Call<LogOutResponse> call, Response<LogOutResponse> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(requireContext().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogOutResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(requireContext().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callProfileAPI() {
        progressDialog.show();
        String Token = "Bearer " + deviceToken;
        Call<GetProfile> login_apiCall = ApiService.apiHolders().getProfile(Token);
        login_apiCall.enqueue(new Callback<GetProfile>() {
            @Override
            public void onResponse(Call<GetProfile> call, Response<GetProfile> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                    assert response.body() != null;
                    name.setText(response.body().getName());
                    phone_number.setText(response.body().getPhoneNumber());
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(requireContext().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetProfile> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(requireContext().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialize(View profile) {
        ll_logOut = profile.findViewById(R.id.ll_logOut);
        ll_profile = profile.findViewById(R.id.ll_profile);
        game_rules_layout = profile.findViewById(R.id.game_rules_layout);
        how_to_Play = profile.findViewById(R.id.how_to_Play);
        match_schedule = profile.findViewById(R.id.match_schedule);
        name = profile.findViewById(R.id.name);
        phone_number = profile.findViewById(R.id.phone_number);
        notification_layout = profile.findViewById(R.id.notification_layout);
        settelment_layout = profile.findViewById(R.id.settelment_layout);
        about_layout = profile.findViewById(R.id.about_layout);
        payment_policy_layout = profile.findViewById(R.id.payment_policy_layout);
        share_layout = profile.findViewById(R.id.share_layout);
        //shared Pref
        loginPref = getActivity().getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        deviceToken = loginPref.getString("deviceToken", null);
        //loading
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
    }
}