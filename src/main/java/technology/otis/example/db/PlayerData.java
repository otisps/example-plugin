package technology.otis.example.db;

/**
 * Represents the playerdata table objects, might be called entity?
 */
public class PlayerData {
    private String name;
    private String playerId;
    private int points;

    public PlayerData(String name, String playerId, int points) {
        this.name = name;
        this.playerId = playerId;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
