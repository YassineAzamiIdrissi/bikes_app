package com.yassine.practice.FilesHandling;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Service
@Slf4j
public class FileStorageService {
    @Value("${application.file.uploads.output-photos-folder}")
    private String absolutePath;

    public String saveFile(@NotNull MultipartFile file,
                           @NotNull Integer userId) {
        String subPath = "users" + File.separator + userId;
        return uploadFile(file, subPath);
    }

    private String uploadFile(@NotNull MultipartFile file,
                              @NotNull String subPath) {
        final String finalPath = absolutePath + separator + subPath;
        File folder = new File(finalPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                log.warn("Could not create folder " + finalPath);
                return null;
            }
        }
        String extension = getFileExtension(file.getOriginalFilename());
        String targetFilePath = finalPath + separator +
                System.currentTimeMillis() + "." + extension;
        Path path = Paths.get(targetFilePath);
        try {
            Files.write(path, file.getBytes());
            return targetFilePath;
        } catch (IOException exp) {
            log.error("Could not save file catch(uploadFile)");
            log.error(exp.getMessage());
        }
        return null;
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastIndexOfDot = filename.lastIndexOf(".");
        if (lastIndexOfDot == -1) {
            return "";
        }
        return filename.substring(lastIndexOfDot + 1);
    }
}
