package com.example.android.architecture.blueprints.todoapp.pocs;

import com.example.android.architecture.blueprints.todoapp.Util;
import com.example.android.architecture.blueprints.todoapp.poc.PocContract;
import com.example.android.architecture.blueprints.todoapp.poc.PocPresenter;
import com.example.android.architecture.blueprints.todoapp.poc.data.Poc;
import com.example.android.architecture.blueprints.todoapp.poc.data.PocDataSource;
import com.example.android.architecture.blueprints.todoapp.poc.data.PocRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 6/18/2017.
 */

public class PocsPresenterTest {

    @Mock
    PocRepository pocRepository;

    @Mock
    PocContract.View pocView;

    @Captor
    ArgumentCaptor<PocDataSource.GetPocsCallBack> pocCallBack;

    private PocPresenter pocPresenter;


    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        pocPresenter = new PocPresenter(pocRepository, pocView);
    }

    @Test
    public void setPocPresenter() {
        verify(pocView).setPresenter(pocPresenter);
    }


    @Test
    public void loadPocsDataFromRepositoryAndPutIntoView() {
        pocPresenter.getPocs(true);
        verify(pocRepository).getPocs(pocCallBack.capture());
        pocCallBack.getValue().onPocsLoaded(Util.loadRemotePocs(getClass()));
        InOrder inOrder = inOrder(pocView);
        inOrder.verify(pocView).showLoadingIndicator(true);
        inOrder.verify(pocView).showLoadingIndicator(false);
        ArgumentCaptor<List> showPocsCaptor = ArgumentCaptor.forClass(List.class);
        // capture call back
        verify(pocView).showPocs(showPocsCaptor.capture());
        assertTrue(showPocsCaptor.getValue().size() < 6);
    }

    @Test
    public void unavailablePocs() {
        pocPresenter.getPocs(true);
        verify(pocRepository).getPocs(pocCallBack.capture());
        pocCallBack.getValue().onPocsLoaded(new ArrayList<Poc>());
        InOrder inOrder = inOrder(pocView);
        inOrder.verify(pocView).showLoadingIndicator(true);
        inOrder.verify(pocView).showLoadingIndicator(false);
        verify(pocView).showNoPocs();
    }


    @Test
    public void showErrorMessage() {
        pocPresenter.getPocs(true);
        verify(pocRepository).getPocs(pocCallBack.capture());
        pocCallBack.getValue().onPocsFailed("No Internet Connection");
        InOrder inOrder = inOrder(pocView);
        inOrder.verify(pocView).showLoadingIndicator(true);
        inOrder.verify(pocView).showLoadingIndicator(false);
        verify(pocView).showMessageError("No Internet Connection");
    }
}
