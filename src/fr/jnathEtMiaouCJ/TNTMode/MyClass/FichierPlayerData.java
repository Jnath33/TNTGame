package fr.jnathEtMiaouCJ.TNTMode.MyClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class FichierPlayerData{
	private ObjectInputStream ofR;
	private ObjectOutputStream ofW;
	private char mode;
	private static String nomDuFichier = "tntMode.dat";
	private String uid;
	private static String repertory;
	public FichierPlayerData(String playerUid) {
		uid=playerUid;
		if(!new File(repertory+uid).exists()) {
			new File(repertory+uid).mkdir();
		}if(!new File(repertory+uid+"/"+nomDuFichier).exists()) {
			try {
				new File(repertory+uid+"/"+nomDuFichier).createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void ouvrir(String str) throws IOException{
		mode = (str.toUpperCase()).charAt(0);
		if (mode== 'R' | mode == 'L') {
			ofR = new ObjectInputStream(new FileInputStream(repertory+uid+"/"+nomDuFichier));
		}else if (mode== 'W' | mode == 'E') {
			ofW  = new ObjectOutputStream(new FileOutputStream(repertory+uid+"/"+nomDuFichier));
		}
	}
	public void ecrire(PlayerData tmp) throws IOException{
		if(tmp!=null) ofW.writeObject(tmp);
	}
	public PlayerData lire() throws IOException, ClassNotFoundException{
		PlayerData tmp = (PlayerData) ofR.readObject();
		return tmp;
	}
	public void fermer() throws IOException{
		if (mode== 'R' | mode == 'L') {
			ofR.close();
		}else if (mode== 'W' | mode == 'E') {
			ofW.close();
		}
	}
	public static void setDefauldRepertory(String saveRepertory) {
		repertory=saveRepertory;
	}
	public static String getDefauldRepertory() {
		return repertory;
	}
	public static void init(String uid, Player player) {
		if(!new File(repertory+uid).exists()) {
			new File(repertory+uid);
		}
		if(!new File(repertory+uid+"/"+nomDuFichier).exists()) {
			new File(repertory+uid+"/"+nomDuFichier);
		}else {
			FichierPlayerData file = new FichierPlayerData(uid);
			try {
				file.ouvrir("L");
				new PlayerData(file.lire());
				file.fermer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		FichierPlayerData file = new FichierPlayerData(uid);
		try {
			file.ouvrir("E");
			List<String> kit = new ArrayList<String>();
			kit.add("Basic");
			file.ecrire(new PlayerData(kit , 0, 0, player));
			file.fermer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
