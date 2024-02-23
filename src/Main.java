import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MazeBrain mazeBrain = new MazeBrain();
        mazeBrain.readMazeFromFile("src/maze.txt");
        //mazeBrain.printMaze();

        //start/end coordinates - hardcoded
        int startX = 3;
        int startY = 3;
        int endX = 11;
        int endY = 8;

        //blind - depthFirst
        //mazeBrain.blindTraverseMaze(startX, startY);

        //blind - best-first TODO missing 'o' cleanup after completion
        //ArrayList<Tile> tiles = new ArrayList<>();
        //mazeBrain.bestFirstTraverseMaze(tiles);

        //heuristic A* best-first
        ArrayList<Tile> tiles = new ArrayList<>();
        mazeBrain.printTile(7,4);
        mazeBrain.aStar(tiles,startX,startY,7,4);
        mazeBrain.printMaze();


    }



}