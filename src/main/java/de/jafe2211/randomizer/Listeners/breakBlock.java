package de.jafe2211.randomizer.Listeners;

import de.jafe2211.randomizer.Randomizer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class breakBlock implements Listener {

    public Boolean ex = false;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e ){
        //e.getPlayer().sendMessage(e.getPlayer().getScoreboard().getEntryTeam(e.getPlayer().getName()).getName());
        if(Randomizer.getPlugin(Randomizer.class).getConfig().getBoolean("active")) {
            e.setDropItems(false);
            getMaterial(e.getBlock().getType(), e.getPlayer());
            if(ex){
                e.setDropItems(true);
                ex = false;
                return;
            }
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(getMaterial(e.getBlock().getType(), e.getPlayer())));
        }
    }

    private Material getMaterial(Material mat, Player p){
        Material randomMaterial;
        try{
            if(Randomizer.getPlugin(Randomizer.class).getConfig().get("mode").equals("single")){
                randomMaterial = Material.valueOf(Randomizer.getPlugin(Randomizer.class).getConfig().getString("randomTable." + mat.toString()));
                return randomMaterial;
            }

            if(Randomizer.getPlugin(Randomizer.class).getConfig().get("mode").equals("team")){
                randomMaterial = Material.valueOf(Randomizer.getPlugin(Randomizer.class).getConfig().getString("randomTable." + p.getScoreboard().getEntryTeam(p.getName()).getName() + "." + mat.toString()));
                return randomMaterial;
            }

            randomMaterial = Material.valueOf(Randomizer.getPlugin(Randomizer.class).getConfig().getString("randomTable." + p.getName() + "." + mat.toString()));
            return randomMaterial;
        } catch (Exception e){
            randomMaterial = mat;
            ex = true;
        }
        return randomMaterial;
    }
}
