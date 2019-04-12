package com.example.retrofittesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> {

    private Context context;
    private List<Users> usersList;

    public UserDataAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = usersList.get(position);
        holder.tvId.setText(String.valueOf(users.getId()));
        holder.tvFirstName.setText(users.getFirstName());
        holder.tvLastName.setText(users.getLastName());
        holder.tvGender.setText(users.getGender());
        holder.tvEmail.setText(users.getEmail());
        Glide.with(context).load(users.getImageUrl()).apply(RequestOptions.circleCropTransform()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvId;
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvGender;
        TextView tvEmail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivProfileImage);
            tvId = itemView.findViewById(R.id.tvId);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvEmail = itemView.findViewById(R.id.tvEmail);

        }
    }
}
