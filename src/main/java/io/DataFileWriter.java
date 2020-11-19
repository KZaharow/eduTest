package io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataFileWriter {
    public void writeDataToFile(Path path, List list) throws IOException {
        Files.write(path, list, Charset.defaultCharset());
    }
}
