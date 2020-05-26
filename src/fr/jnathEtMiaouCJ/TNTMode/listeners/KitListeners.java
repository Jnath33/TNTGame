package fr.jnathEtMiaouCJ.TNTMode.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;

import fr.jnath.Utils.Utils;
import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.ItemPlace;

public class KitListeners implements Listener{
	
	Main _main;
	
	public KitListeners(Main main) {
		_main=main;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getItem()==null)return;
		if(player.getLocation().getY()>55) {
			player.sendMessage("Allez plus bas pour utiliser ceci.");
			return;
		}
		ItemStack it = event.getItem();
		Location loc = player.getLocation();
		float dir = loc.getYaw();
		int mov = 0;
		if(it.getItemMeta().getDisplayName().contains("§cCanon build")) {
			if(it.getAmount()==2) {
				new ItemPlace(Utils.createItem("§cCanon build", Material.STICK, 1), 5).setItem(player);
			} else{
				player.getInventory().clear(5);
			}
			if(dir>=-45&&dir<45) {
				mov = 0;
			}else if(dir>=45&&dir<135) {
				mov = 6;
			}else if(dir>=135||dir<-135) {
				mov = 12;
			}else if(dir<=-45&&dir>-135) {
				mov = 18;
			}
			Utils.copy(new Vector(755,201,-284-mov), new Vector(750,204,-279-mov), new BlockVector(new Vector(loc.getX(), loc.getY()-1, loc.getZ())), BukkitUtil.getLocalWorld(_main.world));
		}else if(it.getItemMeta().getDisplayName().contains("§cTower")) {
			System.err.println("test");
			if(it.getAmount()==2) {
				new ItemPlace(Utils.createItem("§cTower", Material.STICK, 1), 6).setItem(player);
			} else{
				player.getInventory().clear(6);
			}
			Utils.copy(new Vector(748,202,-298), new Vector(742,210,-305), new BlockVector(new Vector(loc.getX()-3, loc.getY()+2, loc.getZ()-3)), BukkitUtil.getLocalWorld(_main.world));
		}else if(it.getItemMeta().getDisplayName().contains("§cTNT cube(3×3)")) {
			Utils.copy(new Vector(745,201,-278), new Vector(743,203,-280), new BlockVector(new Vector(loc.getX()-1, loc.getY()+2, loc.getZ()-1)), BukkitUtil.getLocalWorld(_main.world));
			player.getInventory().clear(7);
		}else if(it.getItemMeta().getDisplayName().contains("§cTNT cube(7×7)")) {
			Utils.copy(new Vector(745,201,-286), new Vector(739,207,-292), new BlockVector(new Vector(loc.getX()-3, loc.getY()+2, loc.getZ()-3)), BukkitUtil.getLocalWorld(_main.world));
			player.getInventory().clear(8);
		}
	}
}
