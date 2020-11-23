package utils;

import com.sun.istack.internal.NotNull;
import exceptions.ArticleException;
import models.priceList.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class uses for define Article data from the price list by separator
 */
public class ArticleParser {
    private final String SP = ";";

    /**
     *
     * @param priceListFromFile - data from read collection
     * @return Map<String, Article> priceList = new HashMap<>();
     * @throws ArticleException
     */
    @NotNull
    public Map getPriceList(List<String> priceListFromFile) throws  ArticleException {

        Map<String, Article> priceList = new HashMap<>();
        for (String s : priceListFromFile) {
            String[] arr = s.split(SP);
            for (String param : arr) {
                if ((param.isEmpty()) || (param == null)) {
                    throw new ArticleException("price list has null or empty article param");
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
