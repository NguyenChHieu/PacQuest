package pacman.model.level;

public interface GhostScoreHandler {
    void incrementGhostEatenCount();
    int getGhostEatenCount();
    void addScore(int score);
}
