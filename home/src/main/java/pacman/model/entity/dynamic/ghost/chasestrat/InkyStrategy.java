package pacman.model.entity.dynamic.ghost.chasestrat;

import pacman.model.entity.dynamic.ghost.ABlinkyGhost;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.maze.MazeCreator;

public class InkyStrategy implements ChaseStrategy {
    private ABlinkyGhost blinkyGhost;
    private static final int GRID_AHEAD = 2;

    @Override
    public Vector2D chase(KinematicState playerPosition, Vector2D pacmanCenter, Ghost currentGhost) {
        int xTileBlinky = (int) Math.floor(blinkyGhost.getCenter().getX() / MazeCreator.RESIZING_FACTOR);
        int yTileBlinky = (int) Math.floor(blinkyGhost.getCenter().getY() / MazeCreator.RESIZING_FACTOR);
        int xTilePacman = (int) Math.floor(pacmanCenter.getX() / MazeCreator.RESIZING_FACTOR);
        int yTilePacman = (int) Math.floor(pacmanCenter.getY() / MazeCreator.RESIZING_FACTOR);

        Direction playerDirection = playerPosition.getDirection();

        switch (playerDirection) {
            case UP:
                yTilePacman -= GRID_AHEAD;
                break;
            case DOWN:
                yTilePacman += GRID_AHEAD;
                break;
            case LEFT:
                xTilePacman -= GRID_AHEAD;
                break;
            case RIGHT:
                xTilePacman += GRID_AHEAD;
                break;
        }

        int xDiff = xTilePacman - xTileBlinky;
        int yDiff = yTilePacman - yTileBlinky;

        int xTileInky = xTileBlinky + 2*xDiff;
        int yTileInky = yTileBlinky + 2*yDiff;

        return new Vector2D(xTileInky * MazeCreator.RESIZING_FACTOR, yTileInky * MazeCreator.RESIZING_FACTOR);
    }

    public void setBlinkyGhost(ABlinkyGhost blinkyGhost) {
        this.blinkyGhost = blinkyGhost;
    }
}
