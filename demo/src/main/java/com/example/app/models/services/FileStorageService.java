package com.example.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
  private Path fileStorageLocation;

  @Autowired
  public FileStorageService(@Value("${file.upload-dir}") Path fileStorageLocation) {
    this.fileStorageLocation = fileStorageLocation;
    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (IOException e) {
      throw new RuntimeException("Could not create upload directory!", e);
    }
  }

  public String storeFile(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      if (fileName.contains("..")) {
        throw new RuntimeException("Filename contains invalid path sequence " + fileName);
      }
      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      return targetLocation.toString();
    } catch (IOException e) {
      throw new RuntimeException("Could not store file " + fileName, e);
    }
  }
}
