package com.teamtreehouse.blog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by scott on 4/18/2017.
 */
public class DateUtil {

    public static String getTodaysDate(){
        //January 31, 2016 at 1:00
        SimpleDateFormat dateformat = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm");
        Date date = new Date();
        String datestring = dateformat.format(date);
        return datestring;
    }
}
