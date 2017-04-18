package com.isacademy.jjdd1.czterystrony.utilities;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class TextFileReader {
    private Path filePath;

    public TextFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public List<String> getContent() {
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}