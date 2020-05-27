package fr.jnathEtMiaouCJ.TNTMode.MyClass;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

public class PlayerData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7098619538188328744L;
	private static Map<String, PlayerData> s_all_player_data = new HashMap<String, PlayerData>();
	private List<Kit> _myKit;
	private int _coins;
	private int _xpTotal;
	private String _playerName;
	private String _playerUID;
	
	public PlayerData(List<Kit> kits, int coin, int xpTotal, Player player) {
		_myKit=kits;
		_coins=coin;
		_xpTotal=xpTotal;
		_playerName=player.getName();
		_playerUID = player.getUniqueId().toString();
		s_all_player_data.put(_playerName, this);
	}
	public PlayerData(PlayerData pData) {
		_myKit=pData._myKit;
		_coins=pData._coins;
		_xpTotal=pData._xpTotal;
		_playerName=pData._playerName;
		_playerUID=pData._playerUID;
		s_all_player_data.put(_playerName, this);
		
	}
	public static boolean haveKit(Player player, Kit kit) {
		if(s_all_player_data.containsKey(player.getName())) {
			return s_all_player_data.get(player.getName())._myKit.contains(kit);
		}
		return false; 
	}
	public int getCoins() {
		return _coins;
	}
	public void addCoins(int coins) {
		_coins+=coins;
	}
	public int getTotalXP() {
		return _xpTotal;
	}
	public int getLevel() {
		boolean end = false;
		int lvl=0;
		int totalXpRenward = _xpTotal;
		while(end){
			if(totalXpRenward-(lvl*50+500)<=0) {
				lvl+=1;
			}else end = true;
		}
		return lvl;
	}
	public void save() throws IOException {
		FichierPlayerData file = new FichierPlayerData(_playerUID);
		file.ouvrir("E");
		file.ecrire(this);
		file.fermer();
	}
	public static PlayerData getPlayerData(Player player) {
		return s_all_player_data.get(player.getName());
	}
}
