package pacman.model.factories.ghostfactories;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.*;
import pacman.model.factories.RenderableFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Concrete renderable factory for Ghost objects
 */
public abstract class GhostFactory implements RenderableFactory {

    private static final int RIGHT_X_POSITION_OF_MAP = 448;
    private static final int TOP_Y_POSITION_OF_MAP = 16 * 3;
    private static final int BOTTOM_Y_POSITION_OF_MAP = 16 * 34;

    protected static final Image BLINKY_IMAGE = new Image("maze/ghosts/blinky.png");
    protected static final Image INKY_IMAGE = new Image("maze/ghosts/inky.png");
    protected static final Image CLYDE_IMAGE = new Image("maze/ghosts/clyde.png");
    protected static final Image PINKY_IMAGE = new Image("maze/ghosts/pinky.png");
    List<Vector2D> targetCorners = Arrays.asList(
            new Vector2D(0, TOP_Y_POSITION_OF_MAP),
            new Vector2D(RIGHT_X_POSITION_OF_MAP, TOP_Y_POSITION_OF_MAP),
            new Vector2D(0, BOTTOM_Y_POSITION_OF_MAP),
            new Vector2D(RIGHT_X_POSITION_OF_MAP, BOTTOM_Y_POSITION_OF_MAP)
    );

    @Override
    public Renderable createRenderable(Vector2D position) {
        return createGhost(position);
    }

    public abstract Renderable createGhost(Vector2D position);

}
