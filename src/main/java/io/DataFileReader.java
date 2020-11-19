package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataFileReader {

    public List<String> readReceiptFromFile(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
