package ar.com.caputo.lptags.handles;

import org.bukkit.event.Listener;

import ar.com.caputo.lptags.LuckPermsTags;

public class Handler implements Listener {

    protected LuckPermsTags lpt;

    protected Handler(LuckPermsTags lpt) {
        this.lpt = lpt;
    }
    
}
