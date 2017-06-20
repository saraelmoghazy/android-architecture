package com.example.android.architecture.blueprints.todoapp.poc;

import com.example.android.architecture.blueprints.todoapp.BasePresenter;
import com.example.android.architecture.blueprints.todoapp.BaseView;
import com.example.android.architecture.blueprints.todoapp.poc.data.Poc;

import java.util.List;

/**
 * Created by user on 6/13/2017.
 */

public interface PocContract {


    public interface View extends BaseView<PocPresenter> {

        void showPocs(List<Poc> pocList);

        void showNoPocs();

        void showMessageError(String msgError);

        void showLoadingIndicator(boolean isShown);
    }


    public interface Presenter extends BasePresenter {
        void getPocs(boolean isRefresh);
    }

}
