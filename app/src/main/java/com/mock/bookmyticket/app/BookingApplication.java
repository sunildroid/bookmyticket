package com.mock.bookmyticket.app;

import android.app.Application;

import com.mock.bookmyticket.data.DataManager;
import com.mock.bookmyticket.ui.Constants;

/**
 * Base Application Class
 */
public class BookingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.getInstance().setDataSource(Constants.DATA_SOURCE_TYPE.SOURCE_TYPE_LOCAL);
    }
}
