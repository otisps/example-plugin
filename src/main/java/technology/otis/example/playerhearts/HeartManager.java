package technology.otis.example.playerhearts;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HeartManager {


    /**
     * test one
     * @param player target player
     * @param value value
     */
    public void manipulateHeats(Player player, double value){
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(value);
    }

}
