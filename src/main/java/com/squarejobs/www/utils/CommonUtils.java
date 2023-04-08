package com.squarejobs.www.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static byte[] convertMultipartFileToBytes(MultipartFile file) throws IOException {
        return file.getBytes();
    }

    // date YY-MM-DD'T'HH-MM-SS
    public static LocalDateTime getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(formatter);
        LocalDateTime parsedTime = LocalDateTime.parse(formattedTime, formatter);
        return parsedTime;
    }
}

