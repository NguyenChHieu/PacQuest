package pacman.model.entity.dynamic.ghost.decorator;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.level.Level;

import java.util.Map;
import java.util.Set;

public abstract class BaseGhostDecorator implements Ghost {
    private final Ghost ghost;

    public BaseGhostDecorator(Ghost ghost) {
        this.ghost = ghost;
    }

    // RENDERABLE

    @Override
    public double getHeight() {
        return ghost.getHeight();
    }

    @Override
    public double getWidth() {
        return ghost.getWidth();
    }

    @Override
    public Vector2D getPosition() {
        return ghost.getPosition();
    }

    @Override
    public Layer getLayer() {
        return ghost.getLayer();
    }

    @Override
    public BoundingBox getBoundingBox() {
        return ghost.getBoundingBox();
    }

    @Override
    public void reset() {
        ghost.reset();
    }

    // DYNAMIC ENTITY

    @Override
    public Vector2D getPositionBeforeLastUpdate() {
        return ghost.getPositionBeforeLastUpdate();
    }

    @Override
    public void setPosition(Vector2D position) {
        ghost.setPosition(position);
    }

    @Override
    public boolean collidesWith(Renderable renderable) {
        return ghost.collidesWith(renderable);
    }

    @Override
    public void collideWith(Level level, Renderable renderable) {
        ghost.collideWith(level, renderable);
    }

    @Override
    public void setPossibleDirections(Set<Direction> possibleDirections) {
        ghost.setPossibleDirections(possibleDirections);
    }

    @Override
    public Direction getDirection() {
        return ghost.getDirection();
    }

    @Override
    public Vector2D getCenter() {
        return ghost.getCenter();
    }

    // OBSERVER
    @Override
    public void update(KinematicState position, Vector2D center) {
        ghost.update(position, center);
    }

    // GHOST
    @Override
    public void setSpeeds(Map<GhostMode, Double> speeds) {
        ghost.setSpeeds(speeds);
    }

    @Override
    public void setGhostMode(GhostMode ghostMode) {
        ghost.setGhostMode(ghostMode);
    }

    @Override
    public Vector2D getTargetCorner() {
        return ghost.getTargetCorner();
    }

    @Override
    public Ghost getGhost() {
        return ghost;
    }

    @Override
    public void setDirection(Direction direction) {
        ghost.setDirection(direction);
    }

    @Override
    public KinematicState getKinematicState() {
        return ghost.getKinematicState();
    }

    @Override
    public Set<Direction> getPossibleDirections() {
        return ghost.getPossibleDirections();
    }

    @Override
    public Direction getCurrentDirection() {
        return ghost.getCurrentDirection();
    }
}
