
package BoC.Engine.Economy;

import BoC.Engine.Site;
import BoC.utils.StringIO;
import BoC.Engine.Globals;

public class ResourceDeposit extends NaturalResource {
	
	public Site    site;
	public boolean depleating;
	public double  stored;
	public double  workforce;
	public double  development_degree;
	public double  drain_rate;
	
	
	public double getYield( ){ return super.getYield(workforce); }
	
	/*
	double drain( double dt, double max_d ){
		double d = drain_rate * dt;
		d = ( d < max_d ) ? d : max_d;
		if( depleating ) stored -= d;
		return d;
	}
	*/
	
	// ========== IO
	
	//@Override
	public String getName(){
		return type.name+"_"+site.ix+"_"+site.iy;
	}
	
	@Override
	public String toString(){
		return site.ix+" "+site.iy+" "+type.name+" "+maxYield+" "+capacity+" "+String.valueOf(depleating)+" "+stored+" "+workforce;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		site.ix     = Integer.parseInt    ( words[0] );
		site.iy     = Integer.parseInt    ( words[1] );         
		type        = Globals.comodityTypes.get( words[2]  ); 
		maxYield    = Double.parseDouble  ( words[3] );   
		capacity    = Double.parseDouble  ( words[4] );  
		depleating  = Boolean.parseBoolean( words[5] );
		stored      = Double.parseDouble  ( words[6] );  
		workforce   = Double.parseDouble  ( words[7] );  
	}
	
}
