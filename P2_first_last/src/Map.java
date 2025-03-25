import java.util.*;

public class Map {
    private int rows, cols;
    private Tile[][] map;
    private boolean[][] visited;

    // Directions: North, South, East, West
    private final int[] dRow = {-1, 1, 0, 0};
    private final int[] dCol = {0, 0, 1, -1};

    public Map(int rows, int cols, Scanner scanner) throws IncorrectMapFormatException, IllegalMapCharacterException, IncompleteMapException {
        this.rows = rows;
        this.cols = cols;
        this.map = new Tile[rows][cols];
        this.visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (!scanner.hasNextLine()) throw new IncompleteMapException("Incomplete map: missing row " + i);
            String line = scanner.nextLine();
            if (line.length() < cols) throw new IncompleteMapException("Incomplete map: row " + i + " has too few characters.");

            for (int j = 0; j < cols; j++) {
                char type = line.charAt(j);
                if (type != '#' && type != '.' && type != 'W' && type != 'C') {
                    throw new IllegalMapCharacterException("Illegal character '" + type + "' found at (" + i + "," + j + ")");
                }
                map[i][j] = new Tile(i, j, type); // Fixed row and col order
            }
        }
    }


    public int findCoinBFS(int startRow, int startCol) {
        Queue<Tile> queue = new LinkedList<>();
        queue.add(map[startRow][startCol]);
        visited[startRow][startCol] = true;

        // Store the previous tile to reconstruct the path
        Tile[][] previous = new Tile[map.length][map[0].length];  

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Tile current = queue.poll();

                if (current.getType() == 'C') { 
                    printPath(previous, current, startRow, startCol);
                    return steps; 
                }

                for (int j = 0; j < 4; j++) {
                    int newRow = current.getRow() + dRow[j];
                    int newCol = current.getCol() + dCol[j];

                    if (isValidMove(newRow, newCol)) {
                        Tile nextTile = map[newRow][newCol];
                        visited[newRow][newCol] = true;
                        queue.add(nextTile);
                        previous[newRow][newCol] = current; // Store parent for path reconstruction
                    }
                }
            }
            steps++;
        }
        return -1; // No path found
    }


    private void printPath(Tile[][] previous, Tile coinTile, int startRow, int startCol) {
        List<Tile> path = new ArrayList<>();
        Tile current = coinTile;

        // Trace the code
        while (current != null && !(current.getRow() == startRow && current.getCol() == startCol)) {
            path.add(current);
            current = previous[current.getRow()][current.getCol()];
        }

        path.add(map[startRow][startCol]); // Add start position
        Collections.reverse(path); // Reverse to get path from start to coin

        // Print path
        System.out.println("Coordinates :");
        for (Tile tile : path) {
            System.out.println("(" + tile.getRow() + ", " + tile.getCol() + ")");
        }
    }




  public int findCoinDFS(int startRow, int startCol) {
        Stack<Tile> stack = new Stack<>();
        stack.push(map[startRow][startCol]);
        visited[startRow][startCol] = true;

        int steps = 0;
        while (!stack.isEmpty()) {
            Tile current = stack.pop();

            if (current.getType() == '$') return steps; // Found the coin

            for (int[] direction : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
                int newRow = current.getRow() + direction[0];
                int newCol = current.getCol() + direction[1];
                if (isValidMove(newRow, newCol)) {
                    visited[newRow][newCol] = true;
                    stack.push(map[newRow][newCol]);
                    steps++;
                }
            }
            // Move to the next step
        }
        return -1; // No path found
    }

    private boolean isValidMove(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols &&
               !visited[r][c] && map[r][c].isWalkable();
    }

    public Tile findWolverine() throws IncorrectMapFormatException {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j].getType() == 'W') return map[i][j];
            }
        }
        throw new IncorrectMapFormatException("Wolverine start position not found!");
    }

    public static class IllegalCommandLineInputsException extends Exception {
        public IllegalCommandLineInputsException(String message) {
            super(message);
        }
    }

    public static class IllegalMapCharacterException extends Exception {
        public IllegalMapCharacterException(String message) {
            super(message);
        }
    }

    public static class IncompleteMapException extends Exception {
        public IncompleteMapException(String message) {
            super(message);
        }
    }

    public static class IncorrectMapFormatException extends Exception {
        public IncorrectMapFormatException(String message) {
            super(message);
        }
    }
}
