import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class p2 {
    public static void main(String[] args) {
        System.out.println("p2");
        readMap("TEST01.txt"); // Ensure filename has the correct format
    }

    public static void readMap(String filename) {
        try {
            File file = new File(filename); // Use actual filename argument
            Scanner scanner = new Scanner(file);

            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();
            scanner.nextLine(); // Move to the next line after reading integers

            Tile[][] map = new Tile[numRows][numCols];

            for (int row = 0; row < numRows; row++) {
                if (!scanner.hasNextLine()) {
                    System.out.println("Error: Incomplete map, missing row " + row);
                    return;
                }
                String line = scanner.nextLine();

                for (int col = 0; col < Math.min(numCols, line.length()); col++) {
                    char el = line.charAt(col);
                    map[row][col] = new Tile(row, col, el);
                }
            }

            System.out.println("Map successfully read!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
        }
    }
}
