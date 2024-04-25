package com.example.utsdikiaulio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String username = bundle.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<UserResponse> userCall = apiService.getUser(username);

            TextView textView = findViewById(R.id.tvName);
            TextView textView2 = findViewById(R.id.tvUsername);
            TextView textView3 = findViewById(R.id.tvAboutme);
            ImageView imageView = findViewById(R.id.foto);


            userCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {

                        UserResponse user = response.body();
                        if (user != null) {
                            String name = "Name: " + user.getName();
                            String usernames = "Username: " + user.getUsername();
                            String bio = "Bio: " + user.getBio();
                            String gambar = user.getfoto();

                            textView.setText(name);
                            textView2.setText(usernames);
                            textView3.setText(bio);
                            Picasso.get().load(gambar).into(imageView);
                        } else {
                            Toast.makeText(Detail_user.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
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

}