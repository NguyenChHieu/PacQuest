package pacman.model.entity.staticentity.collectable;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.physics.BoundingBox;

public class SuperPellet extends Pellet{

    public SuperPellet(BoundingBox boundingBox, Layer layer, Image image, int points) {
        super(boundingBox, layer, image, points);
    }

}
