package com.yassine.practice.FilesHandling;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtils {
    public static byte[] readFileBytes(String location) {
        if (StringUtils.isEmpty(location)) {
            return null;
        }
        try {
            Path path = new File(location).toPath();
            return Files.readAllBytes(path);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
