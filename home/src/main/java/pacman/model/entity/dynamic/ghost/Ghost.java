package pacman.model.entity.dynamic.ghost;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.ghost.state.IGhostState;
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

    Vector2D getTargetLocation();

    void setTargetLocation(Vector2D targetLocation);

    void setCurrentDirection(Direction direction);

    Direction getCurrentDirection();

    KinematicState getKinematicState();

    Set<Direction> getPossibleDirections();

    void setState(IGhostState state);

    IGhostState getState();

    int getCurrentDirectionCount();

    void setCurrentDirectionCount(int currentDirectionCount);
}
