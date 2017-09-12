package com.mock.bookmyticket.util;

import com.mock.bookmyticket.ui.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;



public class FormattingUtils {
    private static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy- hh:mm a";
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
    private static final String TAG = FormattingUtils.class.getCanonicalName();

    public static String getFormattedDate(long dateTime) {
        Date date = new Date(dateTime);
        return sDateFormatter.format(date);
    }

    public static String getFormattedCost(double cost) {
        return Constants.CURRENCY_SYMBOL + cost;
    }
}
