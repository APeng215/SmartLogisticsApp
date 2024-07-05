package com.apeng.smartlogisticsapp.service;

import com.apeng.smartlogisticsapp.service.dto.OrderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService {

    String LOCAL_URL = "http://10.0.2.2:8081/"; // 10.0.2.2 为模拟器运行环境地址（比如运行模拟器的电脑）
    String SERVER_URL = "http://dazahui.chat:8081/";

    @GET("order/search/id/{id}")
    Call<OrderResponse> getOrder(@Path("id") long orderId);

}
