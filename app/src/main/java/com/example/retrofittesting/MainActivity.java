package com.example.retrofittesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IUserDetails{


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



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Users>> call = jsonPlaceHolderApi.getUserList();
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<Users>>() {
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

                /*for (Users user: users) {
                    String content = "";
                    content += "ID: " + user.getId() + "\n";
                    content += "First Name: " + user.getFirstName() + "\n";
                    content += "Last Name: " + user.getLastName() + "\n";
                    content += "Email Id: " + user.getEmail() + "\n";
                    content += "Gender: " + user.getGender() + "\n";
                    content += "ImageUrl: " + user.getImageUrl() + "\n\n\n";

                    //textViewResult.append(content);
                }*/
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(Users user) {
        //Toast.makeText(this, "Id: " +user.getId() + " Name: " + user.getFirstName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
