package technology.otis.example.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import technology.otis.example.Example;
import technology.otis.example.playerhearts.HeartManager;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!Example.getInstance().sql.isConnected()) return;
        Example.getInstance().sqlGetter.createPlayer(event.getPlayer());
        HeartManager heartManager = new HeartManager();
        heartManager.manipulateHeats(event.getPlayer(), 20);
    }

}
