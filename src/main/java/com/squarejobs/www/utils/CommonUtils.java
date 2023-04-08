package com.squarejobs.www.utils;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class CommonUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static byte[] convertMultipartFileToBytes(MultipartFile file) throws IOException {
        return file.getBytes();
    }

    // date YY-MM-DD  HH-MM-SS
    public static Date returnCurrentTime () {
        FastDateFormat formatter = FastDateFormat.getInstance(DATE_FORMAT);

        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = formatter.format(currentTime);
        try {
            return formatter.parse(formattedTime);
        } catch (ParseException e) {
            return null;
        }
    }

    public static LocalDateTime getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(formatter);
        LocalDateTime parsedTime = LocalDateTime.parse(formattedTime, formatter);
        return parsedTime;
    }
}

