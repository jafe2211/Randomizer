package de.jafe2211.randomizer.Commands;

import de.jafe2211.randomizer.Randomizer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomizerCmd implements CommandExecutor, TabCompleter {

    public static List<Material> remaining = new ArrayList<>();

    public void fillRemainingList(){
        remaining.clear();

        for(Material material : Material.values()){
            if(!(material.isItem())) continue;

            remaining.add(material);
        }
    }

    public void generateBlockPaletteForUser(Player player){
        for(Material material: Material.values()){
            if(remaining.isEmpty()) continue;
            if(!material.isBlock()) continue;

            Random r = new Random();
            int random = 0;

            if(remaining.size() != 1){
                random = r.nextInt(remaining.size() -1);
            }

            Randomizer.getPlugin(Randomizer.class).getConfig().set("randomTable." + player.getName() + "." + material.toString(), remaining.get(random).toString());
            remaining.remove(random);
        }
        Randomizer.getPlugin(Randomizer.class).saveConfig();
    }

    public void generateBlockPaletteForTeam(Team team){
        for(Material material: Material.values()){
            if(remaining.isEmpty()) continue;
            if(!material.isBlock()) continue;
            Random r = new Random();
            int random = 0;

            if(remaining.size() != 1){
                random = r.nextInt(remaining.size() -1);
            }

            Randomizer.getPlugin(Randomizer.class).getConfig().set("randomTable." + team.getName() + "." + material.toString(), remaining.get(random).toString());
            remaining.remove(random);
        }
        Randomizer.getPlugin(Randomizer.class).saveConfig();
    }

    public void generateBlockPalette(){
        for(Material material: Material.values()){
            if(remaining.isEmpty()) continue;
            if(!material.isBlock()) continue;
            Random r = new Random();
            int random = 0;

            if(remaining.size() != 1){
                random = r.nextInt(remaining.size() -1);
            }

            Randomizer.getPlugin(Randomizer.class).getConfig().set("randomTable." + material.toString(), remaining.get(random).toString());
            remaining.remove(random);
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
                    for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                        generateBlockPaletteForUser(onlinePlayer);
                    }
                }

                if (Objects.equals(Randomizer.getPlugin(Randomizer.class).getConfig().getString("mode"), "team")) {
                    for(Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()){
                        generateBlockPaletteForTeam(team);
                    }
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
            if(args[0].equals("getstarted")){
                p.sendMessage(Randomizer.prefix() + " First chose the mode you want to play in with " + ChatColor.AQUA + "/rm mode <MODE>");
                p.sendMessage(Randomizer.prefix() + " then shuffle the block drops with " + ChatColor.AQUA + "/rm shuffle");
                p.sendMessage(Randomizer.prefix() + " To start it then run " + ChatColor.AQUA + "/rm start");
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
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("randomTable", null);
                        Randomizer.getPlugin(Randomizer.class).saveConfig();
                        p.sendMessage(Randomizer.prefix() + " Switched mode to player!");
                        break;

                    case "single":
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("mode", "single");
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("randomTable", null);
                        Randomizer.getPlugin(Randomizer.class).saveConfig();
                        p.sendMessage(Randomizer.prefix() + " Switched mode to single!");
                        break;

                    case "team":
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("mode", "team");
                        Randomizer.getPlugin(Randomizer.class).getConfig().set("randomTable", null);
                        Randomizer.getPlugin(Randomizer.class).saveConfig();
                        p.sendMessage(Randomizer.prefix() + " Switched mode to team!");
                        break;
                }
                p.sendMessage(Randomizer.prefix() + " please run " + ChatColor.AQUA + "/rm shuffle" + ChatColor.GRAY);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 1){
            return List.of("mode", "start", "stop", "shuffle", "getstarted");
        }
        if(args.length == 2){
            return List.of("single", "team", "player");
        }

        return List.of();
    }
}
