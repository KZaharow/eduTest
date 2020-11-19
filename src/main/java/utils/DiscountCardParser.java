package utils;

import com.sun.istack.internal.NotNull;
import models.discountCard.DiscountCard;
import models.priceList.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCardParser {
    private final String SP = ";";

    public Map getDiscountCardMap(List<String> discountCardsListFromFile) {

        Map<String, DiscountCard> discountCardMap = new HashMap<>();
        for (String s : discountCardsListFromFile) {
            String[] arr = s.split(SP);
            for (String param : arr) {
                if ((param.isEmpty()) || (param == null)) {
                    System.out.println("database error");//;throw new Exception();
                }
            }

            // define discount from the string
            DiscountCard discountCard = new DiscountCard(arr[0], arr[1], Double.parseDouble(arr[2]));

            // put article data to the priceList list data base (Map)
            discountCardMap.put(arr[0], discountCard);
        }
        return discountCardMap;
    }
}
