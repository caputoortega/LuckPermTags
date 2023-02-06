package ar.com.caputo.lptags.handles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import ar.com.caputo.lptags.LuckPermsTags;
import ar.com.caputo.lptags.PlayerDataUpdater;

public class HandleJoin extends Handler {

    public HandleJoin(LuckPermsTags lpt) {
        super(lpt);
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void join(PlayerJoinEvent evt) {

        Player player = evt.getPlayer();
        
        player.setPlayerListHeader(LuckPermsTags.colorise("\n&fWelcome to &a&lA Secret Project\n"));
        PlayerDataUpdater.updatePrefix(player);

    }


}
