package ar.com.caputo.lptags.handles;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import ar.com.caputo.lptags.LuckPermTags;
import net.luckperms.api.event.user.track.UserTrackEvent;

public class HandleGroupUpdate extends Handler {

    public HandleGroupUpdate(LuckPermTags lpt) {
        super(lpt);
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void userTrackUpdate(UserTrackEvent evt) {

    }

}
