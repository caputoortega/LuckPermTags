package ar.com.caputo.lptags;

import java.util.logging.Level;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.lkeehl.tagapi.TagAPI;

import ar.com.caputo.lptags.handles.HandleGroupUpdate;
import ar.com.caputo.lptags.handles.HandleJoin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class LuckPermTags extends JavaPlugin {
    
    public static final LuckPerms LUCK_PERMS = LuckPermsProvider.get();

    @Override
    public void onEnable() {

        if(LUCK_PERMS == null) {
            getLogger().log(Level.SEVERE, "No LuckPerms instance has been found. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
        }

        // enable embedded TagAPI
        TagAPI.onEnable(this);

        initHandlers();

    }

    @Override
    public void onDisable() {

        // disable embedded  TagAPI
        TagAPI.onDisable();
        
    }

    private void initHandlers() {

        getServer().getPluginManager().registerEvents(((Listener) new HandleJoin(this)), this);
        getServer().getPluginManager().registerEvents(((Listener) new HandleGroupUpdate(this)), this);

    }



}
