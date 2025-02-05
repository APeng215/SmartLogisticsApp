package com.apeng.smartlogisticsapp.ui.notifications;

import static com.apeng.smartlogisticsapp.RetrofitInitializer.RETROFIT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.apeng.smartlogisticsapp.databinding.FragmentNotificationsBinding;
import com.apeng.smartlogisticsapp.service.LoginService;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private final LoginService LOGIN_SERVICE = RETROFIT.create(LoginService.class);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.et1.getText().toString();
                String password = binding.et2.getText().toString();

                // 访问后端
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("username", name)
                        .addFormDataPart("password", password)
                        .build();
                LOGIN_SERVICE.login(requestBody).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 204) {
                            Toast.makeText(NotificationsFragment.this.getContext(), "登陆成功", Toast.LENGTH_SHORT).show();

                        } else if (response.code() == 401) {
                            Toast.makeText(NotificationsFragment.this.getContext(), "认证失败", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                        Toast.makeText(NotificationsFragment.this.getContext(), "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                });
                //

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}