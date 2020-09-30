package fr.jnathEtMiaouCJ.TNTMode.task;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jnath.TNTMode.PlayerData;
import fr.jnathEtMiaouCJ.TNTMode.Main;


public class Redémarage extends BukkitRunnable{
	Player _player;
	Main _main;
	public Redémarage (Main main) {
		_main=main;
		Bukkit.broadcastMessage("Le serveur redémarera dans 30s");
		for(Player pls : Bukkit.getOnlinePlayers())
			pls.setGameMode(GameMode.SPECTATOR);
	}

	@Override
	public void run() {
		if(!_main.getConfig().getBoolean("TNTMode.bungee")) {
			for(Player pls : Bukkit.getOnlinePlayers()) {
				pls.kickPlayer("Le serveur redémare");
				try {
					PlayerData.getPlayerData(pls).save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			String serv = _main.getConfig().getString("TNTMode.bungeeHubName");
			for(Player pls : Bukkit.getOnlinePlayers()) {
				_main.teleportServer(pls, serv);
				try {
					PlayerData.getPlayerData(pls).save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Stop stop = new Stop();
		stop.runTaskLater(_main, 10);
	}
}
