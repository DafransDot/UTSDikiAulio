package com.example.utsdikiaulio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utsdikiaulio.R;
import com.example.utsdikiaulio.data.response.UserResponse;
import com.example.utsdikiaulio.data.retrofit.ApiConfig;
import com.example.utsdikiaulio.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_user extends AppCompatActivity {
    private ProgressBar loding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String username = bundle.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<UserResponse> userCall = apiService.getUser(username);

            loding = findViewById(R.id.loding);
            TextView tvnama = findViewById(R.id.tvName);
            TextView tvusername = findViewById(R.id.tvUsername);
            TextView tvAboutme = findViewById(R.id.tvAboutme);
            TextView tvLocation = findViewById(R.id.tvlocation);
            ImageView foto = findViewById(R.id.foto);

            showLoading(true);
            userCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        UserResponse user = response.body();
                        if (user != null) {
                            showLoading(false);
                            tvnama.setText(user.getName());
                            tvusername.setText(user.getUsername());
                            tvAboutme.setText(user.getBio());
                            tvLocation.setText(user.getLocation());
                            Picasso.get().load(user.getfoto()).into(foto);
                        } else {
                            Toast.makeText(Detail_user.this, "Gagal!!!, Data tidak ada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(Detail_user.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            loding.setVisibility(View.VISIBLE);
        } else {
            loding.setVisibility(View.GONE);
        }
    }

}