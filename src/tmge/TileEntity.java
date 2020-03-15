package tmge;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class TileEntity {
    String iconSrc;
    Image img;
    ImageView imgV;
    //Rectangle rect;
    int tileSize;

    public TileEntity(int tileSize, String iconSrc) {
        this.tileSize = tileSize;

        this.iconSrc = iconSrc;
        img = new Image(iconSrc);
        imgV = new ImageView(img);
        imgV.setFitHeight(this.tileSize);
        imgV.setFitWidth(this.tileSize);
    }

//    public TileEntity(double x, double y, double width, String color)
//    {
//        this.rect = new Rectangle(x, y, width, width);
//        this.rect.setFill(Paint.valueOf(color));
//    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public ImageView getImgV() {
        return imgV;
    }

    public void setImgV(ImageView imgV) {
        this.imgV = imgV;
    }

//    public Rectangle getRect() {
//        return rect;
//    }
//
//    public void setRect(Rectangle rect) {
//        this.rect = rect;
//    }
}
