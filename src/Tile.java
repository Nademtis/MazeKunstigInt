public class Tile {
    public int y;
    public int x;
    public int price;
    public int heuristic;
    public int sum;

    public Tile(int x, int y, int price) {
        this.y = y;
        this.x = x;
        this.price = price;
        //this.heuristic = heuristic;
    }

    public int getPrice() {
        return price;
    }

    public int getSum() {
        return sum;
    }

    public void calcSum() {
        this.sum = this.heuristic + this.price;
    }
}
