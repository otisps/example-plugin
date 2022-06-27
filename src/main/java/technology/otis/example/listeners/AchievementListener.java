package technology.otis.example.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import technology.otis.example.Example;

public class AchievementListener implements Listener {

    @EventHandler
    public void onAchievement(PlayerAdvancementDoneEvent event){
        if(event.getAdvancement().getDisplay() == null) return;
        Example.getInstance().sqlGetter.addPoint(event.getPlayer().getUniqueId().toString());
    }
}
