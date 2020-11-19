package models.receipt;

import models.discountCard.DiscountCard;
import models.priceList.Article;

import java.util.List;

public class Receipt {
    private List<Article> receiptIteam;
    private DiscountCard discountCard;
    private int totalDiscount, total;



    public List<Article> getReceiptIteam() {
        return receiptIteam;
    }

    public void setReceiptIteam(List<Article> receiptIteam) {
        this.receiptIteam = receiptIteam;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }
}
