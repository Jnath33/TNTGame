package fr.jnathEtMiaouCJ.TNTMode.MyClass;

import java.io.Serializable;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemPlace implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4081364741223597118L;
	private String _itemName;
	private short _data;
	private int _nomber;
	private int _id;
	private int _place;
	@SuppressWarnings("deprecation")
	public ItemPlace(ItemStack item, int place) {
		_itemName=item.getItemMeta().getDisplayName();
		_data=item.getData().getData();
		_nomber=item.getAmount();
		_id=item.getType().getId();
		_place=place;
	}
	@SuppressWarnings("deprecation")
	public void setItem(Player player) {
		ItemStack item = new ItemStack(_id, _nomber, _data);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(_itemName);
		item.setItemMeta(itemM);
		player.getInventory().setItem(_place, item);
	}
}
