package BoC.Engine.Economy;

import BoC.Engine.City;
import BoC.Engine.GameObject;
import java.util.*;

/*
Technology is ( or FactoryType ? ) is a production process which converts some comodities into some other comodities and machines, with asistence of other machines
*/
public class Technology extends GameObject {
	public double cycle_time;  // time to produce unit
	public double unit_space;  // unit space taken in factory
	
	public HashMap<ComodityType,Double> consumes;
	public HashMap<ComodityType,Double> produces;
	public HashMap<MachineType,Integer> machines;
	// HashMap<MachineType,Integer> machines_produced;  // maybe later ?
	
	public void consume_units( double units, City city ){
		Iterator it            = consumes.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<ComodityType,Float> pair = ( HashMap.Entry )it.next();
			ComodityType type  = pair.getKey  ();
			double amount      = pair.getValue() * units;
			city.removeComodity( type, amount );
		}
	}
	
	public void produce_units( double units, City city ){
		Iterator it            = produces.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<ComodityType,Float> pair = ( HashMap.Entry )it.next();
			ComodityType type              = pair.getKey  ();
			double amount                  = pair.getValue() * units;
			city.addComodity( type, amount );
		}
	}
	
	
	// ========== IO
	@Override
	public String toString(){
		//String s_consumes = FileSystem.MapToString( consumes );
		//String s_produces = FileSystem.MapToString( produces );
		//String s_machines = FileSystem.MapToString( machines );
		//return name+" "+cycle_time+" "+unit_space + s_consumes + s_produces + s_machines;
		return name+" "+cycle_time+" "+unit_space;
	}
	
	@Override
	public void fromString( String s ){
		//System.out.println( s );
		String [] words = s.split("\\s+");
		name         = words[0];
		cycle_time   = Double.parseDouble( words[1] );
		unit_space   = Double.parseDouble( words[2] );	
	}
	
	// ========== Constructor
	
	void init(){
		consumes = new HashMap<>(); // HashMap<ComodityType,Double>
		produces = new HashMap<>();
		machines = new HashMap<>();
	}
	
	public Technology(          ){	init(); } 
	public Technology( String s ){ init(); fromString( s ); }
	
}