package pacman.model.entity.dynamic.ghost.state;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.level.Level;

public interface IGhostState {
    void update(Ghost ghost);
    void collides(Level level, Renderable renderable);
    Image getImage();
}
