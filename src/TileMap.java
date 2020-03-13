public class TileMap {
    public int length;
    public int width;
    public Tile[][] board;
    private static TileMap tileMapInstance;


    private TileMap(int length, int width){
        this.length = length;
        this.width = width;
        this.board = new int[length][width];
    }


    public void fillMap() {

        for (int i=0; i<length; i++ ){
            for (int j=0; j<width; j++){
                board[i][j] = new Tile(i,j);
            }
        }

    }


    public static TileMap getInstance(int length, int width){

        if (tileMapInstance == null){
            tileMapInstance = new TileMap(length, width);
        }
        return tileMapInstance;

    }
}
