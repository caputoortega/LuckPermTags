package ar.com.caputo.lptags;

import java.util.UUID;

import net.luckperms.api.LuckPermsProvider;

public class ShortLPCall {

    /**
     * Retrieves the prefix for the player's main (highest weight) group.
     * @param uuid
     * @return unparsed prefix
     */
    public static final String getPlayerGroupPrefix(UUID uuid) {
        
        String prefix = LuckPermsProvider.get().getUserManager().getUser(uuid).getCachedData().getMetaData().getPrefix();
        return prefix != null ? prefix : "";
    }

    /**
     * Retrieves the pre-defined username colour
     * for the player's main (highest weight) group
     * @param uuid
     * @return unparsed username colour
     */
    public static String getPlayerGroupUsernameColour(UUID uuid) {
        String usernameColour = LuckPermsProvider.get().getUserManager().getUser(uuid).getCachedData().getMetaData().getMetaValue("username-color");
        return usernameColour != null ? usernameColour : "";
    } 
    
}
