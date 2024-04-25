package com.example.utsdikiaulio.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utsdikiaulio.R;
import com.example.utsdikiaulio.data.response.UserResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<UserResponse> users;

    public ReviewAdapter(List<UserResponse> users){
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserResponse user = users.get(position);
        holder.tvuser.setText(user.getUsername());
        Picasso.get().load(user.getfoto()).into(holder.foto);

        holder.itemView.setOnClickListener(click -> {
          Intent intent = new Intent(click.getContext(), Detail_user.class);
          intent.putExtra("name", user.getName());
          intent.putExtra("username", user.getUsername());
          intent.putExtra("foto", user.getfoto());
          intent.putExtra("bio", user.getBio());
          intent.putExtra("lokasi", user.getLocation());
          click.getContext().startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView tvuser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ivFoto);
            tvuser = itemView.findViewById(R.id.tvUser);
        }
    }
}
