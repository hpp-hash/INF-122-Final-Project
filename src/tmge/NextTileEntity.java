package tmge;

public abstract class NextTileEntity implements TileEntityFactory {

    private TileEntityFactory factory;

    public NextTileEntity(TileEntityFactory factory){
        this.factory = factory;
    }

    public void addNewTileEntity(String entityName, Tile tile){
        tile.addEntity(factory.createTileEntity(entityName));
    }
}
