package pacman.model.entity.dynamic.ghost.decorator;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.maze.Maze;

import java.util.*;

import static pacman.model.entity.dynamic.ghost.GhostImpl.minimumDirectionCount;

public class FrightenedDecorator extends BaseGhostDecorator{
    private final Ghost ghost;
    private final Image image;
    private int currentDirectionCount = 0;

    public FrightenedDecorator(Ghost ghost) {
        super(ghost);
        this.image = new Image("maze/ghosts/frightened.png");
        ghost.setGhostMode(GhostMode.FRIGHTENED);
        this.ghost = ghost;
    }


    // TODO IMPLEMENT RANDOM TURNS

    @Override
    public void update() {
        updateDirection();
        ghost.getKinematicState().update();
        ghost.getBoundingBox().setTopLeft(ghost.getKinematicState().getPosition());
    }

    private Direction selectDirection(Set<Direction> possibleDirections){
        if (possibleDirections.isEmpty()) {
            return ghost.getCurrentDirection();
        }

        if (ghost.getCurrentDirection() != null && currentDirectionCount < minimumDirectionCount) {
            currentDirectionCount++;
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

    private void updateDirection() {
        if (Maze.isAtIntersection(ghost.getPossibleDirections())) {
            Direction newDirection = selectDirection(ghost.getPossibleDirections());

            if (ghost.getCurrentDirection() != newDirection) {
                this.currentDirectionCount = 0;
            }

            ghost.setDirection(newDirection);

            switch (ghost.getCurrentDirection()) {
                case LEFT -> ghost.getKinematicState().left();
                case RIGHT -> this.getKinematicState().right();
                case UP -> this.getKinematicState().up();
                case DOWN -> this.getKinematicState().down();
            }
        }
    }

    // RENDERABLE
    @Override
    public Image getImage() {
        return image;
    }
}
