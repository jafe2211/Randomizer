package de.jafe2211.randomizer.Commands;

import de.jafe2211.randomizer.Randomizer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomizerCmd implements CommandExecutor {

    public static List<Material> remaining = new ArrayList<>();

    public void fillRemainingList(){
        remaining.clear();

        for(Material mat : Material.values()){
            if(!(mat.isItem())) continue;

            remaining.add(mat);
        }
    }

    public void generateBlockPaletteForUser(Player player){
        for(Material mat: Material.values()){
            if(remaining.isEmpty()) continue;
            if(!mat.isBlock()) continue;

            Random r = new Random();
            int rand = 0;

            if(remaining.size() != 1){
                rand = r.nextInt(remaining.size() -1);
            }

            Randomizer.getPlugin(Randomizer.class).getConfig().set("partners." + player.getName() + "." + mat, remaining.get(rand).toString());
            remaining.remove(rand);
        }
        //Randomizer.getPlugin(Randomizer.class).saveConfig();
    }

    public void generateBlockPaletteForTeam(Team team){
        for(Material mat: Material.values()){
            if(remaining.isEmpty()) continue;
            if(!mat.isBlock()) continue;
            Random r = new Random();
            int rand = 0;

            if(remaining.size() != 1){
                rand = r.nextInt(remaining.size() -1);
            }

            Randomizer.getPlugin(Randomizer.class).getConfig().set("partners." + team.getName() + "." + mat, remaining.get(rand).toString());
            remaining.remove(rand);
        }
        //Randomizer.getPlugin(Randomizer.class).saveConfig();
    }

    public void generateBlockPalette(){
        for(Material mat: Material.values()){
            if(remaining.isEmpty()) continue;
            if(!mat.isBlock()) continue;
            Random r = new Random();
            int rand = 0;

            if(remaining.size() != 1){
                rand = r.nextInt(remaining.size() -1);
            }

            Randomizer.getPlugin(Randomizer.class).getConfig().set("partners." + mat, remaining.get(rand).toString());
            remaining.remove(rand);
        }
        //Randomizer.getPlugin(Randomizer.class).saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {

        if(!(cs instanceof Player)) return true;

        Player p = (Player)cs;

        p.sendMessage(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"));

        //fillRemainingList();

       /* if(Objects.equals(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"), "player")){
            generateBlockPaletteForUser(p);
        }

        if(Objects.equals(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"), "team")){
            generateBlockPaletteForTeam(p.getScoreboard().getTeam("s"));
        }

        if(Objects.equals(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"), "single")){
            generateBlockPalette();
        } */
        return false;
    }
}
