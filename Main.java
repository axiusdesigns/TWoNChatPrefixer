package me.AxiusDevelopment.TWoNChatPrefixer;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {
	
    Logger log = Logger.getLogger("TWON");
    boolean custom;
    File cfg = new File(getDataFolder(), "config.yml");

    public void onEnable() {
        this.log.info("TWoNLevelPrefixes Enabled!");
        this.assignPrefix();
        
        if(!cfg.exists()) {
        	log.info("Config not found, creating!");
            saveDefaultConfig();
        }
        
    }

    public void assignPrefix() {
        BukkitTask Task = new Prefixer(this).runTaskTimer((Plugin)this, 20, 20);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, (Runnable)Task, 200, 20);
    }
    
    public boolean onCommand(CommandSender sndr, Command cmd, String label, String[] args) {
    	Player p = Bukkit.getPlayer(sndr.getName());
    	if(label.equalsIgnoreCase("levelprefixcolor") && p.getLevel() == 101) {
    		if(args[0].length() > 1) {
    			sndr.sendMessage("INVALID");
    		}
    		else
    		{
    			if(args[0].contains("[a-fA-F][0-9]+")) {
    				getConfig().set(sndr.getName(), args[0]);
    			}
    		}
    	}
    	return true;
    }
	
}
