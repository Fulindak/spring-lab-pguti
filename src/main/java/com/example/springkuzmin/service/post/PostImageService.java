package com.example.springkuzmin.service.post;

import com.example.springkuzmin.dto.post.FileUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PostImageService {
    private final File imagesDir;
    @Autowired
    public PostImageService(File imagesDir) {
        this.imagesDir = imagesDir;
    }
    public byte[] download(String url){
        try {
            FileInputStream fileInputStream = new FileInputStream(url);
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public FileUploadResponse upload(MultipartFile file){
        try {
            return new FileUploadResponse(createFile(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String createFile(MultipartFile file) throws IOException {
        String filePath = imagesDir.getPath()
                + '/'
                + generateFileName(file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(filePath,true);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return filePath;
    }
    private String generateFileName(String fileName){
        int indexOfLastDot = fileName.lastIndexOf('.');
        String newName = fileName.substring(0,indexOfLastDot);
        return newName + new SimpleDateFormat("dd:MM:yyyy-hh:mm:ss").format(new Date()) + fileName.substring(indexOfLastDot);
    }
}