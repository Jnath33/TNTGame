package fr.jnathEtMiaouCJ.TNTMode;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class Game extends BukkitRunnable{
	int time = 0;
	Location sousSpawn;
	Main _main;
	public Game(Main main) {
		_main=main;
		sousSpawn= new Location(_main.world, 785.5, 13.0, -340.5);
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
				pls.getInventory().addItem(new ItemStack(Material.TNT,2 ));
			}
			time=0;
		}
		sousSpawn.getBlock().setType(Material.COBBLESTONE);
		if(_main.playerOnGame.size()==1) {
			Bukkit.broadcastMessage(_main.playerOnGame.get(0).getDisplayName()+" à gagner");
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
