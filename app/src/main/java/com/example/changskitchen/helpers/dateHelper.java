package com.example.changskitchen.helpers;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateHelper {

    public static String convertToMenuId(Date date) {
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(menuId);
        } catch (ParseException e) {
        }
        DateFormat dateFormat =new SimpleDateFormat("EEEE");
        return dateFormat.format(date);
    }

    public static String convertToWeekDay(String menuId) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(menuId);
        } catch (ParseException e) {
        }
        DateFormat dateFormat =new SimpleDateFormat("EEEE");
        return dateFormat.format(date);
    }

    public static String convertToDate(String menuId) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(menuId);
        } catch (ParseException e) {
        }
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }
}
