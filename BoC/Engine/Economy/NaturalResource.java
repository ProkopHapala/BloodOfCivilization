
package BoC.Engine.Economy;

import BoC.Engine.Economy.MachineType;
import BoC.Engine.Globals;
import BoC.utils.StringIO;

public class NaturalResource implements StringIO {
	
	public ComodityType type;
	public double maxYield;
	public double capacity;
		
	public double getYield( double workforce ){
		return Globals.explotationFunction( workforce, maxYield, capacity  );
	}
	
	// ========== IO
	
	//@Override
	public String getName(){
		return type.name;
	}
	
	@Override
	public String toString(){
		return type.name+" "+maxYield+" "+capacity;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");   
		//System.err.println( words[0]+"|"+words[1]+"|"+words[2]  );
		type      = Globals.comodityTypes.get( words[0]  );  
		maxYield  = Double.parseDouble( words[1] );  
		capacity  = Double.parseDouble( words[2] ); 
	}
	
}
