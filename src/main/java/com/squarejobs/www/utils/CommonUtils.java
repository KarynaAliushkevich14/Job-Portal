package com.squarejobs.www.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CommonUtils {

    public static byte[] convertMultipartFileToBytes(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
