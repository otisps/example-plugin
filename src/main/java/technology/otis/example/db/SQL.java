package technology.otis.example.db;


import java.sql.Connection;
import java.sql.SQLException;

public abstract class SQL {
    public abstract Boolean isConnected();
    public abstract void connect() throws ClassNotFoundException, SQLException;
    public abstract void disconnect();

    public abstract Connection getConnection();
}

