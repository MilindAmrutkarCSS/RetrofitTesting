package com.example.retrofittesting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofittesting.interfaces.IUserDetails;
import com.example.retrofittesting.model.UserResponse;
import com.example.retrofittesting.rest.ApiClient;
import com.example.retrofittesting.R;
import com.example.retrofittesting.adapters.UserDataAdapter;
import com.example.retrofittesting.model.Users;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IUserDetails {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private UserDataAdapter userDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String token = "f7bb3445993c863b3ca02bfe0a4c4434463df8f6";

        Users users = new Users();
        users.setFirstName("Milind");
        users.setGender("Male");
        users.setId(1);

        ApiClient.getInstance().createPost(token, users).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                UserResponse userResponse = response.body();

                if(userResponse!=null && userResponse.getStatus().equalsIgnoreCase("created")) {
                    //Toast.makeText(MainActivity.this, userResponse.getStatus() , Toast.LENGTH_SHORT).show();

                    if(userResponse.getUsersList()!=null) {
                        List<Users> users = userResponse.getUsersList();
                        userDataAdapter = new UserDataAdapter(MainActivity.this, users);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(userDataAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "User response is null", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });


       /* ApiClient.getInstance().getUserList().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Users> users = response.body();
                userDataAdapter = new UserDataAdapter(MainActivity.this, users);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(userDataAdapter);
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });*/

    }

    @Override
    public void onItemClick(Users user) {
        //Toast.makeText(this, "Id: " +user.getId() + " Name: " + user.getFirstName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
