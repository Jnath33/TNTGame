package fr.jnathEtMiaouCJ.TNTMode.commande;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.Enum.State;
import fr.jnathEtMiaouCJ.TNTMode.task.Game;

public class Start implements CommandExecutor {
	Main _main;
	public Start(Main main) {
		_main=main;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			for(Player pls : _main.players) {
				pls.teleport(_main.stringToLoc(_main.getConfig().getString("TNTMode.spawn")));
				ItemStack briquet = new ItemStack(Material.FLINT_AND_STEEL);
				ItemMeta briquetM = briquet.getItemMeta();
				briquetM.addEnchant(Enchantment.DURABILITY, 10, true);
				pls.getInventory().clear();
				pls.getInventory().setItem(2, briquet);
				pls.getInventory().setItem(3, new ItemStack(Material.STONE, 32));
				pls.getInventory().setItem(7, new ItemStack(Material.NETHER_STAR, 1));
				pls.getInventory().setItem(8, new ItemStack(Material.HARD_CLAY, 1, (short) 5));
				pls.updateInventory();
				_main.Life.put(pls, 4);
			}
			_main.state=State.Lancement;
			Game game = new Game(_main);
			game.runTaskTimer(_main, 300, 2);
		}
		
		return false;
	}

}
