package technology.otis.example.db;

import technology.otis.example.Example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private Connection connection;

    public MySQL() {
        Example plugin = Example.getInstance();
        this.host = plugin.getConfig().getString("mysql.host");
        this.port = plugin.getConfig().getString("mysql.port");;
        this.database = plugin.getConfig().getString("mysql.database");;
        this.username = plugin.getConfig().getString("mysql.username");;
        this.password = plugin.getConfig().getString("mysql.password");;
    }

    /**
     * Tests for a null connection
     * @return true if there a connection
     */
    public Boolean isConnected() {
        return (connection == null ? false : true);

    }

    public void connect() throws ClassNotFoundException, SQLException {
        if(isConnected()) return;
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false",
                username, password);
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}



