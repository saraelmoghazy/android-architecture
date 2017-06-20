package com.example.android.architecture.blueprints.todoapp.poc.data;

import java.util.List;

/**
 * Created by user on 6/13/2017.
 */

public interface PocDataSource {


    interface GetPocsCallBack {
        void onPocsLoaded(List<Poc> pocList);

        void onPocsFailed(String errMsg);
    }

    void getPocs(GetPocsCallBack getPocsCallBack);


}
