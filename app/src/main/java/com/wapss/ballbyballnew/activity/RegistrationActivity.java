package com.wapss.ballbyballnew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.widget.Toast;

import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.apiService.ApiService;
import com.wapss.ballbyballnew.response.RegistrationResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    ImageView iv_dob;
    EditText edit_name, edit_email, et_DOB;
    private Calendar calendar;
    CardView btn_card_registration;
    String name,email,dob,deviceToken;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initi();
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));

        calendar = Calendar.getInstance();
        iv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegistrationActivity.this,
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

        btn_card_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name = edit_name.getText().toString();
                 email = edit_email.getText().toString();
                 dob = et_DOB.getText().toString();
                callRegistration(name,email,dob);
            }
        });
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
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);

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

    private void initi() {
        iv_dob = findViewById(R.id.iv_dob);
        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        et_DOB = findViewById(R.id.et_DOB);
        btn_card_registration = findViewById(R.id.btn_card_registration);
        //shared Pref
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        deviceToken = loginPref.getString("deviceToken", null);
        //loading
        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
    }
}