package technology.otis.example.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import technology.otis.example.Example;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) return true;
        Example.getInstance().reloadConfig();
        sender.sendMessage("Config Reloaded.");
        return true;
    }
}
