package ar.com.caputo.lptags;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(
    id = "lptags",
    name = "LuckPermTags",
    version = "1.0-VELOCITY",
    description = "LuckPermTags handled by proxy",
    authors = {"bernardMC"}
)
public class LPTags {
    
    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public LPTags(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    

}
