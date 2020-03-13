package tmge;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class TileEntity implements Moveable, Clearable{
    String name;
    String iconSrc;
    Image img;
    ImageView imgV;
    Rectangle rect;

    public TileEntity(String name, String iconSrc) {
        this.name = name;
        this.iconSrc = iconSrc;
        img = new Image(iconSrc);
        imgV = new ImageView(img);
    }

    public TileEntity(double x, double y, double width, String color)
    {
        this.rect = new Rectangle(x, y, width, width);
        this.rect.setFill(Paint.valueOf(color));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
