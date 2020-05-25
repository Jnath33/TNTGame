package fr.jnathEtMiaouCJ.TNTMode.MyClass;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.task.gainClai;

public class TNTDist {
	private Player _player;
	private Location _tntLoc;
	private static Main _main;
	private static Map<Player, Location>s_all_tnt = new HashMap<Player, Location>();
	private static Map<Location, Player>s_all_player = new HashMap<Location, Player>();
	public TNTDist(Player player, Location tntLoc) {
		_player=player;
		_tntLoc=tntLoc;
		s_all_tnt.put(_player, _tntLoc);
		s_all_player.put(_tntLoc, _player);
	}
	public static void explode(Player player) {
		if(s_all_tnt.containsKey(player)) {
			s_all_tnt.get(player).getWorld().createExplosion(s_all_tnt.get(player), 5);
			s_all_tnt.remove(player);
			gainClai regainClai = new gainClai(player);
			regainClai.runTaskLater(_main, 300);
		}else {
			player.sendMessage("Posser dabord l'explosif");
		}
	}
	public static void rm(Location loc) {
		if(s_all_player.containsKey(loc)) {
			s_all_tnt.remove(s_all_player.get(loc));
			gainClai regainClai = new gainClai(s_all_player.get(loc));
			regainClai.runTaskLater(_main, 300);
			s_all_player.remove(loc);
		}
	}
	
	public static void setMain(Main main) {
		_main = main;
	}
}
