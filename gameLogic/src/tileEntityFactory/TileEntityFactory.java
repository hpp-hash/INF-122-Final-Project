package tileEntityFactory;

import tilemap.TileEntity;

public interface TileEntityFactory {
    public TileEntity createTileEntity(String name);
}
