package com.titkov.konstantin;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class CronConverter implements DatesToCronConverterInterface {
    public static final Map<Integer, String> WEEK_DAYS = new HashMap<>();
    static {
        WEEK_DAYS.put(0, "MON");
        WEEK_DAYS.put(1, "MON");
        WEEK_DAYS.put(2, "MON");
        WEEK_DAYS.put(3, "MON");
        WEEK_DAYS.put(4, "MON");
        WEEK_DAYS.put(5, "MON");
        WEEK_DAYS.put(6, "MON");

    }
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


    public CronConverter() {
    }

    @Override
    public String convert(List<String> dates) throws DatesToCronConvertException {
        List<LocalDateTime> dateTimes = new ArrayList<>();
        try {
            for (String date : dates) {
                dateTimes.add(LocalDateTime.parse(date, formatter));
            }
        } catch (DateTimeParseException e) {
            throw new DatesToCronConvertException(e);
        }

        List<Integer> mins = dateTimes.stream()
                .map(Timestamp::valueOf)
                .map( s -> (int) s.getTime() / 1_000)
                .collect(Collectors.toList());
        Optional<Integer> minStep = detectStep(mins);

        Map<Integer, Integer> seconds = new HashMap<>();
        Map<Integer, Integer> minutes = new HashMap<>();
        Map<Integer, Integer> hours = new HashMap<>();
        Map<Integer, Integer> dayOfMonth = new HashMap<>();
        Map<Integer, Integer> dayOfWeek = new HashMap<>();

        for (LocalDateTime dateTime : dateTimes) {
            putCount(dayOfMonth, dateTime.getDayOfMonth());
            putCount(seconds, dateTime.getSecond());
            putCount(minutes, dateTime.getMinute());
            putCount(hours, dateTime.getHour());
            putCount(dayOfWeek, dateTime.getDayOfWeek().getValue());
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (minStep.isPresent() && minStep.get() == 60) {
            stringBuilder.append("0 * * * ");
        } else {
            stringBuilder.append(getRule(seconds));
            stringBuilder.append(" ");
            stringBuilder.append(getRule(minutes));
            stringBuilder.append(" ");
            stringBuilder.append(getRule(hours));
            stringBuilder.append(" ");
            stringBuilder.append("*");
            stringBuilder.append(" ");
            stringBuilder.append("*");
            stringBuilder.append(" ");
        }

        if (dayOfWeek.size() == 1) {
            stringBuilder.append(String.valueOf(dateTimes.get(0).getDayOfWeek()), 0, 3); // todo день недели
        }

        return stringBuilder.toString();
    }

    private String getRule(Map<Integer, Integer> map) throws DatesToCronConvertException {
        List<Integer> keys = map.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        if (keys.size() == 1) {
            return String.valueOf(keys.get(0));
        }

        Optional<Integer> r = detectStep(keys);

        if (r.isPresent()) {
            if (keys.get(0).equals(0)) {
                return String.format("0/%d", r.get());
            } else if (r.get() == 1) {
                return String.format("%d-%d", keys.get(0), keys.get(keys.size()-1));
            }
        }

        return "*";
    }

    private Optional<Integer> detectStep(List<Integer> keys) {
        int shift = keys.get(1) - keys.get(0);
        for (int i = 2; i < keys.size(); i++) {
            int currentShift = keys.get(i) - keys.get(i-1);
            if (currentShift != shift) {
                return Optional.empty();
            }
        }

        return Optional.of(shift);
    }

    private void putCount(Map<Integer, Integer> dayOfMonth, int value) {
        dayOfMonth.put(value, dayOfMonth.computeIfAbsent(value, k -> 0) + 1);
    }

    //TODO: добавить ссыдлку
    @Override
    public String getImplementationInfo() {
        return "Титков Константин Алексеевич \n"
                + getClass().getSimpleName() + " \n"
                + getClass().getPackage() + " \n"
                + "https://github.com/KronCosta/CronConverter";
    }
}
