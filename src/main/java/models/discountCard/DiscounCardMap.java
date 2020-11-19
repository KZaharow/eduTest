package models.discountCard;

import java.util.HashMap;

public class DiscounCardMap {

    private static final HashMap<String, DiscountCard> TEST_DISCOUNT_CARDS = new HashMap<>();

    static {
        TEST_DISCOUNT_CARDS.put("123", new DiscountCard("123", "A. Volsky", 2));
        TEST_DISCOUNT_CARDS.put("7777", new DiscountCard("333", "V. Klimov", 3));
        TEST_DISCOUNT_CARDS.put("012398", new DiscountCard("444", "S. Svetlova", 4));
    }

    public static HashMap<String, DiscountCard> getTestDiscountCards() {
        return TEST_DISCOUNT_CARDS;
    }
}
