package com.apeng.smartlogisticsapp.service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    String LOCAL_URL = "http://10.0.2.2:8081/"; // 10.0.2.2 为模拟器运行环境地址（比如运行模拟器的电脑）
    String SERVER_URL = "http://dazahui.chat:8081/";

    @POST("login")
    Call<ResponseBody> login(@Body RequestBody body);

}
