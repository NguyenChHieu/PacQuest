package pacman.model.factories;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;

/**
 * Generic factory for Renderables
 */
public interface RenderableFactory {

    /**
     * Instantiates a renderable with the given position
     *
     * @param position starting coordinate position of renderable
     */
    Renderable createRenderable(Vector2D position);
}
