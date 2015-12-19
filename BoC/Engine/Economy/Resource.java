
package BoC.Engine.Economy;

import BoC.Engine.Economy.MachineType;
import BoC.Engine.Globals;
import BoC.utils.StringIO;

public interface Resource {
	
	public double getYield( );
//		double saturation = workforce / capacity;
//		return maxYield * (  1 / (  1 +  ( saturation * saturation ) ) );
	
	
	/*
	// ========== IO
	
	//@Override
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return type.name+" "+maxYield+" "+capacity+" "+workforce;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");       
		type      = Globals.comodityTypes.get( words[0]  );  
		maxYield  = Double.parseDouble( words[1] );  
		capacity  = Double.parseDouble( words[2] ); 
		workforce = Double.parseDouble( words[3] ); 
	}
	*/
	
}
