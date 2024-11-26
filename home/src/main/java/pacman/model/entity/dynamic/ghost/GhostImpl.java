package pacman.model.entity.dynamic.ghost;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.chasestrat.*;
import pacman.model.entity.dynamic.ghost.state.*;
import pacman.model.entity.dynamic.physics.*;
import pacman.model.level.Level;

import java.util.*;

/**
 * Concrete implementation of Ghost entity in Pac-Man Game
 */
public class GhostImpl implements Ghost {

    public static final int minimumDirectionCount = 8;
    private final Layer layer = Layer.FOREGROUND;
    private final Image image;
    private final BoundingBox boundingBox;
    private final Vector2D startingPosition;
    private final Vector2D targetCorner;
    private KinematicState kinematicState;
    private GhostMode ghostMode;
    private Vector2D targetLocation;
    private Vector2D playerPosition;
    private Direction currentDirection;
    private Set<Direction> possibleDirections;
    private Map<GhostMode, Double> speeds;
    private int currentDirectionCount = 0;

    protected ChaseStrategy chaseStrategy;
    private IGhostState state;
    private int freezeCount;

    public GhostImpl(Image image,
                     BoundingBox boundingBox,
                     KinematicState kinematicState,
                     GhostMode ghostMode,
                     Vector2D targetCorner) {
        this.image = image;
        this.boundingBox = boundingBox;
        this.kinematicState = kinematicState;
        this.startingPosition = kinematicState.getPosition();
        this.ghostMode = ghostMode;
        this.possibleDirections = new HashSet<>();
        this.targetCorner = targetCorner;
        this.targetLocation = getTargetLocation();
        this.currentDirection = null;
        this.state = new GhostNormalState(this);

        // DEFAULT CHASE PACMAN
        this.chaseStrategy = new BlinkyStrategy();
    }

    @Override
    public void setSpeeds(Map<GhostMode, Double> speeds) {
        this.speeds = speeds;
    }

    @Override
    public Image getImage() {
        if (state instanceof GhostNormalState) {
            return image;
        }
        return state.getImage();
    }

    @Override
    public void update() {
        if (freezeCount > 0) {
            freezeCount--;
            return;
        }

        state.update(this);
    }

    @Override
    public void setGhostMode(GhostMode ghostMode) {
        this.ghostMode = ghostMode;
        this.kinematicState.setSpeed(speeds.get(ghostMode));
        // ensure direction is switched
        this.currentDirectionCount = minimumDirectionCount;
    }

    @Override
    public boolean collidesWith(Renderable renderable) {
        return boundingBox.collidesWith(kinematicState.getSpeed(), kinematicState.getDirection(), renderable.getBoundingBox());
    }

    @Override
    public void collideWith(Level level, Renderable renderable) {
        state.collides(level, renderable);
    }

    @Override
    public void update(KinematicState playerPosition, Vector2D center) {
        this.playerPosition = this.chaseStrategy.chase(playerPosition, center, this);
    }

    @Override
    public Vector2D getPositionBeforeLastUpdate() {
        return this.kinematicState.getPreviousPosition();
    }

    @Override
    public double getHeight() {
        return this.boundingBox.getHeight();
    }

    @Override
    public double getWidth() {
        return this.boundingBox.getWidth();
    }

    @Override
    public Vector2D getPosition() {
        return this.kinematicState.getPosition();
    }

    @Override
    public void setPosition(Vector2D position) {
        this.kinematicState.setPosition(position);
    }

    @Override
    public Layer getLayer() {
        return this.layer;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    @Override
    public void reset() {
        // return ghost to starting position
        this.kinematicState = new KinematicStateImpl.KinematicStateBuilder()
                .setPosition(startingPosition)
                .build();
        this.boundingBox.setTopLeft(startingPosition);
        this.ghostMode = GhostMode.SCATTER;
        this.currentDirectionCount = minimumDirectionCount;
        this.state = new GhostNormalState(this);
    }

    @Override
    public Direction getDirection() {
        return this.kinematicState.getDirection();
    }

    @Override
    public Vector2D getCenter() {
        return new Vector2D(boundingBox.getMiddleX(), boundingBox.getMiddleY());
    }

    protected void setChaseStrategy(ChaseStrategy chaseStrategy) {
        this.chaseStrategy = chaseStrategy;
    }

    @Override
    public Vector2D getTargetCorner() {
        return this.targetLocation;
    }

    @Override
    public Vector2D getTargetLocation() {
        return switch (this.ghostMode) {
            case CHASE -> this.playerPosition;
            case SCATTER -> this.targetCorner;
            case FRIGHTENED -> null;
        };
    }

    public void setTargetLocation(Vector2D targetLocation) {
        this.targetLocation = targetLocation;
    }

    @Override
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    @Override
    public KinematicState getKinematicState() {
        return kinematicState;
    }

    @Override
    public void setPossibleDirections(Set<Direction> possibleDirections) {
        this.possibleDirections = possibleDirections;
    }

    @Override
    public Set<Direction> getPossibleDirections() {
        return possibleDirections;
    }

    @Override
    public void setState(IGhostState state) {
        this.state = state;
    }

    @Override
    public IGhostState getState() {
        return state;
    }

    @Override
    public int getCurrentDirectionCount() {
        return currentDirectionCount;
    }

    @Override
    public void setCurrentDirectionCount(int currentDirectionCount) {
        this.currentDirectionCount = currentDirectionCount;
    }

    @Override
    public void freeze(int duration) {
        this.freezeCount = duration;
    }
}
