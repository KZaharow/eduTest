package utils;

import exceptions.DiscountCardException;
import models.discountCard.DiscountCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class uses for define DiscountCard data from the file data by separator
 */
public class DiscountCardParser {
    private final String SP = ";";

    /**
     *
     * @param discountCardsListFromFile - data from read collection
     * @return Map<String, DiscountCard> discountCardMap = new HashMap<>();
     * @throws DiscountCardException
     */
    public Map getDiscountCardMap(List<String> discountCardsListFromFile) throws DiscountCardException {

        Map<String, DiscountCard> discountCardMap = new HashMap<>();
        for (String s : discountCardsListFromFile) {
            String[] arr = s.split(SP);
            for (String param : arr) {
                if ((param.isEmpty()) || (param == null)) {
                    throw new DiscountCardException("discount card list has null or empty article param");
                }
            }

            double perc = Double.parseDouble(arr[2]);

            // define discount from the string
            DiscountCard discountCard = new DiscountCard(arr[0], arr[1], perc);

            // put article data to the priceList list data base (Map)
            discountCardMap.put(arr[0], discountCard);
        }
        return discountCardMap;
    }
}
