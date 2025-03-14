public class Tile {
    private char type;
    private int row, col;

    // Constructor (Fixes the undefined constructor error)
    public Tile(int row, int col, char type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public char getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWalkable() {
        return type == '.' || type == 'C' || type == 'W'; // Walkable tiles (open path, coin, or Wolverine's start)
    }

    @Override
    public String toString() {
        return "Tile[" + type + " at (" + row + "," + col + ")]";
    }
}
