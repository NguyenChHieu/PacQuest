Strategy Pattern:
+ Context: Ghost
+ Strategy: ChaseStrategy
+ ConcreteStrategy: BlinkyStrategy, ClydeStrategy, InkyStrategy, PinkyStrategy

State Pattern:
+ Context: Ghost
+ State: IGhostState
+ ConcreteState: GhostNormalState, GhostFrightenedState

Decorator Pattern:
+ Component: PacmanComponent
+ ConcreteComponent: Pacman
+ Decorator: PacmanDecorator
+ ConcreteDecorator: BasePacmanDecorator, ImmunityDecorator