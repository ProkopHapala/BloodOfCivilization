package BoC.Engine.Economy;

import BoC.Engine.Globals;
import BoC.Engine.City;
import BoC.utils.StringIO;
import java.util.*;

public class Factory implements StringIO{
    public double size;
	public double activity;
    public Technology technology;
	public HashMap<MachineType,Integer> machines;
	public City city;

	/*
	ComodityType limiting_comodity;
	MachineType  limiting_machine;
	double limiting_comodity
	*/	
	
	public MachineType getLimitingMachine( HashMap<MachineType,Integer> map1, HashMap<MachineType,Integer> map2 ){
		double      min_factor = Double.POSITIVE_INFINITY;
		MachineType min_type   = null;
		Iterator it = map1.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<MachineType,Integer> pair = ( HashMap.Entry )it.next();
			MachineType type   = pair.getKey  ();
			double factor      = pair.getValue() / map2.get( pair.getKey() );
			if( factor < min_factor ){
				min_factor = factor;
				min_type   = type; 
			}
		}
		return min_type;
	}
	
	public ComodityType getLimitingComodity( HashMap<ComodityType,ComodityManager> map1, HashMap<ComodityType,Double> map2 ){
		double      min_factor = Double.POSITIVE_INFINITY;
		ComodityType min_type  = null;
		Iterator it = map1.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<ComodityType,ComodityManager> pair = ( HashMap.Entry )it.next();
			ComodityType type  = pair.getKey  ();
			double factor      = pair.getValue().stored / map2.get( pair.getKey() );
			if( factor < min_factor ){
				min_factor = factor;
				min_type   = type; 
			}
		}
		return min_type;
	}
	
	public double produce( double max_units_alowed, double delta_t ){
		ComodityType limiting_comodity = getLimitingComodity( city.comodities, technology.consumes );
		MachineType  limiting_machine  = getLimitingMachine ( machines,   technology.machines );
		double max_units_resources  = technology.consumes.get( limiting_comodity );
		double max_units_per_cycle  = technology.machines.get( limiting_machine  );
		double max_units_delta_t    = max_units_per_cycle * delta_t  / technology.cycle_time ;
		double max_units_able       = Math.min( max_units_resources, max_units_delta_t );
		double n_units              = Math.min( max_units_alowed,    max_units_able    );
		technology.consume_units( n_units, city );
		technology.produce_units( n_units, city );
		return n_units;
	}
	
	// ========== IO
	
	@Override
	public String getName(){
		return technology.name;
	}
		
	@Override
	public String toString(){
		return technology.name+" "+size+" "+activity;
	}
	
	@Override
	public void fromString( String s ){
		//System.out.println( "ComodityManager.fromString"+ s );
		s = s.trim();
		String [] words = s.split("\\s+");
		//System.out.println( "ComodityManager.fromString: "+ words[0] +";"+ words[1] +";"+ words[2] );
		technology = Globals.technologies.get( words[0] );
		size       = Double.parseDouble( words[1] );
		activity   = Double.parseDouble( words[2] );	
	}

}