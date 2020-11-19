package utils;

import com.sun.istack.internal.NotNull;
import models.priceList.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleParser {
    private final String SP = ";";

    @NotNull
    public Map getPriceList(List<String> priceListFromFile) {

        Map<String, Article> priceList = new HashMap<>();
        for (String s : priceListFromFile) {
            String[] arr = s.split(SP);
            for (String param : arr) {
                if ((param.isEmpty()) || (param == null)) {
                    System.out.println("database error");//;throw new Exception();
                }
            }

            // define article from the string
            Article article = new Article(arr[0], arr[1], arr[2],
                    Double.parseDouble(arr[3]), Integer.parseInt(arr[4]));

            // put article data to the priceList list data base (Map)
            priceList.put(arr[0], article);
        }
        return priceList;
    }
}
