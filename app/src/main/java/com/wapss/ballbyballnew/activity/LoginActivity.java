package com.wapss.ballbyballnew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.apiService.ApiService;
import com.wapss.ballbyballnew.response.LoginResponse;
import com.wapss.ballbyballnew.utilis.DeviceUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tv_login;
    EditText et_phone;
    String regex = "^[6-9][0-9]{9}$";
    String phone, deviceId;
    ProgressDialog progressDialog;
    SharedPreferences loginPref, loginPref2;
    SharedPreferences.Editor editor, editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initi();
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = et_phone.getText().toString();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(phone);
                if (matcher.matches()) {
                    callLoginApi(phone, deviceId);
                }
            }
        });
    }

    private void callLoginApi(String phone, String deviceId) {
        progressDialog.show();
        Call<LoginResponse> login_apiCall = ApiService.apiHolders().login(phone, deviceId);
        login_apiCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 201) {
                    LoginResponse userLogin_response = response.body();
                    assert response.body() != null;
                    String phoneNum = response.body().getMessage();
                    editor.putString("phone", phoneNum);
//                    editor.putString("fcm",fb_token);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void initi() {
        deviceId = DeviceUtils.getDeviceId(getApplicationContext());
        tv_login = findViewById(R.id.tv_login);
        et_phone = findViewById(R.id.et_phone);
        //loading
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        //sharedPrefrence
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}