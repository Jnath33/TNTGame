package fr.jnathEtMiaouCJ.TNTMode.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jnath.TNTMode.PlayerData;
import fr.jnathEtMiaouCJ.TNTMode.Main;

public class GainCoin extends BukkitRunnable{
	Main _main;
	public GainCoin(Main main) {
		_main=main;
	}
	@Override
	public void run() {
		for(Player pls : _main.playerOnGame) {
			PlayerData.getPlayerData(pls).addCoins(1);			
		}
				
	}
	
}
