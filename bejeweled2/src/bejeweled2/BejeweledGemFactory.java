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
                url = this.getClass().getResource("/fruits/blue.png");
                break;
            case "green":
                url = this.getClass().getResource("/fruits/green.png");
                break;
            case "orange":
                url = this.getClass().getResource("/fruits/orange.png");
                break;
            case "purple":
                url = this.getClass().getResource("/fruits/purple.png");
                break;
            case "red":
                url = this.getClass().getResource("/fruits/red.png");
                break;
            case "white":
                url = this.getClass().getResource("/fruits/white.png");
                break;
            case "yellow":
                url = this.getClass().getResource("/fruits/yellow.png");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }

        return new TileEntity(64, String.valueOf(url));

    }
}
