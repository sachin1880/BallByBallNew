package com.wapss.ballbyballnew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.apiService.ApiService;
import com.wapss.ballbyballnew.response.GetProfile;
import com.wapss.ballbyballnew.response.RegistrationResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update_Profile extends AppCompatActivity {
    ImageView back;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    String deviceToken;
    EditText edit_name,et_email,et_DOB;
    ImageView iv_dob;
    private Calendar calendar;
    TextView tv_update;
    String name,email,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        back = findViewById(R.id.back);
        edit_name = findViewById(R.id.edit_name);
        et_email = findViewById(R.id.et_email);
        et_DOB = findViewById(R.id.et_DOB);
        iv_dob = findViewById(R.id.iv_dob);
        tv_update = findViewById(R.id.tv_update);
        //shared Pref
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        deviceToken = loginPref.getString("deviceToken", null);
        //loading
        progressDialog = new ProgressDialog(Update_Profile.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        calendar = Calendar.getInstance();
        iv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Update_Profile.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                et_DOB.setText(dateFormat1.format(calendar.getTime()));
                                // dob = dateFormat1.format(calendar.getTime());
                            }
                        }, year-18, month, dayOfMonth);
                calendar.set(year-18,month,dayOfMonth);
                long value=calendar.getTimeInMillis();

                datePickerDialog.getDatePicker().setMaxDate(value);
                datePickerDialog.show();
            }
        });

        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edit_name.getText().toString();
                email = et_email.getText().toString();
                dob = et_DOB.getText().toString();
                callRegistration(name,email,dob);
            }
        });

        callProfileAPI();
    }

    private void callRegistration(String name, String email, String dob) {
        progressDialog.show();
        String Token = "Bearer " + deviceToken;
        Call<RegistrationResponse> login_apiCall = ApiService.apiHolders().Registration(Token, name,email,dob);
        login_apiCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
//                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
//                    startActivity(intent);
                    callProfileAPI();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
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
                    edit_name.setText(response.body().getName());
                    et_email.setText(response.body().getEmail());
                    et_DOB.setText(response.body().getDob());
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Update_Profile.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetProfile> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Update_Profile.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}