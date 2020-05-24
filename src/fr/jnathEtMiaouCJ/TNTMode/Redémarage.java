package fr.jnathEtMiaouCJ.TNTMode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Redémarage extends BukkitRunnable{
	Player _player;
	public Redémarage () {
		Bukkit.broadcastMessage("Le serveur redémarera dans 15s");
		for(Player pls : Bukkit.getOnlinePlayers())
			pls.setGameMode(GameMode.SPECTATOR);
	}

	@Override
	public void run() {
		for(Player pls : Bukkit.getOnlinePlayers()) {
			pls.kickPlayer("Le serveur redémare");
		}
		Main.rejen();
		
	}
}
