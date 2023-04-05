package com.example.springkuzmin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;

@PropertySource("classpath:file_config.properties")
@Slf4j
@Configuration
public class FileConfig {
    @Value(value = "${images.path.storeDir}")
    private String filesDirPath;
    @Bean
    public File imagesDir() {
        File file = new File(filesDirPath+"/images/");
        file.mkdir();
        return file;
    }
}