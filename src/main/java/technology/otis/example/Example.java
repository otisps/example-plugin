package technology.otis.example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import technology.otis.example.commands.ReloadCommand;
import technology.otis.example.db.MySQL;

import java.sql.SQLException;

public final class Example extends JavaPlugin {
    private static Example instance;
    public MySQL sql;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        getCommand("reloadexample").setExecutor(new ReloadCommand());
        this.sql = new MySQL();
        try {
            sql.connect();
        } catch (ClassNotFoundException | SQLException e ) {
            Bukkit.getLogger().info("Database is not connected, please update config.yml, check your connection and then try again.");
            getServer().getPluginManager().disablePlugin(this);
            getPluginLoader().disablePlugin(this);
            return;
        }

        if(sql.isConnected()){
            getServer().getLogger().info("MySQL Database and Example Plugin are connected!");
        }
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
