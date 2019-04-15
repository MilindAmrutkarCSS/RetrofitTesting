package com.example.retrofittesting.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofittesting.R;
import com.example.retrofittesting.model.Users;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity {
    Users user;
    @BindView(R.id.ivProfileImage)
    ImageView ivProfileImage;
    @BindView(R.id.tvFirstName)
    TextView tvFirstName;
    @BindView(R.id.tvLastName)
    TextView tvLastName;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            user = getIntent().getParcelableExtra("user");
            Glide.with(this).load(user.getImageUrl()).apply(RequestOptions.circleCropTransform()).into(ivProfileImage);
            tvFirstName.setText(user.getFirstName());
            tvLastName.setText(user.getLastName());
            tvGender.setText(user.getGender());
            tvEmail.setText(user.getEmail());
        }


    }
}
