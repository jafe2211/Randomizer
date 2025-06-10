package de.jafe2211.randomizer.Commands;

import de.jafe2211.randomizer.Randomizer;
import org.bukkit.ChatColor;
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
        Randomizer.getPlugin(Randomizer.class).saveConfig();
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
        Randomizer.getPlugin(Randomizer.class).saveConfig();
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
        Randomizer.getPlugin(Randomizer.class).saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {

        if(!(cs instanceof Player)) return true;

        Player p = (Player)cs;
        if(!(args.length > 0)){
            p.sendMessage(Randomizer.prefix() + " Please provide a valid argument!");
            p.sendMessage(Randomizer.prefix() + " If you need help type /randomizer help or /rm help");
            return true;
        }

        if(args.length == 1) {
            if(args[0].equals("shuffle")) {
                fillRemainingList();

                if (Objects.equals(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"), "player")) {
                    generateBlockPaletteForUser(p);
                }

                if (Objects.equals(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"), "team")) {
                    generateBlockPaletteForTeam(p.getScoreboard().getTeam("s"));
                }

                if (Objects.equals(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"), "single")) {
                    generateBlockPalette();
                }
                p.sendMessage(Randomizer.prefix() + " shuffled block pallet!");
            }
            if(args[0].equals("help")){
                p.sendMessage(Randomizer.prefix() + ChatColor.AQUA + " /rm mode MODE" + ChatColor.GRAY + " lets you switch to the desired mode options being: 1. single 2. team 3. player");
                p.sendMessage(Randomizer.prefix() + ChatColor.AQUA + " /rm shuffle" + ChatColor.GRAY + " lets you shuffle the random block pallet");
            }

            if(args[0].equals("start")){
                Randomizer.getPlugin(Randomizer.class).getConfig().set("active", true);
                Randomizer.getPlugin(Randomizer.class).saveConfig();
                p.sendMessage(Randomizer.prefix() + " started Randomizer");
            }
            if(args[0].equals("stop")){
                Randomizer.getPlugin(Randomizer.class).getConfig().set("active", false);
                Randomizer.getPlugin(Randomizer.class).saveConfig();
                p.sendMessage(Randomizer.prefix() + " stoped Randomizer");
            }
        }

        if(args.length == 2){
            if(args[0].equals("mode")){
                switch (args[1]){
                    case "player":
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("mode", "player");
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("partners", null);
                        Randomizer.getPlugin(Randomizer.class).saveConfig();
                        p.sendMessage(Randomizer.prefix() + " Switched mode to player!");
                        break;

                    case "single":
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("mode", "single");
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("partners", null);
                        Randomizer.getPlugin(Randomizer.class).saveConfig();
                        p.sendMessage(Randomizer.prefix() + " Switched mode to single!");
                        break;

                    case "team":
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("mode", "team");
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("partners", null);
                        Randomizer.getPlugin(Randomizer.class).saveConfig();
                        p.sendMessage(Randomizer.prefix() + " Switched mode to team!");
                        break;
                }
                p.sendMessage(Randomizer.prefix() + " please run " + ChatColor.AQUA + "/rm shuffle" + ChatColor.GRAY);
            }
        }
        return false;
    }
}
