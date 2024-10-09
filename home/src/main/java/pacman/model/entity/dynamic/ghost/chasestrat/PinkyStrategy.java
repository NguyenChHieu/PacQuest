package pacman.model.entity.dynamic.ghost.chasestrat;

import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.maze.MazeCreator;

public class PinkyStrategy implements ChaseStrategy {
    @Override
    public Vector2D chase(KinematicState playerPosition, Vector2D pacmanCenter, Ghost currentGhost) {
        int xTile = (int) Math.floor(pacmanCenter.getX() / MazeCreator.RESIZING_FACTOR);
        int yTile = (int) Math.floor(pacmanCenter.getY() / MazeCreator.RESIZING_FACTOR);

        Direction playerDirection = playerPosition.getDirection();
        switch (playerDirection) {
            case UP:
                yTile -= 4;
                break;
            case DOWN:
                yTile += 4;
                break;
            case LEFT:
                xTile -= 4;
                break;
            case RIGHT:
                xTile += 4;
                break;
        }

        return new Vector2D(xTile * MazeCreator.RESIZING_FACTOR,
                yTile * MazeCreator.RESIZING_FACTOR);
    }
}
