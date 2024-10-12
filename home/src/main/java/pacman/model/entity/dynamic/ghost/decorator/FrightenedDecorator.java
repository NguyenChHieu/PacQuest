package pacman.model.entity.dynamic.ghost.decorator;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.ghost.GhostMode;

public class FrightenedDecorator extends BaseGhostDecorator{
    private final Ghost ghost;
    private final Image image;

    public FrightenedDecorator(Ghost ghost) {
        super(ghost);
        this.image = new Image("maze/ghosts/frightened.png");
        ghost.setGhostMode(GhostMode.FRIGHTENED);
        this.ghost = ghost;
    }


    // TODO IMPLEMENT RANDOM TURNS

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void update() {
        ghost.update();
    }
}
