package com.titkov.konstantin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CronConverter implements DatesToCronConverterInterface {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


    public CronConverter(List<String> dateList) {
    }

    @Override
    public String convert(List<String> dates) {
        List<LocalDateTime> dateTimes = new ArrayList<>();
        Map<String, Integer> seconds = new HashMap<>(60);
        Map<String, Integer> minutes = new HashMap<>(60);
        Map<String, Integer> hours = new HashMap<>(24);
        Map<String, Integer> dayOfMonth = new HashMap<>(31);
        Map<String, Integer> dayOfWeek = new HashMap<>(7);


        for (String date : dates) {
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            dateTimes.add(localDateTime);
        }
        for (int i = 0; i < dateTimes.size(); i++) {
            if (i + 1 < dateTimes.size()) {

                String day = String.valueOf(dateTimes.get(i).getDayOfMonth());
                if (dayOfMonth.containsKey(day)) {
                    dayOfMonth.put(day, dayOfMonth.get(day) + 1);
                } else {
                    dayOfMonth.put(day, 1);
                }


                String sec = String.valueOf(dateTimes.get(i).getSecond());
                if (seconds.containsKey(sec)) {
                    seconds.put(sec, seconds.get(sec) + 1);
                } else {
                    seconds.put(sec, 1);
                }


                String min = String.valueOf(dateTimes.get(i).getMinute());
                if (minutes.containsKey(min)) {
                    minutes.put(min, minutes.get(min) + 1);
                } else {
                    minutes.put(min, 1);
                }


                String hour = String.valueOf(dateTimes.get(i).getHour());
                if (hours.containsKey(hour)) {
                    hours.put(hour, hours.get(hour) + 1);
                } else {
                    hours.put(hour, 1);
                }


                String dayOW = String.valueOf(dateTimes.get(i).getDayOfWeek());
                if (dayOfWeek.containsKey(dayOW)) {
                    dayOfWeek.put(dayOW, dayOfWeek.get(dayOW) + 1);
                } else {
                    dayOfWeek.put(dayOW, 1);
                }
            }
        }

        System.out.println(dayOfMonth);
        System.out.println(seconds);
        System.out.println(minutes);
        System.out.println(hours);
        System.out.println(dayOfWeek);

////TODO: вернуть cron удовлетворяющий 51% дат из списка
        return null;
    }

    //TODO: добавить ссыдлку
    @Override
    public String getImplementationInfo() {
        return "Титков Константин Алексеевич \n"
                + getClass().getSimpleName() + " \n"
                + getClass().getPackage() + " \n"
                + "HREF";
    }

//    private String cronBuilder(String date) {
//        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
//        StringBuilder cron = new StringBuilder();
//        int seconds = localDateTime.getSecond();
//        int minutes = localDateTime.getMinute();
//        int hour = localDateTime.getHour();
//        int dayOfMonth = localDateTime.getDayOfMonth();
//        String month = String.valueOf(localDateTime.getMonthValue());
//        String dayOfWeek = String.valueOf(localDateTime.getDayOfWeek());
//        cron.append(seconds).append(" ")
//                .append(minutes).append(" ")
//                .append(hour).append(" ")
//                .append(dayOfMonth).append(" ")
//                .append(month).append(" ")
//                .append(dayOfWeek, 0, 3);
//
//        return cron.toString();
//    }
//

}
