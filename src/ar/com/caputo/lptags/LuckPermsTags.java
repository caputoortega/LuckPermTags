package ar.com.caputo.lptags;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import ar.com.caputo.lptags.handles.HandleGroupUpdate;
import ar.com.caputo.lptags.handles.HandleJoin;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.EventBus;

public class LuckPermsTags extends JavaPlugin {

    private static final Set<Team> rankTeams = new HashSet<Team>();

    private String tablistHeader;
    private String tablistFooter;

    public static enum ConfigurationNode {
        TABLIST_HEADER("tablist-header"),
        TABLIST_FOOTER("tablist-footer");

        String name;

        ConfigurationNode(String name) {
            this.name = name;
        }

        public String get() {
            return this.name;
        }

    }
    
    /**
     * LuckPerm's Event Bus
     */
    private EventBus eventBus;
    /**
     * RegEx pattern for later parsing of hexadecimal
     * colours
     */
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    /**
     * Hooks up the LuckPerm's Event Bus to the server
     * and registers all plugin handlers.
     */
    @Override
    public void onEnable() {

        if(getServer().getPluginManager().getPlugin("LuckPerms") == null) {
            getLogger().log(Level.SEVERE, "No LuckPerms instance has been found. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            eventBus = LuckPermsProvider.get().getEventBus();
        }

        this.saveDefaultConfig();
        initTeams();
        initHandlers();
        initTablistDetails();

    }

    private void initTeams() {

        Scoreboard serverScoreboard = getServer().getScoreboardManager().getMainScoreboard();

        Set<String> existingTeamNames = serverScoreboard.getTeams().stream().map(team -> team.getName()).collect(Collectors.toSet());

        LuckPermsProvider.get().getGroupManager().getLoadedGroups().forEach(group -> {

            Team groupTeam = null;

            if(!existingTeamNames.contains(group.getName())) {

                groupTeam = serverScoreboard.registerNewTeam(group.getName());
                String groupPrefix = group.getCachedData().getMetaData().getPrefix();
                if(groupPrefix != null)  
                    groupTeam.setPrefix(colorise(groupPrefix));
            } else {

                groupTeam = serverScoreboard.getTeam(group.getName());

            }

            rankTeams.add(groupTeam);

        });


    }

    public static Team getTeam(String name) {
        return rankTeams.stream().filter(team -> team.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Loads tablist's header and footer details
     * from configuration file
     */
    private void initTablistDetails() {

        StringBuilder tablistHeader = new StringBuilder();
        StringBuilder tablistFooter = new StringBuilder();
        List<String> tablistHeaderList = getConfig().getStringList(ConfigurationNode.TABLIST_HEADER.get());
        List<String> tablistFooterList = getConfig().getStringList(ConfigurationNode.TABLIST_FOOTER.get());
        
        if(tablistHeaderList != null && !tablistHeader.isEmpty()) 
            tablistHeaderList.forEach(line -> {
                tablistHeader.append(line);
            });

        this.tablistHeader = colorise(tablistHeader.toString());

        if(tablistFooterList != null && !tablistHeader.isEmpty())
            tablistFooterList.forEach(line -> {
                tablistFooter.append(line);
            });

        this.tablistFooter = colorise(tablistFooter.toString());


    }

    /**
     * @return colorised tablist header
     */
    public String getTablistHeader() {
        return this.tablistHeader;
    }

    /**
     * @return colorised tablist footer
     */
    public String getTablistFooter() {
        return this.tablistFooter;
    }

    /**
     * Listener registration method. Without this, 
     * the plugin would do nothing.
     */
    private void initHandlers() {

        getServer().getPluginManager().registerEvents(((Listener) new HandleJoin(this)), this);
        getServer().getPluginManager().registerEvents(((Listener) new HandleGroupUpdate(this)), this);

    }

    /**
     * LuckPerms requires you to hook the EventBus to
     * listen for events. 
     * This retrieves the EventBus from LuckPerms stored
     * on <code>eventBus</code> attribute for easy access.
     * @return LuckPerm's Event Bus
     */
    public final EventBus getLPEventBus() {
        return this.eventBus;
    }

    /**
     * LuckPerms allows the usage of HEX colour codes, but Minecraft does
     * not know how to parse them natively.
     * <code>translateHexColorCodes(str)</code> is a way to parse those hex colours
     * into something Minecraft is able to understand.
     * 
     * @param message
     * @return parsed message
     */
    private static String translateHexColorCodes(final String message) {
		final char colorChar = ChatColor.COLOR_CHAR;

		final Matcher matcher = HEX_PATTERN.matcher(message);
		final StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

		while (matcher.find()) {
			final String group = matcher.group(1);

			matcher.appendReplacement(buffer, colorChar + "x"
					+ colorChar + group.charAt(0) + colorChar + group.charAt(1)
					+ colorChar + group.charAt(2) + colorChar + group.charAt(3)
					+ colorChar + group.charAt(4) + colorChar + group.charAt(5));
		}

		return matcher.appendTail(buffer).toString();
	}

    /**
     * Combines <code>translateHexColorCodes(str)</code> with <code>translateAlternateColorCodes(char, str)</code>
     * into a single function so hex colours and native Minecraft
     * colours get parsed into the game
     * @param str the message to colorise
     * @return a colorised message Minecraft can understand!
     */
    public static final String colorise(String str) {
        return translateHexColorCodes(ChatColor.translateAlternateColorCodes('&', str));
    } 

}
