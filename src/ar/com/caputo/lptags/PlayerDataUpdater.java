package ar.com.caputo.lptags;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerDataUpdater {
    
    /**
     * Retrieves full colorised profile name for
     * a player given their UUID
     * @param uuid 
     * @return colorised profile name string
     */
    private static String getFullProfilePrefix(UUID uuid) {
        return LuckPermsTags.colorise(
                ShortLPCall.getPlayerGroupPrefix(uuid) +
                ShortLPCall.getPlayerGroupUsernameColour(uuid));
    }

    /**
     * Updates the profile name for the given <code>Player</code>
     * @param player
     */
    public static void updatePrefix(Player player) {
        player.setPlayerListName(
                getFullProfilePrefix(player.getUniqueId()) +            
                player.getName()
               );
    }

    /**
     * Updates the profile name for the given UUID
     * @param uuid
     */
    public static void updatePrefix(UUID uuid) {

        Player player = Bukkit.getServer().getPlayer(uuid);

        if(player != null && player.isOnline()) 
            player.setPlayerListName(
                getFullProfilePrefix(player.getUniqueId()) +            
                player.getName()
            );

    }

}
