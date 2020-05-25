package fr.jnathEtMiaouCJ.TNTMode.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.Kit;

public class Starting extends BukkitRunnable{
	Main _main;
	int time=20;
	public Starting(Main main) {
		_main =main;
	}

	@Override
	public void run() {
		for(Player player : _main.players) {
			player.setLevel(time);
		}
		if(time==20||time==10||time<=5) {
			Bukkit.broadcastMessage(ChatColor.RED+"La partie commence dans "+ChatColor.GREEN+time+ChatColor.GOLD+"s");
		}
		time --;
		if(time==0) {
			for(Player pls : _main.players) {
				if(!_main.playerKit.containsKey(pls)) {
					_main.playerKit.put(pls, Kit.getKit("Basic"));
				}
				pls.teleport(_main.stringToLoc(_main.getConfig().getString("TNTMode.spawn")));
				ItemStack briquet = new ItemStack(Material.FLINT_AND_STEEL);
				ItemMeta briquetM = briquet.getItemMeta();
				briquetM.addEnchant(Enchantment.DURABILITY, 10, false);
				briquet.setItemMeta(briquetM);
				pls.getInventory().clear();
				pls.getInventory().setItem(2, briquet);
				pls.getInventory().setItem(3, new ItemStack(Material.STONE, 32));
			}
			Game game = new Game(_main);
			game.runTaskTimer(_main, 300, 2);
			Bukkit.broadcastMessage(ChatColor.RED+"La partie va commencer bonne chance");
			cancel();
		}
	}
	
}
