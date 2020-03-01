//package com.company;

import java.text.SimpleDateFormat;
import java.util.*;

public class MagicDay {
    public static int numberOfDays(int century) {
        int count = 0;
        java.time.LocalDate it = java.time.LocalDate.of( (century-1)*100 , 1, 1);
        java.time.LocalDate end = java.time.LocalDate.of( century*100 , 1, 1);
        java.time.format.DateTimeFormatter formatter2 = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd");
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy");
        for (; it.compareTo(end) < 0; it = it.plusDays( 1 )) {
            String date = it.format(formatter);
            String date2 = it.format(formatter2);
            if (date.equals(new StringBuilder(date).reverse().toString()) &&
                    date2.equals(new StringBuilder(date2).reverse().toString())) {
                count++;
                //System.out.println(date + " " + date2);
            }
        }
        return count;
    }
}