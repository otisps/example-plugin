package technology.otis.example.db;

import org.bukkit.entity.Player;
import technology.otis.example.Example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGetter {

    /**
     * This procedure creates the player data table in database
     */
    public void createTable() {
        PreparedStatement statement;
        try{
            statement = Example.getInstance().sql.getConnection()
                    .prepareStatement("CREATE TABLE  IF NOT EXISTS playerdata "
                    + "(name VARCHAR(64), playerUUID VARCHAR(64), points INT(64), PRIMARY KEY (playerUUID))");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Shouldn't be possible, as if connection issues exists, we don't load...
        }
    }

    /**
     * Function to see if a target player contains in the playerdata table
     * @param playerId target player's uuid
     * @return true or false
     */
    public boolean tableContains(String playerId){
        try {
            PreparedStatement statement = Example.getInstance().sql.getConnection()
                    .prepareStatement("SELECT * FROM playerdata WHERE playerUUID=?");
            statement.setString(1, playerId);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                return true;
            }
            return false;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * If the player isn't in the database, this procedure adds them to it
     * @param player target player
     */
    public void createPlayer(Player player){
        try{
            String uuid = player.getUniqueId().toString();
            String name = player.getName();
            boolean exist = tableContains(uuid);
            if(!exist){
                PreparedStatement statement = Example.getInstance().sql.getConnection()
                        .prepareStatement("INSERT INTO playerdata VALUES(?, ?, ?)");
                statement.setString(1, name);
                statement.setString(2, uuid);
                statement.setInt(3, 0); // No initial points
                statement.executeUpdate();
                return;
            }
        } catch (SQLException e){
            e.printStackTrace(); // Shouldn't be possible, as if connection issues exist, we don't load
        }
    }


    /**
     * Function which queries the MySQL database for a players points
     * @param playerId target player's uuid
     * @return The target player's points
     */
    public int getPoints(String playerId){
        try {
            PreparedStatement statement = Example.getInstance().sql.getConnection()
                    .prepareStatement("SELECT points FROM playerdata WHERE playerUUID=?");
            statement.setString(1, playerId);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                return results.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Procedure to add a point to the total of the target player
     * @param playerId target player's uuid
     */
    public void addPoint(String playerId){
        try {
            int points = getPoints(playerId);
            points++;
            PreparedStatement statement = Example.getInstance().sql.getConnection()
                    .prepareStatement("UPDATE playerdata SET points=? WHERE playerUUID=?");
            statement.setInt(1, points);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Procedure to allow the bruteforce setting of points
     * @param playerId target player's uuid
     * @param points the new integer value for target player's points
     */
    public void setPoints(String playerId, int points){
        // TODO: THIS
    }


}
