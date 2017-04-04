package com.isacademy.jjdd1.czterystrony;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;;

public class FileReader {

    private final String SEPARATOR = ",";
    private final String DIRECTORY = "./data/omegafun";
    private Path filePath;

    public FileReader(String investFundID) {
        this.filePath = Paths.get(DIRECTORY, investFundID);
    }

    public List<String> getContentList() throws IOException {
        List<String> content = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        content.remove(0);
        return content;
    }
}
