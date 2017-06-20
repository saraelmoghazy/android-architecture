package com.example.android.architecture.blueprints.todoapp.poc;

import com.example.android.architecture.blueprints.todoapp.poc.data.Poc;
import com.example.android.architecture.blueprints.todoapp.poc.data.PocDataSource;
import com.example.android.architecture.blueprints.todoapp.poc.data.PocRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by user on 6/13/2017.
 */

public class PocPresenter implements PocContract.Presenter {

    private final PocRepository mPocRepository;
    private final PocContract.View mPocView;
    private boolean isFirstLoad = true;


    @Override
    public void getPocs(boolean forceUpdate) {
        mPocView.showLoadingIndicator(true);

        // force update when refresh or first load
        if (forceUpdate || isFirstLoad) {
            mPocRepository.refreshPocs();
            isFirstLoad = false;
        }

        mPocRepository.getPocs(new PocDataSource.GetPocsCallBack() {
            @Override
            public void onPocsLoaded(List<Poc> pocList) {
                mPocView.showLoadingIndicator(false);
                if (pocList == null || pocList.size() < 1)
                    mPocView.showNoPocs();
                else {
                    mPocView.showPocs(pocList);
                }
            }

            @Override
            public void onPocsFailed(String errMsg) {
                mPocView.showLoadingIndicator(false);
                mPocView.showMessageError(errMsg);
            }
        });


    }


    @Override
    public void start() {
        getPocs(false);

    }


    public PocPresenter(PocRepository mPocRepository, PocContract.View mPocView) {
        this.mPocRepository = checkNotNull(mPocRepository, "mPocRepository cannot be null");
        this.mPocView = checkNotNull(mPocView, "mPocView can't be null");
        mPocView.setPresenter(this);
    }


}
