package pacman.model.entity.dynamic.ghost.state;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.*;
import pacman.model.entity.dynamic.physics.*;
import pacman.model.level.Level;
import pacman.model.maze.Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import static pacman.model.entity.dynamic.ghost.GhostImpl.minimumDirectionCount;

public class GhostFrightenedState implements IGhostState {
    private Ghost ghost;
    private KinematicState ks;
    private final Image img = new Image("maze/ghosts/frightened.png");
    private int tickCount;

    public GhostFrightenedState(Ghost ghost, int duration) {
        ghost.setGhostMode(GhostMode.FRIGHTENED);
        this.ghost = ghost;
        this.tickCount = duration;
        this.ks = ghost.getKinematicState();
    }

    @Override
    public void update(Ghost ghost) {
        if (tickCount == 0) {
            ghost.setState(new GhostNormalState(ghost));
            return;
        }

        this.ghost = ghost;
        ks = ghost.getKinematicState();
        updateDirection();
        ks.update();
        ghost.getBoundingBox().setTopLeft(ks.getPosition());
        tickCount--;
    }

    @Override
    public void collides(Level level, Renderable renderable) {
        if (level.isPlayer(renderable)) {
            ghost.reset();
            ghost.setGhostMode(GhostMode.SCATTER);
            ghost.freeze(34);
        }
    }

    private void updateDirection() {
        if (Maze.isAtIntersection(ghost.getPossibleDirections())) {
            Direction newDirection = selectDirection(ghost.getPossibleDirections());

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
    }

    private Direction selectDirection(Set<Direction> possibleDirections) {
        if (possibleDirections.isEmpty()) {
            return ghost.getCurrentDirection();
        }

        if (ghost.getCurrentDirection() != null && ghost.getCurrentDirectionCount() < minimumDirectionCount) {
            ghost.setCurrentDirectionCount(ghost.getCurrentDirectionCount() + 1);
            return ghost.getCurrentDirection();
        }

        ArrayList<Direction> validDirections = new ArrayList<>();

        for (Direction direction : possibleDirections) {
            if (ghost.getCurrentDirection() == null || direction != ghost.getCurrentDirection().opposite()) {
                validDirections.add(direction);
            }
        }

        // only go the opposite way if trapped
        if (validDirections.isEmpty()) {
            return ghost.getCurrentDirection().opposite();
        }

        // Randomly select a direction
        int rand = new Random().nextInt(validDirections.size());
        return validDirections.get(rand);
    }

    public void resetTickCount(int duration) {
        tickCount = duration;
    }

    @Override
    public Image getImage() {
        return img;
    }
}
