package tmge.tilemap;

public abstract class TileMap {
    int numberOfRows;
    int numberOfColumns;
    Tile[][] board;

    public abstract void fillMap();

    public TileMap (int row, int col) {
        this.numberOfRows = row;
        this.numberOfColumns = col;

        this.board = new Tile[this.numberOfRows][this.numberOfColumns];

        for (int r = 0; r < numberOfRows; r++){
            for (int c = 0; c < numberOfColumns; c++){
                board[r][c] = new Tile(r,c, null);
            }
        }

        //cells = new TileEntity[row][col];
    }

//    public void boardInsert(int row, int col, int value) {
//        board[row][col] = value;
//    }
//
//    public void cellInsert(int row, int col, TileEntity teValue) {
//        cells[row][col] = teValue;
//    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public Tile getTile(int rowIndex, int columnIndex){
        return board[rowIndex][columnIndex];
    }

    public Tile[][] getBoard(){
        return board;
    }
}
