import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        System.out.println("p2");
        readMap("TEST01.txt");
    }

    public static void readMap(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();
            scanner.nextLine();

            Map gameMap = new Map(numRows, numCols, scanner);  // Now properly initialized

            Tile wolverineTile = gameMap.findWolverine();
            boolean[][] visited = new boolean[numRows][numCols];

            // Running BFS (queueSolve) and DFS (stackSolve)
            long startTimeQueue = System.nanoTime();
            boolean foundQueue = gameMap.queueSolve(wolverineTile.getRow(), wolverineTile.getCol());
            double totalTimeQueue = (System.nanoTime() - startTimeQueue) / 1e9;

            System.out.println("Queue Solve - Coin found: " + foundQueue);
            System.out.printf("Queue Solve Runtime: %.6f seconds\n", totalTimeQueue);

            long startTimeStack = System.nanoTime();
            boolean foundStack = gameMap.stackSolve(wolverineTile.getRow(), wolverineTile.getCol(), visited);
            double totalTimeStack = (System.nanoTime() - startTimeStack) / 1e9;

            System.out.println("Stack Solve - Coin found: " + foundStack);
            System.out.printf("Stack Solve Runtime: %.6f seconds\n", totalTimeStack);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
