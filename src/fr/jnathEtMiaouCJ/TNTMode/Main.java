package fr.jnathEtMiaouCJ.TNTMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.jnath.TNTMode.Kit;
import fr.jnath.Utils.Utils;
import fr.jnathEtMiaouCJ.TNTMode.Enum.State;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.TNTDist;
import fr.jnathEtMiaouCJ.TNTMode.commande.Start;
import fr.jnathEtMiaouCJ.TNTMode.listeners.GplayerListener;
import fr.jnathEtMiaouCJ.TNTMode.listeners.KitListeners;


public class Main extends JavaPlugin{
	Comparator<Player> comparePlayerbyName = new Comparator<Player>() {
	@Override
	public int compare(Player o1, Player o2) {
		return o1.getName().compareTo(o2.getName());
	}
};
	public List<Player> players = new ArrayList<Player>();
	public List<Player> playerOnGame = new ArrayList<Player>();
	public TreeMap<Player, Integer> Life= new TreeMap<Player, Integer>(comparePlayerbyName);
	public Map<Player, Boolean> canBreakTNT = new HashMap<Player, Boolean>();
	String worldName;
	public World world;
	public State state;
	public Map<Player, Kit> playerKit = new HashMap<Player, Kit>();
	@Override
	public void onEnable() {
		saveDefaultConfig();
		worldName = getConfig().getString("TNTMode.world");
		world = Bukkit.getWorld(worldName);
		state=State.AttenteDeJoueur;
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new GplayerListener(this), this);
		pm.registerEvents(new KitListeners(this), this);
		this.getCommand("start").setExecutor(new Start(this));
		
		TNTDist.setMain(this);
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	public void teleportServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        
      //Envoyer un message au joueur pour le prévenir (FACULTATIF)
        player.sendMessage(ChatColor.GREEN+"Vous etes envoye sur "+ChatColor.GOLD+server);

        player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}
	
	@Override
	public void onDisable() {
		rejen();
	}
	
	public Location stringToLoc(String locStr) {
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
	public void rejen() {
		Bukkit.unloadWorld(world, false);
		File worldFile = new File(world.getName());
		File worldCopyFile = new File(worldName+"-copy");
		Utils.deleateWorld(worldFile);
		try {
			Utils.copyWorld(worldCopyFile, worldFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
