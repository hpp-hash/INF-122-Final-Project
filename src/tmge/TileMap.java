package tmge;

public abstract class TileMap {
    int length;
    int width;
    int[][] board;
    TileEntity [][] cells;

    public abstract void fillMap();

    public TileMap (int row, int col) {
        this.length=col;
        this.width=row;
        board = new int[row][col];
        cells = new TileEntity[[row][cells];
    }

    public void boardInsert(int row, int col, int value) {
        board[row][col] = value;
    }

    public void cellInsert(int row, int col, TileEntity teValue) {
        cells[row][col] = teValue;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
