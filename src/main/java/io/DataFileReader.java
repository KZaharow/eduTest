package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Class is simple data reader for read *txt data base files like
 * resources/priceList.txt
 * resources/discountCards.txt and
 * resources/vat.txt
 * file path is "hardocored" but of course can be
 * specified in the external config.properties
 */
public class DataFileReader {

    public List<String> readReceiptFromFile(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
