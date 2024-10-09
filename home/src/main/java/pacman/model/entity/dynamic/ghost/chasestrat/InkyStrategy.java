package pacman.model.entity.dynamic.ghost.chasestrat;

import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;

public class InkyStrategy implements ChaseStrategy {
    // TODO Implement Inky's chase strategy
    @Override
    public Vector2D chase(KinematicState playerPosition, Vector2D pacmanCenter, Ghost currentGhost) {
        return playerPosition.getPosition();
    }
}
