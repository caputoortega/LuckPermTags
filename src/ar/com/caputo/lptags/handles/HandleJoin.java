package ar.com.caputo.lptags.handles;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import ar.com.caputo.lptags.LuckPermTags;

public class HandleJoin extends Handler {

    public HandleJoin(LuckPermTags lpt) {
        super(lpt);
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void join(PlayerJoinEvent evt) {

    }


}
