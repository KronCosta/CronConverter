package com.titkov.konstantin;


import java.util.List;

public interface DatesToCronConverterInterface  {
    public final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    String convert(List<String> dates) throws DatesToCronConvertException;

    String getImplementationInfo();

}
