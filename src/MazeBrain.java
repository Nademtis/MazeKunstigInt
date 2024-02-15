import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeBrain {
    public ArrayList<ArrayList<Character>> maze;

    public MazeBrain() {
        maze = new ArrayList<>();
    }

    public void printTile(int xPosition, int yPosition) {
        System.out.print("this tile is: " + yPosition + "," + xPosition);
        System.out.println(" - This tile is marked: \"" + maze.get(yPosition).get(xPosition).charValue() + "\"");

    }

    public boolean traverseMaze(int x, int y) {
        //printMaze();
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
        if (traverseMaze(x + 1, y) || traverseMaze(x - 1, y) || traverseMaze(x, y + 1) || traverseMaze(x, y - 1)) {
            return true;
        }
        //if this was dead end - set back to whitespace
        //this is depthFirst so the method will return false until it can continue the search
        maze.get(y).set(x, ' ');
        return false;
    }

    public void printPath() {
        for (ArrayList<Character> row : maze) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
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

}
