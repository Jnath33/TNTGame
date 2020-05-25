package fr.jnathEtMiaouCJ.TNTMode.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.jnath.Utils.Utils;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.ItemPlace;

public class KitListeners implements Listener{
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getItem()==null)return;
		ItemStack it = event.getItem();
		if(it.getItemMeta().getDisplayName().contains("§cCanon build")) {
			if(it.getAmount()==2) {
				new ItemPlace(Utils.createItem("§cCanon build", Material.STICK, 1), 5).setItem(player);
			}
		}
	}
}
