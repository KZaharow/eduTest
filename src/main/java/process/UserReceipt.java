package process;

import models.receipt.Iteam;
import printer.Adjustment;
import printer.Printer;
import models.receipt.Reciptor;
import models.discountCard.DiscounCardMap;
import models.priceList.Article;
import models.discountCard.DiscountCard;

import java.util.HashMap;
import java.util.Map;

public class UserReceipt implements Reciptor {


    private final double DISCOUNT_QTY_LIMIT = 5; // 5.000
    private final double DISCOUNT_PRC_LIMIT = 10; //%
    private String[] inputArrayString;
    private Map<String, Article> priceList;
    private Map<String, Iteam> items;
    private Map<String, DiscountCard> discountCardsList;
    private DiscountCard discountCard;
    private double total;
    private double totalDiscount;
    private Map<String, Double> vatList;

    public UserReceipt(String[] inputArrayString, Map<String, Article> priceList, Map<String, DiscountCard> discountCardsList) {
        this.inputArrayString = inputArrayString;
        this.priceList = priceList;
        this.discountCardsList = discountCardsList;
    }

    @Override
    public void saveReceipt() {

    }

    @Override
    public void create() {
        items = getItemsList(inputArrayString);
        discountCard = getUserDiscountCard(inputArrayString);
    }

    private DiscountCard getUserDiscountCard(String[] userInputData) {

        //load test discount cards data base
        HashMap testDiscountCardMap = DiscounCardMap.getTestDiscountCards();

        // get user discount card data
        for (String s : userInputData) {

            if (s.matches("#\\d{1,10}")) {
                String[] arr = s.split("#");
                if (testDiscountCardMap.containsKey(arr[1])) {
                    discountCard = (DiscountCard) testDiscountCardMap.get(arr[1]);
                }
            }
        }
        return discountCard;
    }

    private Map getItemsList(String[] userInputData) {

        // basic map for receipt items
        items = new HashMap<>();
        // userRequestList uses for collect data from user as HashMap
        Map<String, String> userRequestList = new HashMap<>();

        // get user receipt item data
        for (String s : userInputData) {

            if (s.matches("\\d{1,13}-\\d{1,6}")) {
                String[] arr = s.split("-");
                String k = arr[0];
                String v = arr[1];

                // check for twins in user task, and if twins a present make sum qty of it
                if (userRequestList.containsKey(k)){
                    v = String.valueOf(Integer.parseInt(userRequestList.get(k))
                            + Integer.parseInt(v));
                }
                userRequestList.put(k, v);
            }
        }

        // looking for article in the price list and create an item of userReceipt
        userRequestList.forEach((k, v) -> {
            if (priceList.containsKey(k)) {
                Article article = priceList.get(k);
                Iteam iteam = new Iteam(article.getCode(),
                        article.getName(),
                        article.getTax(),
                        article.getPrice(),
                        article.getFlags(),
                        0,
                        Double.parseDouble(v));
                items.put(k, iteam);
            }
        });
        return items;
    }

    @Override
    public void calcDiscount() {
        items.forEach((k, v) -> {
            if (((v.getFlags() & 1) == 1) && (v.getQuantity() > DISCOUNT_QTY_LIMIT)){
                double kDsc = (100 - DISCOUNT_PRC_LIMIT)/100;
                v.setDiscount(DISCOUNT_PRC_LIMIT);
            }
            if ((((v.getFlags() >> 1) & 1) == 1) && (discountCard != null)){
                v.setDiscount(v.getDiscount() + discountCard.getPercent());
            }

        });
    }

    @Override
    public void printReceipt() {

        // header
        // body
        // sub total
        // footer
        Printer printer = new Printer();
        printer.printHeader();
        items.forEach((k, v)-> printer.print(v, Adjustment.LEFT));
        if (discountCard != null) printer.print(discountCard);
        printer.printFooter();
    }

    @Override
    public void getTotal() {
        items.forEach((k, v) -> {
            total += v.getIteamTotal();
            totalDiscount += v.getIteamTotal() * v.getDiscount() / 100;
        });
    }
}
