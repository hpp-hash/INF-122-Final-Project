package bejeweled2;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tmge.TileEntity;

public class BejeweledController extends BejeweledGameLogic{

    // TODO: attributes (if any)
    private int GEM_SIZE = 64;

    public BejeweledController() {
    }

    public void moveOnMouseDrag() {
        // TODO: implement movement
        currentTileImageView.setOnMousePressed(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event)
            {
                cX = tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                cY = tY = (int)((event.getSceneY() - 30) / GEM_SIZE);
                draw();
            }
        });

        currentTileImageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event)
            {
                tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                tY = (int)((event.getSceneY() - 30) / GEM_SIZE);

                TileEntity temp = map.getTile(tY, tX).getTileEntity();
                map.getTile(tY, tX).addEntity(map.getTile(cY, cX).getTileEntity());
                map.getTile(cY, cX).addEntity(temp);

                if(eatable(tY, tX) || eatable(cY, cX))
                {
                    label.setText("EatAble");
                }
                else
                {
                    label.setText("Swap Back");
                    temp = map.getTile(tY, tX).getTileEntity();
                    map.getTile(tY, tX).addEntity(map.getTile(cY, cX).getTileEntity());
                    map.getTile(cY, cX).addEntity(temp);

                }
                draw();
            }
        });
    }

    // placeholder for non-movement handling; can involve keyboard or mouse
    public void otherMove(){
        // TODO: implement other moves (if any)
    }
}
