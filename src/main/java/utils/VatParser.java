package utils;

import exceptions.VatException;
import models.vat.Vat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class uses for define Vat data from the file data by separator
 */
public class VatParser {
    private final String SP = ";";

    /**
     *
     * @param vatListFromFile - data from read collection
     * @return Map<String, Vat> vatMap = new HashMap<>();
     * @throws VatException
     */
    public Map getVatMap(List<String> vatListFromFile) throws VatException {

        Map<String, Vat> vatMap = new HashMap<>();
        for (String s : vatListFromFile) {
            String[] arr = s.split(SP);
            for (String param : arr) {
                if (param.isEmpty()) {
                    throw new VatException("vat list has empty or null value");
                }
            }
            double perc = Double.parseDouble(arr[1]);

            // define vat from the string
            Vat vat = new Vat(arr[0], perc, 0);

            // put article data to the priceList list data base (Map)
            vatMap.put(arr[0], vat);
        }
        return vatMap;
    }
}
