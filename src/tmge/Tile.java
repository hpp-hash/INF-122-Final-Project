package tmge;

public class Tile {
    int xCoordinate;
    int yCoordinate;
    Object currentObj;
    int objSize;
    TileEntity tileEntity;

    public Tile(String iconSrc) {
        this.tileEntity = new TileEntity(iconSrc);
    }

    public Tile(Tile otherTile) {
        this.tileEntity = new TileEntity(otherTile.getTileEntity().getIconSrc());
        this.xCoordinate = otherTile.xCoordinate;
        this.yCoordinate = otherTile.yCoordinate;
        this.objSize = otherTile.objSize;
        //TODO what is currentObj
    }

    public void isEmpty() {

    }

    public void addEntity() {

    }

    public void removeEntity() {

    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Object getCurrentObj() {
        return currentObj;
    }

    public void setCurrentObj(Object currentObj) {
        this.currentObj = currentObj;
    }

    public int getObjSize() {
        return objSize;
    }

    public void setObjSize(int objSize) {
        this.objSize = objSize;
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    public void setTileEntity(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }
}