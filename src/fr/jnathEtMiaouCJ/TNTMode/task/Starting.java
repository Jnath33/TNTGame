package fr.jnathEtMiaouCJ.TNTMode.task;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jnath.Utils.Utils;
import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.Kit;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.PlayerData;

public class Starting extends BukkitRunnable{
	Main _main;
	int time=25;
	public Starting(Main main) {
		_main=main;
		Collection<Kit> allKit = Kit.getAllKit();
		/*
		 * Bug a coriger
		Kit bKit =Kit.getKit("Basic");
		allKit.remove(bKit);
		inv.setItem(curentSet, Utils.createItem("test", Kit.getKit("Basic").getMaterial(), 1));
		*/
		for(Player player : _main.players) {
			int curentSet=-1;
			Inventory inv = Bukkit.createInventory(null, 54,"§cKit selector");
			for(Kit kit : allKit) {
				curentSet++;
				if(PlayerData.getPlayerData(player).haveKit(kit)) {
					inv.setItem(curentSet, Utils.createItem(kit.getItemName(), kit.getMaterial(), 1));
				}else {
					inv.setItem(curentSet, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5));
				}
				if(!(curentSet<44)) {
					break;
				}
			}
			player.openInventory(inv);
			System.err.println(PlayerData.getPlayerData(player).getKit().size());
		}
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
				_main.canBreakTNT.put(pls, true);
				Kit.setItems(pls, _main.playerKit.get(pls));
			}
			Game game = new Game(_main);
			game.runTaskTimer(_main, 300, 2);
			Bukkit.broadcastMessage(ChatColor.RED+"La partie va commencer bonne chance");
			cancel();
		}
	}
	
}
