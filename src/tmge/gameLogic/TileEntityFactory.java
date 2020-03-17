package tmge.gameLogic;

import tmge.tilemap.TileEntity;

public interface TileEntityFactory {
    public TileEntity createTileEntity(String name);
}
