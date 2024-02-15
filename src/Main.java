public class Main {
    public static void main(String[] args) {
        MazeBrain mazeBrain = new MazeBrain();
        mazeBrain.readMazeFromFile("src/maze.txt");
        mazeBrain.printMaze();


        int startY = 3;
        int startX = 3;

        int endY = 8;
        int endX = 11;
        //mazeBrain.printTile(startX, startY);
        mazeBrain.traverseMaze(startX, startY);
        mazeBrain.printMaze();
    }
}