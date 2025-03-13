public class Tile {
    private char type;
    private int row, col;

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
        return type == '.' || type == 'C' || type == 'W'; // Walkable tiles (path, coin, Wolverine's start)
    }

  
    public String toString() {
        return null;
    }
}
