package com.wapss.ballbyballnew.apiService;

import com.wapss.ballbyballnew.response.GetProfile;
import com.wapss.ballbyballnew.response.LoginResponse;
import com.wapss.ballbyballnew.response.OTP_Response;
import com.wapss.ballbyballnew.response.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiHolder {
    @POST("auth/mobile/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("loginId") String loginId,
                              @Field("deviceId") String deviceId);

    @POST("auth/verify")
    @FormUrlEncoded
    Call<OTP_Response> OTP_Verify(@Field("loginId") String loginId,
                                  @Field("otp") String otp);

    @PATCH("account")
    @FormUrlEncoded
    Call<RegistrationResponse> Registration(@Header("Authorization") String Token,
                                          @Field("name") String name,
                                          @Field("email") String email,
                                          @Field("dob") String address);

    @GET("account")
    Call<GetProfile> getProfile(@Header("Authorization") String token);
}
