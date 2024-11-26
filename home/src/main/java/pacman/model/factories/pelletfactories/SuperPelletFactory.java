package pacman.model.factories.pelletfactories;

import pacman.ConfigurationParseException;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.collectable.SuperPellet;

public class SuperPelletFactory extends PelletFactory {
    private static final double OFFSET = -8;

    @Override
    public Renderable createRenderable(
            Vector2D position
    ) {
        try {
            double newX = position.getX() + OFFSET;
            double newY = position.getY() + OFFSET;
            position = new Vector2D(newX, newY);

            BoundingBox boundingBox = new BoundingBoxImpl(
                    position,
                    PELLET_IMAGE.getHeight()*2,
                    PELLET_IMAGE.getWidth()*2
            );

            return new SuperPellet(
                    boundingBox,
                    layer,
                    PELLET_IMAGE,
                    NUM_POINTS * 5
            );

        } catch (Exception e) {
            throw new ConfigurationParseException(
                    String.format("Invalid pellet configuration | %s", e));
        }
    }
}
