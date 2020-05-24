package fr.jnathEtMiaouCJ.TNTMode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
			}
		} else {
			String serv = _main.getConfig().getString("TNTMode.bungeeHubName");
			for(Player pls : Bukkit.getOnlinePlayers()) {
				_main.teleportServer(pls, serv);
			}
		}
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
	}
}
