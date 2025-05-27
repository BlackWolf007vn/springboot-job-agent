package com.example.agent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
public class FileTransferController {

    private static final String STORAGE_DIR = "uploads";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File dir = new File(STORAGE_DIR);
        if (!dir.exists()) dir.mkdirs();

        File outputFile = new File(dir, file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(file.getBytes());
        }
        return ResponseEntity.ok("File uploaded: " + outputFile.getAbsolutePath());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        Path path = Paths.get(STORAGE_DIR, fileName);
        byte[] fileBytes = Files.readAllBytes(path);
        return ResponseEntity.ok().body(fileBytes);
    }
}
