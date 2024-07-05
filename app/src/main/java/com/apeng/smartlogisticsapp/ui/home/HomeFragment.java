package com.apeng.smartlogisticsapp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.apeng.smartlogisticsapp.activity.QRCodeScannerActivity;
import com.apeng.smartlogisticsapp.databinding.FragmentHomeBinding;
import com.apeng.smartlogisticsapp.service.OrderService;
import com.apeng.smartlogisticsapp.service.dto.Order;
import com.apeng.smartlogisticsapp.service.dto.OrderResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private static final int REQUEST_CODE = 51615;
    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(OrderService.SERVER_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    private static final OrderService ORDER_SERVICE = RETROFIT.create(OrderService.class);
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.scanButton.setOnClickListener(button -> {
            startActivityForResult(new Intent(getActivity(), QRCodeScannerActivity.class), REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            long orderId = data.getLongExtra(QRCodeScannerActivity.ORDER_ID_KEY, -1L);
            ORDER_SERVICE.getOrder(orderId).enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                    if (response.code() == 401) {
                        Toast.makeText(HomeFragment.this.getContext(), "未登录，请求失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.i(TAG, "onResponse: " + response);
                    OrderResponse orderResponse = response.body();
                    Order order = orderResponse.getOrder();
                    Log.i(TAG, "order: " + order);
                    binding.orderIdValue.setText(order.getId().toString());
                    binding.createTimeValue.setText(order.getCreateTime().toString());
                    binding.updateTimeValue.setText(order.getUpdateTime().toString());
                    binding.orderStateValue.setText(order.getState());
                    binding.targetWarehouseIdValue.setText(order.getTargetWarehouse() == null ? "null" : order.getTargetWarehouse().getId().toString());
                    binding.carIdValue.setText(order.getCar() == null ? "null" : order.getCar().getId().toString());
                    binding.shelveIdValue.setText(order.getShelve() == null ? "null" : order.getShelve().getId().toString());
                }

                @Override
                public void onFailure(Call<OrderResponse> call, Throwable throwable) {
                    Log.e(TAG, "Fail to request " + call.request().url() + throwable);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}