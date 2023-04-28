package com.project.wineshop.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageReader {

    public static byte[] getImageBytes(String url) {
        File file = new File(url);
        byte[] imageBytes = new byte[(int) file.length()];
        int bytesRead = 0;
        try (FileInputStream fis = new FileInputStream(file)) {
            bytesRead = fis.read(imageBytes);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            // або можна використати логування помилок, наприклад, через log4j або slf4j
        }
        if(bytesRead > 0) {
            System.out.println("ProductController.getImageBytes(" + url + "): " + bytesRead + " bytes were read.");
        } else if(bytesRead == -1) {
            System.out.println("During reading data from file " + url + " the end of the file has been reached.");
        }
        return imageBytes;
    }

}
