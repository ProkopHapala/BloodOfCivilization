
package BoC.Engine.Economy;

import BoC.Engine.Site;
import BoC.utils.StringIO;
import BoC.Engine.Globals;

public class NaturalResource implements Resource, StringIO {
	
	Site site;
	public ComodityType type;
	/*
	public boolean depleating;
	public double  stored;
	public double  drain_rate;
	*/
	
	public double maxYield;
	public double capacity;
	public double workforce;
	
	/*
	double drain( double dt, double max_d ){
		double d = drain_rate * dt;
		d = ( d < max_d ) ? d : max_d;
		if( depleating ) stored -= d;
		return d;
	}
	*/
	
	double development_degree;
	double difficulty;
	

	@Override
	public double getYield( ){
		return Globals.explotationFunction( workforce, maxYield, capacity  );
	}
	
	// ========== IO
	
	//@Override
	public String getName(){
		return type.name+"_"+site.ix+"_"+site.iy;
	}
	
	@Override
	public String toString(){
		return site.ix+" "+site.iy+" "+type.name+" "+maxYield+" "+capacity+" "+workforce;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		site.ix     = Integer.parseInt( words[0] );
		site.iy     = Integer.parseInt( words[1] );         
		type        = Globals.comodityTypes.get( words[2]  ); 
		maxYield    = Double.parseDouble( words[3] );   
		capacity    = Double.parseDouble( words[4] );   
		workforce   = Double.parseDouble( words[5] );   
	}
	
}
