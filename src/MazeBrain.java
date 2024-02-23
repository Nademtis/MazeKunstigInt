import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MazeBrain {
    public ArrayList<ArrayList<Character>> maze;

    public MazeBrain() {
        maze = new ArrayList<>();
    }

    public void printTile(int xPosition, int yPosition) {
        System.out.print("this tile is: " + yPosition + "," + xPosition);
        System.out.println(" - This tile is marked: \"" + maze.get(yPosition).get(xPosition).charValue() + "\"");

    }

    public boolean blindTraverseMaze(int x, int y) {
        printAndWait(500);
        if (maze.get(y).get(x) == 'G') {
            System.out.println("Found end at: " + x + "," + y);
            return true;
        }
        //check if current position is wall
        if (maze.get(y).get(x) == '*') {
            //System.out.println("Wall at: " + x + "," + y);
            return false;
        }
        //check if current position has been explored before
        if (maze.get(y).get(x) == 'o') {
            //System.out.println("Was here before: " + x + "," + y);
            return false;
        }
        //set current position as explored
        maze.get(y).set(x, 'o');
        //explore neighbour cells. 1 right, 1 left, 1 up, 1 down - this is the order it checks
        if (blindTraverseMaze(x + 1, y) || blindTraverseMaze(x - 1, y) || blindTraverseMaze(x, y + 1) || blindTraverseMaze(x, y - 1)) {
            return true;
        }
        //if this was dead end - set back to whitespace
        //this is depthFirst so the method will return false until it can continue the search
        maze.get(y).set(x, ' ');
        return false;
    }

    public boolean bestFirstTraverseMaze(ArrayList<Tile> tiles) {
        printAndWait(500); // 500 is good

        if (tiles.isEmpty()) {
            Tile tile = new Tile(3, 3, 0); //TODO hardcode start value
            tiles.add(tile);
            //return bestFirstTraverseMaze(tiles);
        }
        //current tile
        Tile currentTile = tiles.get(0);

        //check if goal reached
        if (maze.get(currentTile.y).get(currentTile.x) == 'G') {
            System.out.println("Found end at: " + currentTile.x + "," + currentTile.y);
            return true;
        }
        //check if current position is wall
        if (maze.get(currentTile.y).get(currentTile.x) == '*') {
            System.out.println("Wall at: " + currentTile.x + "," + currentTile.y);
            tiles.remove(currentTile);
            return bestFirstTraverseMaze(tiles);
        }
        if (maze.get(currentTile.y).get(currentTile.x) == 'o') {
            System.out.println("Was here before: " + currentTile.x + "," + currentTile.y);
            tiles.remove(currentTile);
            return bestFirstTraverseMaze(tiles);
        }
        //add adjacent tiles to list - price goes up by 1
        tiles.add(new Tile(currentTile.x + 1, currentTile.y, currentTile.price + 1));
        tiles.add(new Tile(currentTile.x, currentTile.y + 1, currentTile.price + 1));
        tiles.add(new Tile(currentTile.x - 1, currentTile.y, currentTile.price + 1));
        tiles.add(new Tile(currentTile.x, currentTile.y - 1, currentTile.price + 1));

        //mark the current tile as visited
        maze.get(currentTile.y).set(currentTile.x, 'o');

        //remove the tile from the list
        tiles.remove(currentTile);

        return bestFirstTraverseMaze(tiles);
    }

    public boolean aStar(ArrayList<Tile> tiles, int startX, int startY, int endX, int endY) {
        printAndWait(500);
        if (tiles.isEmpty()) {
            Tile tile = new Tile(3, 3, 0); //TODO hardcode start value
            tiles.add(tile);
        }
        //sort the list by price
        Collections.sort(tiles, Comparator.comparingInt(Tile::getPrice));

        Tile currentTile = tiles.get(0);

        //check if goal reached
        if (maze.get(currentTile.y).get(currentTile.x) == 'G') {
            System.out.println("Found end at: " + currentTile.x + "," + currentTile.y);
            return true;
        }
        //check if current position is wall
        if (maze.get(currentTile.y).get(currentTile.x) == '*') {
            System.out.println("Wall at: " + currentTile.x + "," + currentTile.y);
            tiles.remove(currentTile);
            return aStar(tiles, startX, startY, endX, endY);
        }
        if (maze.get(currentTile.y).get(currentTile.x) == 'o') {
            System.out.println("Was here before: " + currentTile.x + "," + currentTile.y);
            tiles.remove(currentTile);
            return aStar(tiles, startX, startY, endX, endY);
        }
        Tile tileRight = new Tile(currentTile.x + 1, currentTile.y, currentTile.price + 1);
        Tile tileDown = new Tile(currentTile.x, currentTile.y + 1, currentTile.price + 1);
        Tile tileLeft = new Tile(currentTile.x - 1, currentTile.y, currentTile.price + 1);
        Tile tileUp = new Tile(currentTile.x, currentTile.y - 1, currentTile.price + 1);

        tileRight.price = tileRight.price + Math.abs(endX-tileRight.x) + Math.abs(endY-tileRight.y);
        tileDown.price = tileDown.price + Math.abs(endX-tileDown.x) + Math.abs(endY-tileDown.y);
        tileLeft.price = tileLeft.price + Math.abs(endX-tileLeft.x) + Math.abs(endY-tileLeft.y);
        tileUp.price = tileUp.price + Math.abs(endX-tileUp.x) + Math.abs(endY-tileUp.y);

        tiles.add(tileRight);
        tiles.add(tileDown);
        tiles.add(tileLeft);
        tiles.add(tileUp);

        //mark the current tile as visited
        maze.get(currentTile.y).set(currentTile.x, 'o');

        //remove the tile from the list
        tiles.remove(currentTile);

        return aStar(tiles, startX, startY, endX, endY);

    }

    public void readMazeFromFile(String filePath) {//from gpt
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Character> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add(c);
                }
                maze.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMaze() {//from gpt
        for (ArrayList<Character> row : maze) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public void printAndWait(int milliSecondsToWait) {
        printMaze();
        try {
            Thread.sleep(milliSecondsToWait);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
