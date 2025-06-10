package de.jafe2211.randomizer.Listeners;

import de.jafe2211.randomizer.Randomizer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.EventListener;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.sendMessage(Randomizer.prefix() + " Danke fürs Benutzen von Randomizer");
    }
}
