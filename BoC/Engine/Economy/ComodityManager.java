package BoC.Engine.Economy;
        
import BoC.Engine.Globals;
import BoC.utils.StringIO;

public class ComodityManager implements StringIO{
	public double stored;
	public double price;
    public  ComodityType type;
        
    // price model
    public double supply_target;
    public double price_stiffness;
        
	public double add( double amount ){
		stored += amount;
		// later there will be probably more.
		return amount;
	}
	
	public double remove( double amount ){
		stored -= amount;		
		// later there will be probably more
		return amount;
	}
	
        // ======= price model =======
        // description:
        // price is determined based on global price_normal and price_max of the comodity 
        // and the setting of targed commodity amount present in the city
        // the resulting price of comodities and goods determines which should be 
        // either produced from other comodities or transported by convoys from other cities 
        
	public void evalPrice( ){
		// there should be some economical model e.g. supply-demand curve ?
		// or maybe we will estimate prices as some eingen value of comodity dependency matrix ?
	}
            
        public double price_function( double supply ){
            return type.price_max / ( 1.0d -  price_stiffness * supply  );
        }
        
        public void update_price( ){
            price = price_function( stored );
        }
        
        public void fit_price_function( ){
            //   price_normal =  price_max / ( 1.0d -  price_stiffness *  supply_target  )
            //   price_normal  - price_normal *  price_stiffness *  supply_target  ) =  price_max 
            //   ( price_normal - price_max ) / (  price_normal * supply_target )  = price_stiffness
            price_stiffness = ( type.price_normal - type.price_max ) / (  type.price_normal * supply_target );
            
        }
        
	// ========== IO
	
	//@Override
	public String getName(){
		return type.name;
	}
		
	//@Override
	public String toString(){
		return type.name+" "+stored+" "+price;
	}
	
	//@Override
	public void fromString( String s ){
		//System.err.println( "ComodityManager.fromString:"+ s );
		String [] words = s.split("\\s+");
		//System.err.println( "ComodityManager.fromString:"+ words[0] +";"+ words[1] +";"+ words[2] );
		type    = Globals.comodityTypes.get( words[0] );
		stored  = Double.parseDouble( words[1] );
		price   = Double.parseDouble( words[2] );	
	}
        
        
	public ComodityManager(){ };
	public ComodityManager( ComodityType type_, double stored_ ){ type = type_; stored = stored_;};
}