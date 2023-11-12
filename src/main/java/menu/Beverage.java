package menu;

public enum Beverage {
    제로콜라(3_000),
    레드와인(60_000),
    샴페인(25_000);

    private int price;

    Beverage(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
