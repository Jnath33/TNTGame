package fr.jnathEtMiaouCJ.TNTMode.task;

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

public class Starting extends BukkitRunnable{
	Main _main;
	Inventory inv = Bukkit.createInventory(null, 54,"§cKit selector");
	int time=20;
	public Starting(Main main) {
		_main =main;
		
		ItemStack wall1=new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemStack wall2=new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		inv.setItem(0, wall1);
		inv.setItem(1, wall1);
		inv.setItem(9, wall1);
		
		inv.setItem(2, wall2);
		inv.setItem(3, wall2);
		inv.setItem(4, wall2);
		inv.setItem(5, wall2);
		inv.setItem(6, wall2);

		inv.setItem(7, wall1);
		inv.setItem(8, wall1);
		inv.setItem(17, wall1);

		inv.setItem(26, wall2);
		inv.setItem(35, wall2);
		
		inv.setItem(36, wall1);
		inv.setItem(45, wall1);
		inv.setItem(46, wall1);
		
		inv.setItem(47, wall2);
		inv.setItem(48, wall2);
		inv.setItem(49, wall2);
		inv.setItem(50, wall2);
		inv.setItem(51, wall2);

		inv.setItem(44, wall1);
		inv.setItem(53, wall1);
		inv.setItem(52, wall1);
		
		inv.setItem(18, wall2);
		inv.setItem(27, wall2);
		
		inv.setItem(10, wall2);
		inv.setItem(37, wall2);
		inv.setItem(43, wall2);
		inv.setItem(16, wall2);
		
		inv.setItem(11, Utils.createItem("§cBasic", Material.IRON_PICKAXE, 1));
		inv.setItem(15, Utils.createItem("§cTelecom", Material.NETHER_STAR, 1));
		inv.setItem(38, Utils.createItem("§cBuilder", Material.BRICK, 1));
		inv.setItem(42, Utils.createItem("§cMax TNT", Material.TNT, 4));
		for(Player player : _main.players) {
			player.openInventory(inv);
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
			}
			Game game = new Game(_main);
			game.runTaskTimer(_main, 300, 2);
			Bukkit.broadcastMessage(ChatColor.RED+"La partie va commencer bonne chance");
			cancel();
		}
	}
	
}
