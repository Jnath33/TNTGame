package fr.jnathEtMiaouCJ.TNTMode.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Stop extends BukkitRunnable{

	@Override
	public void run() {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
		
	}

}
