package pacman.model.entity.dynamic.ghost.chasestrat;

import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.maze.MazeCreator;

public class ClydeStrategy implements ChaseStrategy {
    @Override
    public Vector2D chase(KinematicState playerPosition, Vector2D pacmanCenter, Ghost currentGhost) {
        int xTilePacman = (int) Math.floor(pacmanCenter.getX() / MazeCreator.RESIZING_FACTOR);
        int yTilePacman = (int) Math.floor(pacmanCenter.getY() / MazeCreator.RESIZING_FACTOR);
        int xTileGhost = (int) Math.floor(currentGhost.getCenter().getX() / MazeCreator.RESIZING_FACTOR);
        int yTileGhost = (int) Math.floor(currentGhost.getCenter().getY() / MazeCreator.RESIZING_FACTOR);

        double distance = Vector2D.calculateEuclideanDistance(new Vector2D(xTilePacman, yTilePacman),
                new Vector2D(xTileGhost, yTileGhost));

        if (distance > 8) {
            return playerPosition.getPosition();
        }
        return currentGhost.getCenter();
    }

}
