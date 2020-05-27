package fr.jnathEtMiaouCJ.TNTMode.commande;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.Enum.State;
import fr.jnathEtMiaouCJ.TNTMode.task.Starting;

public class Start implements CommandExecutor {
	Main _main;
	public Start(Main main) {
		_main=main;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			for(Player pls : Bukkit.getOnlinePlayers()) {
				pls.updateInventory();
				_main.Life.put(pls, 4);
				_main.players.add(pls);
				_main.playerOnGame.add(pls);
			}
			_main.state=State.Lancement;
			Starting game = new Starting(_main);
			game.runTaskTimer(_main, 0, 20);
			return true;
		}
		
		return false;
	}

}
