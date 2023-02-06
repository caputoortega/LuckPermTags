package ar.com.caputo.lptags;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerDataUpdater {
    
    private static String getFullProfilePrefix(UUID uuid) {
        return LuckPermsTags.colorise(
                ShortLPCall.getPlayerGroupPrefix(uuid) +
                ShortLPCall.getPlayerGroupUsernameColour(uuid));
    }

    public static void updatePrefix(Player player) {
        player.setPlayerListName(
                getFullProfilePrefix(player.getUniqueId()) +            
                player.getName()
               );
    }

    public static void updatePrefix(UUID uuid) {

        Player player = Bukkit.getServer().getPlayer(uuid);

        if(player != null && player.isOnline()) 
            player.setPlayerListName(
                getFullProfilePrefix(player.getUniqueId()) +            
                player.getName()
            );

    }

}
