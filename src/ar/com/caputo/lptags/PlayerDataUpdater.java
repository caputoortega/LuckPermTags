package ar.com.caputo.lptags;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

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

        if(player != null && player.isOnline()) {
            updateNametag(player); 
            player.setPlayerListName(
                    getFullProfilePrefix(player.getUniqueId()) +            
                    player.getName()
            );
        }
    }

    /**
     * Updates the profile name for the given UUID
     * @param uuid
     */
    public static void updatePrefix(UUID uuid) {

        Player player = Bukkit.getServer().getPlayer(uuid);

        if(player != null && player.isOnline()) {
            updateNametag(player);
            player.setPlayerListName(
                getFullProfilePrefix(player.getUniqueId()) +            
                player.getName()
            );
        }

    }

    private static void updateNametag(Player player) {

       Team currentTeam = Bukkit.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(player.getName());

       if(currentTeam != null)
        currentTeam.removeEntry(player.getName());
       
       currentTeam = LuckPermsTags.getTeam(ShortLPCall.getPlayerMainGroup(player.getUniqueId()));
       currentTeam.addEntry(player.getName());

    }

}
