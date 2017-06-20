package com.example.android.architecture.blueprints.todoapp.poc.data.remote;

import com.example.android.architecture.blueprints.todoapp.poc.data.Poc;
import com.example.android.architecture.blueprints.todoapp.poc.data.PocDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 6/14/2017.
 */

public class RemotePocRepo implements PocDataSource {
    private static RemotePocRepo instance;


    private RemotePocRepo() {
    }

    public static RemotePocRepo getInstance() {
        if (instance == null)
            instance = new RemotePocRepo();
        return instance;
    }


    @Override
    public void getPocs(final PocDataSource.GetPocsCallBack callBack) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Poc>> call = apiService.getPocs();
        call.enqueue(new Callback<List<Poc>>() {
            @Override
            public void onResponse(Call<List<Poc>> call, Response<List<Poc>> response) {
                if (response.isSuccessful()) {
                    List<Poc> pocs = response.body();
                    callBack.onPocsLoaded(pocs);
                } else {
                    ApiError error = ApiErrorUtil.parseError(response);
                    callBack.onPocsFailed(error.message());
                }

            }

            @Override
            public void onFailure(Call<List<Poc>> call, Throwable t) {
                callBack.onPocsFailed(t.getMessage());

            }
        });
    }
}
