package com.example.utsdikiaulio.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.utsdikiaulio.R;
import com.example.utsdikiaulio.data.response.SearchResponse;
import com.example.utsdikiaulio.data.response.UserResponse;
import com.example.utsdikiaulio.data.retrofit.ApiConfig;
import com.example.utsdikiaulio.data.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = ApiConfig.getApiService();
        Call <SearchResponse> call = apiService.searchUser("Df");

        recyclerView = findViewById(R.id.RecycleviewMain);

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    List<UserResponse> users = response.body().getUsers();
                    reviewAdapter = new  ReviewAdapter(users);
                    recyclerView.setAdapter(reviewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }else {
                    Toast.makeText(MainActivity.this, "Gagal ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}