package com.example.changskitchen.helpers;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String convertToMenuId(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String convertToWeekDay(String menuId) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(menuId);
        } catch (ParseException e) {
        }
        DateFormat dateFormat =new SimpleDateFormat("EEEE");
        return dateFormat.format(date);
    }

    public static String convertToDate(String menuId) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(menuId);
        } catch (ParseException e) {
        }
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }
}
