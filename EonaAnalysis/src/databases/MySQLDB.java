package databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.mysql.jdbc.util.ResultSetUtil;

public class MySQLDB {

	
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private ArrayList<ConnectionData> zugaenge = new ArrayList<ConnectionData>();
	private int opened_Connections = 0;
	public Logger l = null;
	
	
	public MySQLDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addConnectionData(ConnectionData x){
		zugaenge.add(x);
	}
	
	public void addConnectionData(String h, String u, String p, String n){
		ConnectionData x = new ConnectionData();
		x.setDbHost(h);
		x.setDbName(n);
		x.setDbPass(p);
		x.setDbUser(u);
		addConnectionData(x);
	}
	
	public void openConnections(){
		for(ConnectionData a:zugaenge){
			String connString = "jdbc:mysql://" + a.getDbHost() + "/" + a.getDbName() + "?user=" + a.getDbUser() + "&password=" + a.getDbPass();
			try{
				Connection x = DriverManager.getConnection(connString);
				connections.add(x);
				opened_Connections++;
			}catch(Exception e){
				if(l != null){
					l.info("Konnte keine Verbindung zur DB herstellen: " + a.toString());
					l.info(e.getMessage());
				}
			}
		}
	}
	
	
	public ResultSet query(String sql){
		boolean gefunden = false;
		Connection conn = null;
		for(Connection c:connections){
			try {
				if(!c.isClosed()){
					conn = c;
					gefunden = true;
				}
			} catch (SQLException e) {
				return null;
			}
		}
		
		if(gefunden){
			try{
				return conn.createStatement().executeQuery(sql);
			}catch(Exception e){
				l.info("Fehler beim Abrufen der Daten:" );
				return null;
			}
		}else{
			return null;
		}
	}
	
	
	
	
	
}
