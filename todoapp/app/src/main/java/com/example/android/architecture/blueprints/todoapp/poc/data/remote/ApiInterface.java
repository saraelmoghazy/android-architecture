package com.example.android.architecture.blueprints.todoapp.poc.data.remote;

import com.example.android.architecture.blueprints.todoapp.poc.data.Poc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 6/14/2017.
 */

public interface ApiInterface {

    @GET("todos")
    public Call<List<Poc>> getPocs();


}
