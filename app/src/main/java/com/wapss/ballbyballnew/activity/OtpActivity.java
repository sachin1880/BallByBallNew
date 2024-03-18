package com.wapss.ballbyballnew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.apiService.ApiService;
import com.wapss.ballbyballnew.response.OTP_Response;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    TextView btn_Otp_submit;
    EditText otp1,otp2,otp3,otp4;
    TextView tv_phone,count_time,btn_resend;
    private CountDownTimer countDownTimer;
    long timerDuration = 60000;
    long timerInterval = 1000;
    LinearLayout tv_verify;
    String otp,phone;
    ProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        initi();
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));

        otp_move();

        btn_Otp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(OtpActivity.this, MainActivity.class);
//                startActivity(intent);
                otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();

                if (otp1.getText().toString().isEmpty() || otp2.getText().toString().isEmpty() || otp3.getText().toString().isEmpty() ||
                        otp4.getText().toString().isEmpty()){
                    Toast.makeText(OtpActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
                else {
                    callVerifyOTP(otp,phone);
                }
            }
        });

        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_time.setVisibility(View.VISIBLE);
                CountDown();

//                otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();
//
//                if (otp1.getText().toString().isEmpty() || otp2.getText().toString().isEmpty() || otp3.getText().toString().isEmpty() ||
//                        otp4.getText().toString().isEmpty()){
//                    Toast.makeText(OtpActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    callVerifyOTP(otp,phone);
//                }
            }
        });

        CountDown();
    }

    private void otp_move() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // otp1.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                // otp1.setTextColor(getResources().getColor(R.color.white));
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (otp1.getText().toString().length() == 1) {
                    otp1.clearFocus();
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);
                } else {
                    otp1.requestFocus();
                    //otp1.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                    //otp1.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // otp2.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                // otp2.setTextColor(getResources().getColor(R.color.white));

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (otp2.getText().toString().length() == 1) {
                    otp2.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);
                } else {
                    otp2.clearFocus();
                    otp1.requestFocus();
                    otp1.setCursorVisible(true);
                    //  otp2.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // otp3.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                // otp3.setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (otp3.getText().toString().length() == 1) {
                    otp3.clearFocus();
                    otp4.requestFocus();
                    otp4.setCursorVisible(true);
                } else {
                    otp3.clearFocus();
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);
                    // otp3.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //otp4.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                // otp4.setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (otp4.getText().toString().length() == 1) {
                    otp4.clearFocus();
                    otp4.requestFocus();
                    //otp5.setCursorVisible(true);
                    // otp5.setCursorVisible(true);
                } else {
                    otp3.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);
                    // otp4.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
    }

    private void callVerifyOTP(String otp, String phone) {
        progressDialog.show();
        Call<OTP_Response> login_apiCall = ApiService.apiHolders().OTP_Verify(phone,otp);
        login_apiCall.enqueue(new Callback<OTP_Response>() {
            @Override
            public void onResponse(Call<OTP_Response> call, Response<OTP_Response> response) {
                if (response.code() == 401){
                    invalidOTP();
                    progressDialog.hide();
                }
                else {
                    if(response.code() == 201) {
                        assert response.body() != null;
                        String deviceToken = response.body().getToken();
                        Boolean latest = response.body().getLatest();
                        String latests = String.valueOf(latest);
                        editor.putString("deviceToken", deviceToken);
                        editor.putString("latests", latests);
                        editor.commit();
                        final androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(OtpActivity.this);
                        LayoutInflater inflater1 = getLayoutInflater();
                        View dialogView1 = inflater1.inflate(R.layout.otp_success_layout,null);
                        builder1.setCancelable(false);
                        builder1.setView(dialogView1);
                        final androidx.appcompat.app.AlertDialog alertDialog1 = builder1.create();
                        alertDialog1.show();
                        alertDialog1.setCanceledOnTouchOutside(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Toast.makeText(OTPActivity.this, ""+latestLogin, Toast.LENGTH_SHORT).show();
                                Intent intent;
                                if (Objects.equals(latests, "true")) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("phone",phone);
                                    Intent intent1 = new Intent(OtpActivity.this,RegistrationActivity.class);
                                    intent1.putExtras(bundle);
                                    startActivity(intent1);
                                }else {
                                    intent = new Intent(OtpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                alertDialog1.dismiss();
                            }
                        },2000);
                        progressDialog.hide();

                    } else {
                        progressDialog.hide();
                        //progressDialog.hideProgressDialog();
                    }
                }
            }
            @Override
            public void onFailure(Call<OTP_Response> call, Throwable t) {
                progressDialog.hide();
                //progressDialog.hideProgressDialog();

            }
        });

    }

    private void invalidOTP() {
        final androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(OtpActivity.this);
        LayoutInflater inflater1 = getLayoutInflater();
        View dialogView1 = inflater1.inflate(R.layout.invalidotp_layout,null);
        builder1.setCancelable(false);
        builder1.setView(dialogView1);
        final androidx.appcompat.app.AlertDialog alertDialog1 = builder1.create();
        alertDialog1.show();
        alertDialog1.setCanceledOnTouchOutside(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog1.dismiss();
                otp1.setText("");
                otp2.setText("");
                otp3.setText("");
                otp4.setText("");
            }
        },5000);
    }

    private void initi() {
        btn_Otp_submit = findViewById(R.id.btn_Otp_submit);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        tv_phone = findViewById(R.id.tv_phone);
        count_time = findViewById(R.id.count_time);
        tv_verify = findViewById(R.id.tv_verify);
        btn_resend =findViewById(R.id.btn_resend);
        //loading
        progressDialog = new ProgressDialog(OtpActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        //sharedPrefrence
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        phone = loginPref.getString("phone", null);
        editor = loginPref.edit();
    }

    private void CountDown() {
        countDownTimer = new CountDownTimer(timerDuration, timerInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timerTextView with the remaining time
                count_time.setText( millisUntilFinished / 1000 + " Sec");
                btn_resend.setVisibility(View.GONE);
                tv_verify.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFinish() {
                // Timer finished, you can perform actions here
                btn_resend.setVisibility(View.VISIBLE);
                count_time.setVisibility(View.GONE);
                tv_verify.setVisibility(View.GONE);
                otp1.setText("");
                otp2.setText("");
                otp3.setText("");
                otp4.setText("");
            }
        };
        // Start the timer
        countDownTimer.start();
    }
}