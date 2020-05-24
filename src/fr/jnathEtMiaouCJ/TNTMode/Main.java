package fr.jnathEtMiaouCJ.TNTMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	Comparator<Player> comparePlayerbyName = new Comparator<Player>() {
	@Override
	public int compare(Player o1, Player o2) {
		return o1.getName().compareTo(o2.getName());
	}
};
	List<Player> players = new ArrayList<Player>();
	List<Player> playerOnGame = new ArrayList<Player>();
	TreeMap<Player, Integer> Life= new TreeMap<Player, Integer>(comparePlayerbyName);
	String worldName;
	public World world;
	String worldGameName;
	public World worldGame;
	static Main _main;
	State state;
	@Override
	public void onEnable() {
		worldGameName = "MapTNT";
		saveDefaultConfig();
		worldName = getConfig().getString("TNTMode.world");
		world = Bukkit.getWorld(worldName);
		getServer().createWorld(new WorldCreator(worldGameName));
		
		state=State.AttenteDeJoueur;
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new GplayerListener(this), this);
		this.getCommand("start").setExecutor(new Start(this));
		worldGame = getServer().getWorld(worldGameName);
		
		System.out.println(worldGameName);
		System.err.println(worldGame.getName());
		
		TNTDist.setMain(this);
	}
	
	public Location stringToLoc(String locStr, World world) {
		String[] locSeparate = locStr.split(",");
		double x = Double.valueOf(locSeparate[0]);
		double y = Double.valueOf(locSeparate[1]);
		double z = Double.valueOf(locSeparate[2]);
		if(locSeparate.length==5) {
			float yaw = Float.valueOf(locSeparate[3]);
			float pitch = Float.valueOf(locSeparate[4]);
			return new Location(world, x, y, z, yaw, pitch);		
		} else {
			return new Location(world, x, y, z);			
		}
	}
	public static void rejen() {
		Bukkit.unloadWorld(Bukkit.getWorld("MapTNT"), false);
		File worldFile = new File("MapTNT");
		File worldCopyFile = new File("MapTNT"+"-copy");
		rejen.deleateWorld(worldFile);
		try {
			rejen.copyWorld(worldCopyFile, worldFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bukkit.createWorld(new WorldCreator("MapTNT"));		
	}
}
