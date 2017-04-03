package com.isacademy.jjdd1.czterystrony;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;;

public class RatingsReader {

    private final String SEPARATOR = ",";

    private final String DIRECTORY = "/home/jakubdobosz/Downloads/omegafun";

    private Path filePath;

    public RatingsReader(String investFundID) {
        this.filePath = Paths.get(DIRECTORY, investFundID);
    }

    public List<String> getContentList() throws IOException {
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }
}
