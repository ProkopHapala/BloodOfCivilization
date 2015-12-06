package BoC;
        
public class ComodityType extends GameObject{
	
	double transport_weight; // transport weight can difer from unit weight due to container
	double transport_volume;
	
	double price_max;
	double price_normal;
        	
	// ========== IO
	
	@Override
	public String toString(){
		return name+" "+transport_weight+" "+transport_volume+" "+price_max+" "+price_normal;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		name             = words[0];  
		transport_weight = Double.parseDouble( words[1] );  
		transport_volume = Double.parseDouble( words[2] );  
		price_max        = Double.parseDouble( words[3] );  
		price_normal     = Double.parseDouble( words[4] );  
	}
	
	// ========== Constructor
	
	public ComodityType( ){	} 
	public ComodityType( String s ){ fromString( s ); }
	
}