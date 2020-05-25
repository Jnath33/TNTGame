package fr.jnathEtMiaouCJ.TNTMode.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jnath.Utils.Utils;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.ItemPlace;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.Kit;

public class SetKit {
	public static void set() {
		List<ItemPlace> itemPlace = new ArrayList<ItemPlace>();
		itemPlace.add(new ItemPlace(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1), 102));
		new Kit("Basic", 4, itemPlace);
		

		List<ItemPlace> itemPlace2 = new ArrayList<ItemPlace>();
		itemPlace2.add(new ItemPlace(new ItemStack(Material.NETHER_STAR, 1), 7));
		itemPlace2.add(new ItemPlace(new ItemStack(Material.HARD_CLAY, 1, (short) 5),8));
		itemPlace.add(new ItemPlace(new ItemStack(Material.LEATHER_HELMET, 1), 103));
		new Kit("Distance", 3, itemPlace2);
		
		List<ItemPlace> itemPlace3 = new ArrayList<ItemPlace>();
		itemPlace3.add(new ItemPlace(Utils.createItem("§cCanon build", Material.STICK, 2), 5));
		itemPlace3.add(new ItemPlace(Utils.createItem("§cTower", Material.ANVIL, 2), 6));
		itemPlace3.add(new ItemPlace(Utils.createItem("§cTNT cube(3×3)", Material.STONE, 1), 7));
		itemPlace3.add(new ItemPlace(Utils.createItem("§cTNT cube(7×7)", Material.COBBLESTONE, 1), 8));
		new Kit("builder", 2, itemPlace3);

		new Kit("More TNT", 8, new ArrayList<ItemPlace>());
		
	}
}
