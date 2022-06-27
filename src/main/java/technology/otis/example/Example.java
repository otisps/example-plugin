package technology.otis.example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import technology.otis.example.commands.CreativeCommand;
import technology.otis.example.commands.ReloadCommand;
import technology.otis.example.db.MySQL;
import technology.otis.example.db.SQL;
import technology.otis.example.db.SQLGetter;
import technology.otis.example.db.SQLite;
import technology.otis.example.listeners.AchievementListener;
import technology.otis.example.listeners.JoinListener;

import java.sql.SQLException;

public final class Example extends JavaPlugin {
    private static Example instance;
    public SQL sql;
    public SQLGetter sqlGetter;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        getCommand("reloadexample").setExecutor(new ReloadCommand());
        getCommand("creative").setExecutor(new CreativeCommand());
        this.sql = new MySQL();
        this.sqlGetter = new SQLGetter();

        try {
            sql.connect();
        } catch (ClassNotFoundException | SQLException e ) {
            this.sql = new SQLite();
            try {
                sql.connect();
            } catch (ClassNotFoundException | SQLException ex) {
                Bukkit.getLogger().info("Database is not connected, please update config.yml, check your connection and then try again.");
                getServer().getPluginManager().disablePlugin(this);
                getPluginLoader().disablePlugin(this);
                return;
            }
        }

        if(sql.isConnected()){
            getServer().getLogger().info("Database and Example Plugin are connected!");
            sqlGetter.createTable();
        }


        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new AchievementListener(), this);

        getServer().getLogger().info("Example Plugin Successfully Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("Example Plugin Successfully Disabled");
        if(sql.isConnected()) {
            sql.disconnect();
        }
    }

    public static Example getInstance() {
        return instance;
    }
}
