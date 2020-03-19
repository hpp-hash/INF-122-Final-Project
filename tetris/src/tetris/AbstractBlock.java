package tetris;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class AbstractBlock {
    protected Color color;
    protected Rectangle a;
    protected Rectangle b;
    protected Rectangle c;
    protected Rectangle d;

    public AbstractBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {
        this.color = color;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


}
