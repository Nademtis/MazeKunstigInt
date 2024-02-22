import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MazeBrain mazeBrain = new MazeBrain();
        mazeBrain.readMazeFromFile("src/maze.txt");
        mazeBrain.printMaze();

        //start coordinates - hardcoded
        int startY = 3;
        int startX = 3;

        //end coordinates - hardcoded
        int endY = 8;
        int endX = 11;

        //blind - depthFirst
        //mazeBrain.blindTraverseMaze(startX, startY);

        //blind - best-first TODO missing 'o' cleanup after completion
        ArrayList<Tile> tiles = new ArrayList<>();
        mazeBrain.bestFirstTraverseMaze(tiles);


    }
}