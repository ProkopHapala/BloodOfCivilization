package BoC;
import java.util.*;

/*
Technology is ( or FactoryType ? ) is a production process which converts some comodities into some other comodities and machines, with asistence of other machines
*/
class Technology implements StringIO{
	String name;
	double cycle_time;  // time to produce unit
	double unit_space;  // unit space taken in factory
	
	HashMap<ComodityType,Double> consumes;
	HashMap<ComodityType,Double> produces;
	HashMap<MachineType,Integer> machines;
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
		return name+" "+cycle_time+" "+unit_space;			
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		name         = words[0];
		cycle_time   = Double.parseDouble( words[1] );
		unit_space   = Double.parseDouble( words[2] );
	}
	
	// ========== Constructor
	
	public Technology( ){	} 
	public Technology( String s ){ fromString( s ); }
	
}