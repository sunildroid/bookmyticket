package com.mock.bookmyticket.ui.home;

import com.mock.bookmyticket.data.DataManager;
import com.mock.bookmyticket.ui.base.MvpView;

/**
 * Created by sunilkuntal on 9/10/2017.
 */

public class MainPresenter implements MainMvpPresenter {

    MainMvpView mainMvpView;
    DataManager dataManager;

    MainPresenter(MainMvpView mainMvpView) {
        this.mainMvpView = mainMvpView;
        dataManager = DataManager.getInstance();
    }

    @Override
    public void onAttach(MvpView mvpView) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void handleApiError(Error error) {

    }


    @Override
    public void onDrawerOptionBooKClick() {
        mainMvpView.onDrawerOptionBookTicket();
    }

    @Override
    public void onDrawerOptionHistoryClick() {
        mainMvpView.onDrawerOptionViewHistory();
    }

    @Override
    public void onDrawerOptionContactClick() {
        mainMvpView.onDrawerOptionContact();
    }
}
