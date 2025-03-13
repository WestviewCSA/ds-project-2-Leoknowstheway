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
            scanner.nextLine(); // Move to next line after reading integers

            Tile[][] map = new Tile[numRows][numCols];

            int rowIndex = 0;

            while (scanner.hasNextLine() && rowIndex < numRows) {
                String row = scanner.nextLine();
                for (int i = 0; i < numCols && i < row.length(); i++) {
                    char el = row.charAt(i);
                    map[rowIndex][i] = new Tile(rowIndex, i, el);
                }
                rowIndex++;
            }

            scanner.close();
            System.out.println("Map loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
