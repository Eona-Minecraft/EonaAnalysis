package analysis;

import java.util.ArrayList;

public class AnalysisPacket {

	private String event = "";
	private ArrayList<Object> data = new ArrayList<Object>();
	
	public void setEvent(String x){
		event = x;
	}
	
	public void addData(Object x){
		data.add(x);
	}
	
	
}
