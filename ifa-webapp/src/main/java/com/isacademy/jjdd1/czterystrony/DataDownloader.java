package com.isacademy.jjdd1.czterystrony;

import javax.ejb.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Singleton
public class DataDownloader {
    private static final String FUNDS_RATINGS_ZIP_URL = "http://bossa.pl/pub/fundinwest/omega/omegafun.zip";
    private static final Path TMP_PATH = Paths.get(System.getProperty("java.io.tmpdir")).resolve("4analysis");
    private static final int BUFFER_SIZE = 4096;

    public void download() throws IOException {
        URL url = new URL(FUNDS_RATINGS_ZIP_URL);
        URLConnection urlConnection = url.openConnection();
        InputStream stream = urlConnection.getInputStream();
        ZipInputStream zipInputStream = new ZipInputStream(stream);

        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            Path path = TMP_PATH.resolve(entry.getName());
            OutputStream outputStream = Files.newOutputStream(path);
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = stream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            outputStream.close();
        }
    }
}
