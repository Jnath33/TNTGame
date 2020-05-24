package fr.jnathEtMiaouCJ.TNTMode;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class gainClai extends BukkitRunnable{
	Player _player;
	public gainClai(Player player) {
		_player=player;
	}

	@Override
	public void run() {
		_player.getInventory().setItem(8, new ItemStack(Material.HARD_CLAY,1,(short) 5));
		_player.updateInventory();
	}
}
