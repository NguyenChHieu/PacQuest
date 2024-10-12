package pacman.model.entity.dynamic.ghost;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.player.observer.PlayerPositionObserver;

import java.util.Map;
import java.util.Set;

/**
 * Represents Ghost entity in Pac-Man Game
 */
public interface Ghost extends DynamicEntity, PlayerPositionObserver {

    /***
     * Sets the speeds of the Ghost for each GhostMode
     * @param speeds speeds of the Ghost for each GhostMode
     */
    void setSpeeds(Map<GhostMode, Double> speeds);

    /**
     * Sets the mode of the Ghost used to calculate target position
     *
     * @param ghostMode mode of the Ghost
     */
    void setGhostMode(GhostMode ghostMode);

    Vector2D getTargetCorner();
    Ghost getGhost();

    void setDirection(Direction direction);
    KinematicState getKinematicState();
    Set<Direction> getPossibleDirections();
    Direction getCurrentDirection();
}
