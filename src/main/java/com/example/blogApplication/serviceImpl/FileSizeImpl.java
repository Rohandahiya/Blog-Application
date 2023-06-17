package com.example.blogApplication.serviceImpl;

import com.example.blogApplication.services.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSizeImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException, URISyntaxException {

        //String finalPath = path + File.separator + file.getOriginalFilename();
        ClassPathResource resource = new ClassPathResource("static");
        URL resourceUrl = resource.getURL();
        Path resourcePath = Paths.get(resourceUrl.toURI());
        File folder = resourcePath.toFile();

        // Use the folder as needed
        String finalDir = folder.getAbsolutePath();

        String finalPath = finalDir + file.getOriginalFilename();

        InputStream inputStream = file.getInputStream();
//        File f = new File(path);
//        if(!f.exists()){
//            f.mkdir();
//        }

        // This statement is for file upload
        Files.copy(inputStream, Path.of(finalPath));

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/image").path(file.getOriginalFilename()).toString();
    }

    @Override
    public InputStream getImage(String path, String filename) throws FileNotFoundException {

        String finalPath = path + File.separator + filename;

        InputStream inputStream = new FileInputStream(finalPath);

        return inputStream;
    }
}
