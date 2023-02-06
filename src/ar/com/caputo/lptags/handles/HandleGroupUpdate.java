package ar.com.caputo.lptags.handles;

import org.bukkit.Bukkit;
import ar.com.caputo.lptags.LuckPermsTags;
import ar.com.caputo.lptags.PlayerDataUpdater;
import net.luckperms.api.event.group.GroupDataRecalculateEvent;
import net.luckperms.api.event.user.track.UserTrackEvent;

public class HandleGroupUpdate extends Handler {

    /**
     * Initialise subscription to LuckPerms Event Bus
     * since its required to listen for <code>UserTrackEvent</code>
     * and <code>GroupDataRecalculateEvent</code> 
     * @param lpt
     */
    public HandleGroupUpdate(LuckPermsTags lpt) {
        super(lpt);

        lpt.getLPEventBus().subscribe(lpt, UserTrackEvent.class, this::userTrackUpdate);
        lpt.getLPEventBus().subscribe(lpt, GroupDataRecalculateEvent.class, this::groupMetaUpdate);

    }
    
    /**
     * Updates user prefixes when a user gets
     * promoted/demoted
     * @param evt LuckPerms' <code>UserTrackEvent</code>
     */
    public void userTrackUpdate(UserTrackEvent evt) {
        PlayerDataUpdater.updatePrefix(evt.getUser().getUniqueId());
    }

    /**
     * Updates user prefixes on group data update
     * such as prefix changes, messagechanges, etc..
     * @param evt LuckPerms' <code>GroupDataRecalculateEvent</code>
     */
    public void groupMetaUpdate(GroupDataRecalculateEvent evt) {

        String updatedGroupNameNode = "group."+evt.getGroup().getName();

        Bukkit.getServer().getOnlinePlayers().stream()
                          .filter((player -> player.hasPermission(updatedGroupNameNode)))
                          .forEach(player -> PlayerDataUpdater.updatePrefix(player));        

    }

}
