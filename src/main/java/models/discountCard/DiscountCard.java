package models.discountCard;

public class DiscountCard {
    private String id;
    private String name;
    // 1.5% = 150
    private double percent;

    public DiscountCard(String id, String name, double percent) {
        this.id = id;
        this.name = name;
        this.percent = percent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percent=" + percent +
                '}';
    }
}
