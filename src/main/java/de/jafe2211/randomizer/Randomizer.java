package de.jafe2211.randomizer;

import de.jafe2211.randomizer.Commands.RandomizerCmd;
import de.jafe2211.randomizer.Listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
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
    }

    public void loadCommands(){
        getCommand("Randomizer").setExecutor(new RandomizerCmd());
    }

    public void loadListeners(){
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    public static List<Material> remaining = new ArrayList<>();

    public static String prefix(){
        return ChatColor.GRAY + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Randomizer" + ChatColor.GRAY + "]";
    }
}
