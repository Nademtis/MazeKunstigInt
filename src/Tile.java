public class Tile {
    public int y;
    public int x;
    public int price;

    public Tile(int x, int y, int price) {
        this.y = y;
        this.x = x;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
