package tilemap;

public class Tile {
    int rowIndex;
    int columnIndex;
    TileEntity tileEntity;

    public Tile(int rowIndex, int columnIndex, TileEntity entity) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.tileEntity = entity;
    }

//    public Tile(Tile otherTile) {
//        this.tileEntity = new TileEntity(otherTile.getTileEntity().getIconSrc());
//        this.columnIndex = otherTile.columnIndex;
//        this.rowIndex = otherTile.rowIndex;
//        this.objSize = otherTile.objSize;
//    }

    public boolean isEmpty() {
        return tileEntity == null;
    }

    public void addEntity(TileEntity entity) {
        tileEntity = entity;
        tileEntity.getImgV().setTranslateX(columnIndex * entity.tileSize + 20); // TODO: must change hardcoded numbers
        tileEntity.getImgV().setTranslateY(rowIndex * entity.tileSize + 30);    // TODO: must change hardcoded numbers
    }

    public void removeEntity() {
        tileEntity = null;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }
}