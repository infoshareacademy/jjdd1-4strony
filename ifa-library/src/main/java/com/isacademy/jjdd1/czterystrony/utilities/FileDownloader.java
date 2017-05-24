package com.isacademy.jjdd1.czterystrony.utilities;

import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Stateless
public class FileDownloader {

    public void download(String fileSource, Path destinationPath) throws IOException {
        URL url = new URL(fileSource);
        try (InputStream inputStream = url.openStream()) {
            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
