package me.AxiusDevelopment.TWoNChatPrefixer;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;

public class Prefixer
extends BukkitRunnable {
	
    Chat chat;
    Main plugin;

    public Prefixer(Main plugin) {
        RegisteredServiceProvider<Chat> rsp = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        this.chat = (Chat)rsp.getProvider();
    }

	public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
        	chat.setPlayerPrefix(p, "");
            //Normal:
        	int l = p.getLevel();
        	String lvl = "";
        	
        	if(l <= 100) {
        		if(l > 0 && l <= 20) lvl = "§b" + l;
        		if(l > 20 && l <= 40) lvl = "§a" + l;
        		if(l > 40 && l <= 60) lvl = "§e" + l;
        		if(l > 60 && l <= 80) lvl = "§6" + l;
        		if(l > 80 && l <= 100) lvl = "§c" + l;
        	}
        	
        	if(l == 101) {
        		String cfg = plugin.getConfig().getString(p.getName());
        		if(cfg.equals("")||cfg.isEmpty()) {
        			lvl = "§" + plugin.getConfig().getString("Default");
        		}
        		else
        		{
        			if(cfg.length() > 1) {
        				String[] colors = cfg.split("");
        				for(int i = 0; i == cfg.length(); i++) {
        					lvl = lvl + "§" + colors[i];
        				}
        			}
        			else
        			{
        				lvl = "§" + cfg + l;
        			}
        		}
        	}
        	
        	if(l == 0) {
        		lvl = "§90";
        	}
        	
        	if(l > 101) {
        		lvl = "§c§lPLUGIN UPDATE REQUIRED";
        	}
        	
            chat.setPlayerPrefix(p, "§8LVL" + lvl + " §r" + chat.getPlayerPrefix(p));
        }
    }
}
