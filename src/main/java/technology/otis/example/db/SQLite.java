package technology.otis.example.db;

import technology.otis.example.Example;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite extends SQL{
    private String database;
    private Connection connection;
    public SQLite(){
        this.database = Example.getInstance().getConfig().getString("sqlite.database");
    }

    @Override
    public Boolean isConnected() {
        return (connection == null ? false : true);
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        File dataFile = new File(Example.getInstance().getDataFolder(), database + ".db");
        if(!dataFile.exists()){
            try{
                dataFile.createNewFile();
            } catch (IOException e){
                Example.getInstance().getLogger().info("Failed to create new sqlite db file!");
            }
        }
        if(isConnected()) return;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + dataFile);
        return;
    }


    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
