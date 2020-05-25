package fr.jnathEtMiaouCJ.TNTMode.MyClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

public class Kit {
	private static Map<Kit, Integer> s_all_tnt_per_kit = new HashMap<Kit, Integer>();
	private static Map<String, Kit> s_all_kit = new HashMap<String, Kit>();
	private List<ItemPlace> itemList = new ArrayList<ItemPlace>();
	private String _name;
	public Kit(String name, Integer TNT, List<ItemPlace> items) {
		_name=name;
		s_all_tnt_per_kit.put(this, TNT);
		s_all_kit.put(_name, this);
		itemList=items;
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
	
	public static void setItems(Player player, Kit kit) {
		for(ItemPlace itemP : kit.getItems()) {
			itemP.setItem(player);
		}
		player.updateInventory();
	}
}
