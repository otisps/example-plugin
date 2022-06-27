package technology.otis.example.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import technology.otis.example.Example;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!Example.getInstance().sql.isConnected()) return;
        Example.getInstance().sqlGetter.createPlayer(event.getPlayer());
    }
}
