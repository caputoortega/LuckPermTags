package ar.com.caputo.lptags;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import ar.com.caputo.lptags.handles.HandleGroupUpdate;
import ar.com.caputo.lptags.handles.HandleJoin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.EventBus;

public class LuckPermTags extends JavaPlugin {
    
    private EventBus eventBus;

    @Override
    public void onEnable() {


        if(getServer().getPluginManager().getPlugin("LuckPerms") == null) {
            getLogger().log(Level.SEVERE, "No LuckPerms instance has been found. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            eventBus = LuckPermsProvider.get().getEventBus();
        }

        initHandlers();

    }

    @Override
    public void onDisable() {

    }

    private void initHandlers() {

        getServer().getPluginManager().registerEvents(((Listener) new HandleJoin(this)), this);
        getServer().getPluginManager().registerEvents(((Listener) new HandleGroupUpdate(this)), this);

    }

    public final EventBus getLPEventBus() {
        return this.eventBus;
    }

    public static final String formatted(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    } 

}
