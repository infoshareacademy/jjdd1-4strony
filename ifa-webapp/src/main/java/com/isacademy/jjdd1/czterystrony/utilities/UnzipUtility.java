package com.isacademy.jjdd1.czterystrony.utilities;

import javax.ejb.Stateless;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Stateless
public class UnzipUtility {

    private static final int BUFFER_SIZE = 4096;

    public void unzip(Path zipFile, Path destinationDir) throws IOException {
        Files.createDirectories(destinationDir);
        ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile));
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            Path filePath = destinationDir.resolve(entry.getName());
            if (entry.isDirectory()) {
                Files.createDirectories(filePath);
            } else {
                extractFile(zipInputStream, filePath);
            }
        }
    }

    private void extractFile(ZipInputStream zipInputStream, Path filePath) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipInputStream.read(bytesIn)) != -1) {
            outputStream.write(bytesIn, 0, read);
        }
        outputStream.close();
    }
}
