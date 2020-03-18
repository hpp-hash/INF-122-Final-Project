package bejeweled2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tilemap.TileEntity;
import tileEntityFactory.TileEntityFactory;

import java.net.URL;

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
        URL url;
        switch(name){
            case "blue":
//                return new TileEntity(64, "images/bejeweled2/fruits/blue.png");
                url = this.getClass().getResource("/fruits/blue.png");
                break;
            case "green":
//                return new TileEntity(64, "images/bejeweled2/fruits/green.png");
                url = this.getClass().getResource("/fruits/green.png");
                break;
            case "orange":
//                return new TileEntity(64, "images/bejeweled2/fruits/orange.png");
                url = this.getClass().getResource("/fruits/orange.png");
                break;
            case "purple":
//                return new TileEntity(64, "images/bejeweled2/fruits/purple.png");
                url = this.getClass().getResource("/fruits/purple.png");
                break;
            case "red":
//                return new TileEntity(64, "images/bejeweled2/fruits/red.png");
                url = this.getClass().getResource("/fruits/red.png");
                break;
            case "white":
//                return new TileEntity(64, "images/bejeweled2/fruits/white.png");
                url = this.getClass().getResource("/fruits/white.png");
                break;
            case "yellow":
//                return new TileEntity(64, "images/bejeweled2/fruits/yellow.png");
                url = this.getClass().getResource("/fruits/yellow.png");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }

        return new TileEntity(64, String.valueOf(url));

    }
}
