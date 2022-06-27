package technology.otis.example;

import org.bukkit.plugin.java.JavaPlugin;
import technology.otis.example.commands.ReloadCommand;

public final class Example extends JavaPlugin {
    private static Example instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        getCommand("reloadexample").setExecutor(new ReloadCommand());
        getServer().getLogger().info("Example Plugin Successfully Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("Examole Plugin Successfully Disabled");
    }

    public static Example getInstance() {
        return instance;
    }
}
