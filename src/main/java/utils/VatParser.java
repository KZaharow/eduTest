package utils;
import models.vat.Vat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VatParser {
    private final String SP = ";";

    public Map getVatMap(List<String> vatListFromFile) {

        Map<String, Vat> vatMap = new HashMap<>();
        for (String s : vatListFromFile) {
            String[] arr = s.split(SP);
            for (String param : arr) {
                if ((param.isEmpty()) || (param == null)) {
                    System.out.println("database error");//;throw new Exception();
                }
            }

            // define vat from the string
            Vat vat = new Vat(arr[0], Double.parseDouble(arr[1]), 0);

            // put article data to the priceList list data base (Map)
            vatMap.put(arr[0], vat);
        }
        return vatMap;
    }
}
