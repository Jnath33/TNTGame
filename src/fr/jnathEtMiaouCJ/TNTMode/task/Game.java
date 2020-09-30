package fr.jnathEtMiaouCJ.TNTMode.task;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jnath.TNTMode.Kit;
import fr.jnath.TNTMode.PlayerData;
import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.Enum.State;


public class Game extends BukkitRunnable{
	int time = 0;
	int totalTime = 0;
	List<Location> sousSpawn = new ArrayList<Location>();
	List<Location> airSpawn = new ArrayList<Location>();
	Main _main;
	public Game(Main main) {
		_main=main;
		for(String locStr : _main.getConfig().getStringList("TNTMode.locationInfiniteBlock")) {
			sousSpawn.add(_main.stringToLoc(locStr));
		}
		for(String locStr : _main.getConfig().getStringList("TNTMode.locationInfiniteAir")) {
			airSpawn.add(_main.stringToLoc(locStr));
		}
	}
	
	@Override
	public void run() {
		time++;
		List<Player> rmPlayer = new ArrayList<Player>();
		if(!(_main.state==State.Partie)) {
			_main.state=State.Partie;
			Bukkit.broadcastMessage("Vous êtes vulnérable");
		}
		for(Player pls : _main.playerOnGame) {
			pls.setFoodLevel(20);
			if(pls.getLocation().getY()<-2) {
				pls.setHealth(20);
				if(_main.Life.get(pls)-1==0) {
					pls.setGameMode(GameMode.SPECTATOR);
					rmPlayer.add(pls);
				}else {
					_main.Life.put(pls, _main.Life.get(pls)-1);
				}
				pls.teleport(_main.stringToLoc(_main.getConfig().getString("TNTMode.spawn")));
			}
		}
		for(Player pls : rmPlayer) {
			_main.playerOnGame.remove(pls);
		}
		if(time==30) {
			for(Player pls : _main.playerOnGame) {
				if(!(pls.getInventory().getItem(0)!=null&&pls.getInventory().getItem(0).getAmount()+Kit.getTNT(_main.playerKit.get(pls))>64)) {
					pls.getInventory().addItem(new ItemStack(Material.TNT, Kit.getTNT(_main.playerKit.get(pls))));
				}
			}
			time=0;
		}
		for(Location sousSpawnBlock : sousSpawn) {
			sousSpawnBlock.getBlock().setType(Material.COBBLESTONE);			
		}
		for(Location airSpawnBlock : airSpawn) {
			airSpawnBlock.getBlock().setType(Material.AIR);			
		}
		if(_main.playerOnGame.size()==1) {
			Bukkit.broadcastMessage(_main.playerOnGame.get(0).getDisplayName()+" à gagner");
			PlayerData.getPlayerData(_main.playerOnGame.get(0)).addCoins(4000);
			Redémarage redémare = new Redémarage(_main);
			redémare.runTaskLater(_main, 600);
			cancel();
		} else if(_main.playerOnGame.size()==0) {
			Bukkit.broadcastMessage("égaliter");
			Redémarage redémare = new Redémarage(_main);
			redémare.runTaskLater(_main, 600);
			cancel();
		}
		
	}

}
