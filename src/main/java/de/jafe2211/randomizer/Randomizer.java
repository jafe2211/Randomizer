package de.jafe2211.randomizer;

import de.jafe2211.randomizer.Commands.RandomizerCmd;
import de.jafe2211.randomizer.Listeners.JoinListener;
import de.jafe2211.randomizer.Listeners.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Randomizer extends JavaPlugin {

    public Randomizer Randomizer;


    @Override
    public void onEnable() {
        load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void load(){

        Randomizer = this;

        this.saveDefaultConfig();

        loadCommands();
        loadListeners();
        if(Bukkit.getOnlinePlayers().isEmpty()){
            Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        }
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.GRAY + "<-----------------[" + ChatColor.AQUA + ChatColor.BOLD + "Randomizer" + ChatColor.GRAY + "]----------------->");
            p.sendMessage(ChatColor.GRAY + " Thanks for using " + ChatColor.AQUA + "Randomizer V1.0");
            p.sendMessage(ChatColor.GRAY + " If you need help geting started type " + ChatColor.AQUA + "/rm getstarted");
        }
    }

    public void loadCommands(){
        getCommand("Randomizer").setExecutor(new RandomizerCmd());
    }

    public void loadListeners(){
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    public static List<Material> remaining = new ArrayList<>();

    public static String prefix(){
        return ChatColor.GRAY + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Randomizer" + ChatColor.GRAY + "]";
    }
}
