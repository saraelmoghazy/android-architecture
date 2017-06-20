package com.example.android.architecture.blueprints.todoapp.data.source;

import com.example.android.architecture.blueprints.todoapp.Util;
import com.example.android.architecture.blueprints.todoapp.poc.data.PocDataSource;
import com.example.android.architecture.blueprints.todoapp.poc.data.PocRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 6/18/2017.
 */

public class PocsRepositoryTest {
    PocDataSource mPocRepository;
    @Mock
    PocDataSource mRemotePocRepository;

    @Mock
    PocDataSource mLocalPocRepository;

    @Mock
    PocDataSource.GetPocsCallBack getPocsCallBack;


    //Mockito API to capture argument value and do action or assertion on it.
    @Captor
    private ArgumentCaptor<PocDataSource.GetPocsCallBack> callBackCaptor;


    @Before
    public void setupPocRepo() {
        MockitoAnnotations.initMocks(this);
        mPocRepository = PocRepository.getInstance(mLocalPocRepository, mRemotePocRepository);
    }

    @After
    public void destroyRepositoryInstance() {
        PocRepository.destroyInstance();
    }


    @Test
    public void PocsCachesAfterFirstApiCall() {
        ((PocRepository) mPocRepository).setmCacheDirty(true);
        mPocRepository.getPocs(getPocsCallBack);
//        // capture call back
        verify(mRemotePocRepository).getPocs(callBackCaptor.capture());
        // invoke remote on Pocs loaded
        callBackCaptor.getValue().onPocsLoaded(Util.loadRemotePocs(getClass()));

        mPocRepository.getPocs(getPocsCallBack);
        // verify that local call back has been captured.
        verify(mLocalPocRepository).getPocs(callBackCaptor.capture());

    }

}
