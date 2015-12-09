
package BoC.Engine.Economy;

public class NaturalResource {
	public ComodityType type;
	public boolean depleating;
	public double  stored;
	public double  drain_rate;
	
	double drain( double dt, double max_d ){
		double d = drain_rate * dt;
		d = ( d < max_d ) ? d : max_d;
		if( depleating ) stored -= d;
		return d;
	}
	
	double development_degree;
	double difficulty;
	
}
