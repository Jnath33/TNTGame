package fr.jnathEtMiaouCJ.TNTMode.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jnathEtMiaouCJ.TNTMode.Main;

public class TNTWaitBreak extends BukkitRunnable{
	Main _main;
	Player _player;
	public TNTWaitBreak(Main main, Player player) {
		_main=main;
		_player=player;
		_main.canBreakTNT.put(_player, false);
	}
	@Override
	public void run() {
		_main.canBreakTNT.put(_player, true);
	}

}
