import java.util.*;
public class Map {
	 private int rows, cols;
	 private Tile[][] map; //is 2d enough
	 private final int[] dRow = {-1, 1, 0, 0};
	 private final int[] dCol = {0, 0, 1, -1};
	 private boolean[][] visited;

public int findCoin(int startRow, int startCol) {
    Queue<Tile> queue = new LinkedList<>();
    queue.add(map[startRow][startCol]);
    visited[startRow][startCol] = true;

    int steps = 0;
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Tile current = queue.poll();

            if (current.getType() == 'C') return steps; // Found the coin

            // Explore neighbors (N, S, E, W)
            for (int j = 0; j < 4; j++) {
                int newRow = current.getRow() + dRow[j];
                int newCol = current.getCol() + dCol[j];

                if (isValidMove(newRow, newCol)) {
                    visited[newRow][newCol] = true;
                    queue.add(map[newRow][newCol]);
                }
            }
        }
        steps++; // Move to the next step
    }
    return -1; // No path found
}
private boolean isValidMove(int r, int c) {
    return r >= 0 && r < rows && c >= 0 && c < cols &&
           !visited[r][c] && map[r][c].isWalkable();
}

// Finds Wolverine's start position
public Tile findWolverine() {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (map[i][j].getType() == 'W') return map[i][j];
        }
    }
    return null;
}
	
}
