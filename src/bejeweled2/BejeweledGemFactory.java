package bejeweled2;

import tmge.TileEntity;
import tmge.TileEntityFactory;

public class BejeweledGemFactory implements TileEntityFactory {

    private static BejeweledGemFactory factory;

    private BejeweledGemFactory(){};

    public static BejeweledGemFactory getInstance(){
        if (factory == null){
            factory = new BejeweledGemFactory();
        }
        return factory;
    }

    @Override
    public TileEntity createTileEntity(String name) {
        switch(name){
            case "blue":
                return new TileEntity(64, "images/bejeweled2/fruits/blue.png");
            case "green":
                return new TileEntity(64, "images/bejeweled2/fruits/green.png");
            case "orange":
                return new TileEntity(64, "images/bejeweled2/fruits/orange.png");
            case "purple":
                return new TileEntity(64, "images/bejeweled2/fruits/purple.png");
            case "red":
                return new TileEntity(64, "images/bejeweled2/fruits/red.png");
            case "white":
                return new TileEntity(64, "images/bejeweled2/fruits/white.png");
            case "yellow":
                return new TileEntity(64, "images/bejeweled2/fruits/yellow.png");
            default:
                return null;
        }
    }
}
