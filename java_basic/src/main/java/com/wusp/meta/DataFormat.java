package com.wusp.meta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataFormat {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);
        Date strDate = calendar.getTime();
        DateFormat formatUpperCase = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("2019-08-31 to yyyy-MM-dd: " + formatUpperCase.format(strDate));
        //2019-08-31 to yyyy-MM-dd: 2019-12-31
        formatUpperCase = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("2019-08-31 to YYYY/MM/dd: " + formatUpperCase.format(strDate));
        //2019-08-31 to YYYY/MM/dd: 2020-12-31
    }
}
