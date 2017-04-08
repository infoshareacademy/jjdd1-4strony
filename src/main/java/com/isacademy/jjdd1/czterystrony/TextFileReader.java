package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

public class TextFileReader {
    private Path filePath;

    public TextFileReader(File file) {
        this.filePath = Paths.get(file.getAbsolutePath());
    }

    public List<String> getContentList() throws IOException {
        List<String> content = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        content.remove(0);
        return content;
    }

    public String getFileNameWithoutExtension() {
        return filePath.getFileName().toString().split("\\.")[0];
    }
}