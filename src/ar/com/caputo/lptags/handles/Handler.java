package ar.com.caputo.lptags.handles;

import org.bukkit.event.Listener;

import ar.com.caputo.lptags.LuckPermTags;

public class Handler implements Listener {

    protected LuckPermTags lpt;

    protected Handler(LuckPermTags lpt) {
        this.lpt = lpt;
    }
    
}
