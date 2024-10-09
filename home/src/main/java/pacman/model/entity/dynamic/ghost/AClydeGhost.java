package pacman.model.entity.dynamic.ghost;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.ghost.chasestrat.ClydeStrategy;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;

public class AClydeGhost extends GhostImpl{
    public AClydeGhost(Image image,
                       BoundingBox boundingBox,
                       KinematicState kinematicState,
                       GhostMode ghostMode,
                       Vector2D targetCorner) {
        super(image, boundingBox, kinematicState, ghostMode, targetCorner);
        setChaseStrategy(new ClydeStrategy());
    }
}
