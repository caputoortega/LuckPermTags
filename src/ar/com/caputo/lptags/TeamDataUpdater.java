package ar.com.caputo.lptags;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.luckperms.api.LuckPermsProvider;
import static ar.com.caputo.lptags.LuckPermsTags.colorise;

public class TeamDataUpdater {

    private static final Set<Team> rankTeams = new HashSet<Team>();

    public static void updateTeams() {

        if(LuckPermsProvider.get().getGroupManager().getLoadedGroups().size() != rankTeams.size())
            loadTeams();
        
        rankTeams.forEach(team -> {
            team.setPrefix(colorise(ShortLPCall.getGroupPrefix(team.getName())));
        });

    }

    public static void loadTeams() {

        Scoreboard serverScoreboard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
        Set<String> existingTeamNames = serverScoreboard.getTeams().stream().map(team -> team.getName()).collect(Collectors.toSet());

        LuckPermsProvider.get().getGroupManager().getLoadedGroups().forEach(group -> {

            Team groupTeam = null;
            String groupName = group.getName();

            if(!existingTeamNames.contains(groupName)) {

                groupTeam = serverScoreboard.registerNewTeam(groupName);
                groupTeam.setPrefix(colorise(ShortLPCall.getGroupPrefix(groupName)));

            } else {

                groupTeam = serverScoreboard.getTeam(group.getName());

            }

            rankTeams.add(groupTeam);

        });

    }

    public static Team getTeam(String name) {
        return rankTeams.stream().filter(team -> team.getName().equals(name)).findFirst().orElse(null);
    }

}
