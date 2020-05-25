package fr.jnathEtMiaouCJ.TNTMode.MyClass;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemPlace {
	ItemStack _item;
	int _place;
	public ItemPlace(ItemStack item, int place) {
		_item=item;
		_place=place;
	}
	public void setItem(Player player) {
		player.getInventory().setItem(_place, _item);
	}
}
