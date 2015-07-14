package de.eonaminecraft;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class Hauptklasse extends JavaPlugin{
	
	private ArrayList<String> zeilen = new ArrayList<String>();
	private File config = null;
	private Logger l = null;
	
	
	public void onEnable(){
		l = this.getLogger();
		l.info("Erstelle Plugin-Ordner, falls es nicht existiert");
		makePluginFolder();
		l.info("Erstelle Plugin-Config, falls diese nicht existiert");
		if(!configExits()){
			createDefaultConfig();
		}
		
		l.info("Lese die Zeilen ein");
		readFile();
	}
	
	
	private void makePluginFolder(){
		if(!this.getDataFolder().exists()){
			try {
				this.getDataFolder().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = new File(this.getDataFolder().getAbsolutePath() + "/config.yml");
	}
	
	private void readFile(){
		try(FileInputStream fs = new FileInputStream(config); InputStreamReader isr = new InputStreamReader(fs); BufferedReader br = new BufferedReader(isr)){
			String zeile = br.readLine();
			while(zeile != "" && zeile != null){
				zeilen.add(zeile);
				zeile = br.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private boolean configExits(){
		return config.exists();
	}
	
	private void createDefaultConfig(){
		try{
			if(!config.exists()){
				config.createNewFile();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try(FileOutputStream fs = new FileOutputStream(config); OutputStreamWriter isr = new OutputStreamWriter(fs); BufferedWriter bw = new BufferedWriter(isr)){
			bw.write("Vote-Befehl: Default-Text");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void onCommand(CommandSender sender, Command command, String label, String[] args){
		if(label.startsWith("ev")){
			if(args.length > 0){
				switch(args[0]) {
				case "reload":
					zeilen.clear();
				case "":
				}
			}else{
				sender.sendMessage("Keine Argumente übergeben");
			}
		}else if (label.startsWith("vote")){
			for(String s:zeilen){
				sender.sendMessage(s);
			}
		}
	}
	
	
	
	

}
