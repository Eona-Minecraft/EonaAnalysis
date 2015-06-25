package de.eonaminecraft;


import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import analysis.Analysis;
import analysis.AnalysisPacket;

public class EonaAnalysis extends JavaPlugin{

	
	private Logger meinLogger = null;
	private ArrayList<Analysis> schnittstellen = new ArrayList<Analysis>();
	
	
	
	public void onEnable(){
		meinLogger = this.getLogger();
		meinLogger.info("Log-System eingepflanzt");
		meinLogger.info("Lade Analysenschnittstellen");
		
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){
		return true;
	}
	
	
	
	private void showStatistic(CommandSender sender){
		sender.sendMessage("EONA-Analysis: Statistiken");
	}
	
	
}
