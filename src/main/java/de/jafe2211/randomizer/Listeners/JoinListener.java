package de.jafe2211.randomizer.Listeners;

import de.jafe2211.randomizer.Randomizer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.sendMessage(Randomizer.prefix() + " Thanks for using Randomizer");
        p.sendMessage(Randomizer.prefix() + " If you need help on how to get started run" + ChatColor.AQUA + " /rm getstarted");
    }
}
