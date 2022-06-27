package technology.otis.example.db;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLlite extends SQL{


    @Override
    public Boolean isConnected() {
        return null;
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
