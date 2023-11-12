package menu;

public enum Dessert {
    초코케이크(15_000),
    아이스크림(5_000);

    private int price;

    Dessert(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
