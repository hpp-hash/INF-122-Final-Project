package bejeweled2;

import tileEntityFactory.NextTileEntity;
import tilemap.TileMap;

import java.util.Random;

public class BejeweledTileMap extends TileMap {

    private static BejeweledTileMap map;

    private BejeweledTileMap(int row, int col) {
        super(row, col);
    }

    public static BejeweledTileMap getInstance(int row, int col){
        if (map == null){
            map = new BejeweledTileMap(row, col);
        }
        return map;
    }

    @Override
    public void fillMap() {
        NextTileEntity nextTileEntity = new NextTileEntity(BejeweledGemFactory.getInstance());
        String[] tileEntityNames = {"blue", "green", "orange", "purple", "red", "white", "yellow"};
        Random random = new Random();
        int randomIndex;
        for (int r = 0; r < this.getNumberOfRows(); r++){
            for (int c = 0; c < this.getNumberOfColumns(); c++){
                if(BejeweledController.GEM_SET.size() == 0)
                    randomIndex = random.nextInt(tileEntityNames.length);
                else
                    randomIndex = BejeweledController.GEM_SET.pop();
                nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], this.getTile(r,c));
            }
        }
    }
}
