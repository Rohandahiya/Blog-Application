package com.example.blogApplication.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;


@Service
public interface FileService {

    String uploadImage(String path, MultipartFile file) throws IOException, URISyntaxException;

    InputStream getImage(String path,String filename) throws FileNotFoundException;
}
