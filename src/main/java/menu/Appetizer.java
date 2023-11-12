package menu;

public enum Appetizer {
    양송이수프(6_000),
    타파스(5_500),
    시저샐러드(8_000);

    private int price;

    Appetizer(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
