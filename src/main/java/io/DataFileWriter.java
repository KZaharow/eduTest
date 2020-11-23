package io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * This class uses for store txt receipt data to the specified file
 * file path is "hardocored" but of course can be
 * specified in the external config.properties
 */
public class DataFileWriter {
    public void writeDataToFile(Path path, List list) throws IOException {
        Files.write(path, list, Charset.defaultCharset());
    }
}
