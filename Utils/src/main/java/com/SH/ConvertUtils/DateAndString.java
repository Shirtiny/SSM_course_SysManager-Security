package com.SH.ConvertUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndString {

    public static String DateToString(Date date,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);

        return sdf.format(date);
    }



}
