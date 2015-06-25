package analysis;

public abstract class Analysis {

	public abstract String getName();
	
	public abstract AnalysisStatistic  getStatistik();
	
	public abstract void analyse(AnalysisPacket x);
		
}
