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
    
    /**
     * Updates a player's tablist details
     * @param evt
     */
    @EventHandler(priority = EventPriority.LOW)
    public void join(PlayerJoinEvent evt) {

        Player player = evt.getPlayer();

        PlayerDataUpdater.updatePrefix(player);
        player.setPlayerListHeader(lpt.getTablistHeader());
        player.setPlayerListFooter(lpt.getTablistFooter());


    }


}
