package com.titkov.konstantin;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<String> dateList1 = new ArrayList<>();
    private static final List<String> dateList2 = new ArrayList<>();


    public static void main(String[] args) throws DatesToCronConvertException {

        dateList1.add("2022-01-25T08:00:00");
        dateList1.add("2022-01-25T08:30:00");
        dateList1.add("2022-01-25T09:00:00");
        dateList1.add("2022-01-25T09:30:00");
        dateList1.add("2022-01-26T08:00:00");
        dateList1.add("2022-01-26T08:30:00");
        dateList1.add("2022-01-26T09:00:00");
        dateList1.add("2022-01-26T09:30:00");

        dateList2.add("2022-01-24T19:53:00");
        dateList2.add("2022-01-24T19:54:00");
        dateList2.add("2022-01-24T19:55:00");
        dateList2.add("2022-01-24T19:56:00");
        dateList2.add("2022-01-24T19:57:00");
        dateList2.add("2022-01-24T19:58:00");
        dateList2.add("2022-01-24T19:59:00");
        dateList2.add("2022-01-24T20:00:00");
        dateList2.add("2022-01-24T20:01:00");
        dateList2.add("2022-01-24T20:02:00");


        CronConverter cronConverter1 = new CronConverter();
        CronConverter cronConverter2 = new CronConverter();
        System.out.println(cronConverter1.getImplementationInfo() + "\n");

        System.out.println("Input 1 : \n");
        for (String date : dateList1) {
            System.out.println(date);
        }
        System.out.println("\nOutput 1 : \n");
        System.out.println(cronConverter1.convert(dateList1));

        System.out.println("\nInput 2 : \n");
        for (String date2 : dateList2) {
            System.out.println(date2);
        }

        System.out.println("\nOutput 2\n");
        System.out.println(cronConverter2.convert(dateList2));
    }

}
