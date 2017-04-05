package com.isacademy.jjdd1.czterystrony;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;;

public class TxtFileReader {
    private Path filePath;

    public TxtFileReader(String directory, String dataFileName) {
        this.filePath = Paths.get(directory, dataFileName + ".txt");
    }

    public List<String> getContentList() throws IOException {
        List<String> content = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        content.remove(0);
        return content;
    }
}