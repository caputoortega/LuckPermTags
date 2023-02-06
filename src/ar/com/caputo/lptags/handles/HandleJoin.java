package ar.com.caputo.lptags.handles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import ar.com.caputo.lptags.LuckPermsTags;
import ar.com.caputo.lptags.PlayerDataUpdater;

import static ar.com.caputo.lptags.LuckPermsTags.ConfigurationNode;

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
        
        StringBuilder tablistHeader = new StringBuilder();
        lpt.getConfig().getStringList(ConfigurationNode.TABLIST_HEADER.get()).forEach(line -> {
            tablistHeader.append(line);
        });

        StringBuilder tablistFooter = new StringBuilder();
        lpt.getConfig().getStringList(ConfigurationNode.TABLIST_FOOTER.get()).forEach(line -> {
            tablistFooter.append(line);
        });

        player.setPlayerListHeader(LuckPermsTags.colorise(tablistHeader.toString()));
        player.setPlayerListFooter(LuckPermsTags.colorise(tablistFooter.toString()));

        PlayerDataUpdater.updatePrefix(player);

    }


}
