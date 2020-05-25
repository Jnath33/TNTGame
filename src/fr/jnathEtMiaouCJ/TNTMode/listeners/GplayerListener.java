package fr.jnathEtMiaouCJ.TNTMode.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import fr.jnathEtMiaouCJ.TNTMode.Main;
import fr.jnathEtMiaouCJ.TNTMode.Enum.State;
import fr.jnathEtMiaouCJ.TNTMode.MyClass.TNTDist;

public class GplayerListener implements Listener {
	Main _main;
	public GplayerListener(Main main) {
		_main=main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!(_main.state==State.AttenteDeJoueur)) {
			if(!_main.getConfig().getBoolean("TNTMode.bungee")) {
				player.kickPlayer("Une partie est déja en cours");
			} else {
				String serv = _main.getConfig().getString("TNTMode.bungeeHubName");
				_main.teleportServer(player, serv);
			}
			return;
		}
		player.getInventory().clear();
		player.updateInventory();
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20);
		player.setFoodLevel(20);
		player.teleport(_main.stringToLoc(_main.getConfig().getString("TNTMode.location")));
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		for (Block b : event.blockList()) {
			if(b.getType()==Material.HARD_CLAY) {
				TNTDist.rm(b.getLocation());
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if(!(_main.state==State.Partie)) {
			event.setCancelled(true);
			return;
		}
		if(event.getBlock()==null) {
			event.setCancelled(true);
			return;
		}
		if(event.getBlock().getType()==Material.HARD_CLAY) {
			TNTDist.rm(event.getBlock().getLocation());
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		if(event.getDamage()>=player.getHealth()) {
			if(event.getCause()==DamageCause.ENTITY_EXPLOSION||event.getCause()==DamageCause.BLOCK_EXPLOSION) {
				player.setHealth(20);
				if(_main.Life.get(player)-1==0) {
					player.setGameMode(GameMode.SPECTATOR);
					_main.playerOnGame.remove(player);
				}else {
					_main.Life.put(player, _main.Life.get(player)-1);
				}
				player.teleport(_main.stringToLoc(_main.getConfig().getString("TNTMode.spawn")));
			}
			event.setCancelled(true);
		}
		if(_main.state==State.Partie) {
			if(event.getCause()==DamageCause.ENTITY_EXPLOSION||event.getCause()==DamageCause.BLOCK_EXPLOSION) {
				event.setDamage(event.getDamage()/1.5);
				return;
				}
		}
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(_main.state!=State.AttenteDeJoueur) {
			if(_main.players.contains(player)) {
				_main.players.remove(player);
			}
			if(_main.playerOnGame.contains(player)) {
				_main.playerOnGame.remove(player);
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if(!(_main.state==State.Partie)) {
			event.setCancelled(true);
			return;
		}
		if(event.getBlock()!=null) {
			if(event.getBlock().getType()==Material.STONE){
				event.getPlayer().getInventory().setItem(3, new ItemStack(Material.STONE, 32));
			} else if(event.getBlock().getType()==Material.HARD_CLAY) {
				new TNTDist(event.getPlayer(), event.getBlock().getLocation());
			}
		}
		
	}
	
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem()!=null&&event.getItem().getType()==Material.NETHER_STAR) {
			TNTDist.explode(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		event.setCancelled(true);
	}
}
