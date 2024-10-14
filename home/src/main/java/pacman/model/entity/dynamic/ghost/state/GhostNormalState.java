package pacman.model.entity.dynamic.ghost.state;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.level.Level;
import pacman.model.maze.Maze;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static pacman.model.entity.dynamic.ghost.GhostImpl.minimumDirectionCount;

public class GhostNormalState implements IGhostState {
    private Ghost ghost;
    private KinematicState ks;

    public GhostNormalState(Ghost ghost) {
        this.ghost = ghost;
        this.ks = ghost.getKinematicState();
    }

    @Override
    public void update(Ghost ghost) {
        this.ghost = ghost;
        ks = ghost.getKinematicState();
        updateDirection();
        ks.update();
        ghost.getBoundingBox().setTopLeft(ks.getPosition());
    }

    @Override
    public void collides(Level level, Renderable renderable) {
        if (level.isPlayer(renderable)) {
            level.handleLoseLife();
        }
    }

    private void updateDirection() {
        // Ghosts update their target location when they reach an intersection
        if (Maze.isAtIntersection(ghost.getPossibleDirections())) {
            ghost.setTargetLocation(ghost.getTargetLocation());
        }

        Direction newDirection = selectDirection(ghost.getPossibleDirections());

        // Ghosts have to continue in a direction for a minimum time before changing direction
        if (ghost.getCurrentDirection() != newDirection) {
            ghost.setCurrentDirectionCount(0);
        }
        ghost.setCurrentDirection(newDirection);

        switch (ghost.getCurrentDirection()) {
            case LEFT -> ks.left();
            case RIGHT -> ks.right();
            case UP -> ks.up();
            case DOWN -> ks.down();
        }
    }

    private Direction selectDirection(Set<Direction> possibleDirections) {
        if (possibleDirections.isEmpty()) {
            return ghost.getCurrentDirection();
        }

        // ghosts have to continue in a direction for a minimum time before changing direction
        if (ghost.getCurrentDirection() != null && ghost.getCurrentDirectionCount() < minimumDirectionCount) {
            ghost.setCurrentDirectionCount(ghost.getCurrentDirectionCount() + 1);
            return ghost.getCurrentDirection();
        }

        Map<Direction, Double> distances = new HashMap<>();

        for (Direction direction : possibleDirections) {
            // ghosts never choose to reverse travel
            if (ghost.getCurrentDirection() == null || direction != ghost.getCurrentDirection().opposite()) {
                distances.put(direction, Vector2D.calculateEuclideanDistance(
                        ks.getPotentialPosition(direction),
                        ghost.getTargetLocation()));
            }
        }

        // only go the opposite way if trapped
        if (distances.isEmpty()) {
            return ghost.getCurrentDirection().opposite();
        }

        // select the direction that will reach the target location fastest
        return Collections.min(distances.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void frightenedState(int duration) {
        ghost.setState(new GhostFrightenedState(ghost, duration));
    }

    @Override
    public Image getImage() {
        return ghost.getImage();
    }
}
