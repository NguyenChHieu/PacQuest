package pacman.model.entity.dynamic.player.decorator;

import pacman.model.entity.dynamic.player.Controllable;

public class ImmunityDecorator extends BasePacmanDecorator{
    public ImmunityDecorator(Controllable pacman) {
        super(pacman);
    }
}
