package process;

import io.DataFileReader;
import models.discountCard.DiscountCard;
import models.priceList.Article;
import utils.ArticleParser;
import utils.DiscountCardParser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TaskHandler implements ReceiptCreator {
    private String[] inputData;

    private final Path PRICE_LIST_PATH = Paths.get("src/main/resources/priceList.txt");
    private final Path DISCOUNT_CARDS_PATH = Paths.get("src/main/resources/discountCards.txt");

    public TaskHandler(String[] args) {
        this.inputData = args;
    }


    @Override
    public void create() throws IOException {

        //load priceList list from file
        List<String> rawPriceList = new DataFileReader().readReceiptFromFile(PRICE_LIST_PATH);
        Map<String, Article> priceList = new ArticleParser().getPriceList(rawPriceList);

        //load discount cards list
        List<String> rawDiscountCardListList = new DataFileReader().readReceiptFromFile(PRICE_LIST_PATH);
        Map<String, DiscountCard> discountCards = new DiscountCardParser().getDiscountCardMap(rawDiscountCardListList);

        //load user receipt data
        UserReceipt userReceipt = new UserReceipt(inputData, priceList, discountCards);
        userReceipt.create();
        userReceipt.calcDiscount();
        userReceipt.printReceipt();



    }
}
