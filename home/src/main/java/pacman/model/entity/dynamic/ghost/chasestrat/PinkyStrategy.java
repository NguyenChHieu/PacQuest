package pacman.model.entity.dynamic.ghost.chasestrat;

import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.maze.MazeCreator;

public class PinkyStrategy implements ChaseStrategy {
    private static final int MIN_DISTANCE = 4;

    @Override
    public Vector2D chase(KinematicState playerPosition, Vector2D pacmanCenter, Ghost currentGhost) {
        int xTile = (int) Math.floor(pacmanCenter.getX() / MazeCreator.RESIZING_FACTOR);
        int yTile = (int) Math.floor(pacmanCenter.getY() / MazeCreator.RESIZING_FACTOR);

        Direction playerDirection = playerPosition.getDirection();
        switch (playerDirection) {
            case UP:
                yTile -= MIN_DISTANCE;
                break;
            case DOWN:
                yTile += MIN_DISTANCE;
                break;
            case LEFT:
                xTile -= MIN_DISTANCE;
                break;
            case RIGHT:
                xTile += MIN_DISTANCE;
                break;
        }
        xTile = Math.max(Math.min(xTile, 27), 0);
        yTile = Math.max(Math.min(yTile, 35), 0);

        return new Vector2D(xTile * MazeCreator.RESIZING_FACTOR,
                yTile * MazeCreator.RESIZING_FACTOR);
    }
}
