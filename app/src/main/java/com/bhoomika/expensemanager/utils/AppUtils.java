package com.bhoomika.expensemanager.utils;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bhoomika on 25/1/17.
 */

public class AppUtils {

    public static void showToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static void showValidation(Activity context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static String getDate(long milliSeconds) {// Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
        //  formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Create a calendar object that will convert the date and time value in millisecondsto date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static Date stringToDate(String date) {
        String dateString = date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        try {
            date1 = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }


}
