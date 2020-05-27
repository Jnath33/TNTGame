package fr.jnathEtMiaouCJ.TNTMode.MyClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Kit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2233441325565162827L;
	private static Map<Kit, Integer> s_all_tnt_per_kit = new HashMap<Kit, Integer>();
	private static Map<String, Kit> s_all_kit = new HashMap<String, Kit>();
	private List<ItemPlace> itemList = new ArrayList<ItemPlace>();
	private String _name;
	private String _itemName;
	private Material _mat;
	public Kit(String name, Integer TNT, List<ItemPlace> items, String itemName, Material mat) {
		_name=name;
		s_all_tnt_per_kit.put(this, TNT);
		s_all_kit.put(_name, this);
		itemList=items;
		_mat=mat;
		_itemName=itemName;
	}
	public static int getTNT(Kit kit) {
		return s_all_tnt_per_kit.get(kit);
	}
	public static Kit getKit(String name) {
		return s_all_kit.get(name);
	}
	public List<ItemPlace> getItems(){
		return itemList;
	}
	public Material getMaterial() {
		return _mat;
	}
	public static Collection<Kit> getAllKit(){
		return s_all_kit.values();
	}
	public static void setItems(Player player, Kit kit) {
		for(ItemPlace itemP : kit.getItems()) {
			itemP.setItem(player);
		}
		player.updateInventory();
	}
	public String getItemName() {
		return _itemName;
	}
	public String getName() {
		return _name;
	}
}
