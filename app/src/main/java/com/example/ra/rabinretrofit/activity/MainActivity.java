package com.example.ra.rabinretrofit.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.widget.TextView;

import com.example.ra.rabinretrofit.Adapters.PhotoAdapter;
import com.example.ra.rabinretrofit.MyInterface.APIInterface;
import com.example.ra.rabinretrofit.MyInterface.ApiClient;
import com.example.ra.rabinretrofit.R;
import com.example.ra.rabinretrofit.model.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView photoTextView;
    APIInterface apiInterface;
    private List<Photo> photoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(APIInterface.class);
//    photoTextView = (TextView) findViewById(R.id.photoTextView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new PhotoAdapter(this, photoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        preparePhotoData();

    }

    private void preparePhotoData() {
        retrofit2.Call<List<Photo>> call = apiInterface.getAllPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> photos = response.body();
                for (Photo aPhoto : photos
                        ) {
                    photoList.add(aPhoto);
                }


                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<List<Photo>> call, Throwable t) {


            }
        });
    }
}