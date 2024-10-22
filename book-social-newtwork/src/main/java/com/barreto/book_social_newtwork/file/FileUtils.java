package com.barreto.book_social_newtwork.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class FileUtils {


    public static byte[] readFileFromLocation(String fileUrl) {

        if (fileUrl.isBlank()) {
            return null;
        }

        try {

            Path filePath = new File(fileUrl).toPath();
            return Files.readAllBytes(filePath);
        } catch (IOException exception) {
            log.warn("No file found in the path: " + fileUrl);
        }

        return null;
    }
}
