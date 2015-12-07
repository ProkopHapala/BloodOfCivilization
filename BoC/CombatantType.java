package BoC;

import java.util.*;

public class CombatantType extends GameObject{
	
	// ==== Static
	
	static final double damage_eps   = 1.0d; 
	static final double armor_cutoff = 1.0d; // is armor is higher than this value it is hard target
	static final double kill_rate    = 1.0d; // how many of wounded or damaged will be killed ?
	
	static double damage_function( double att_over_def ){
		if( att_over_def > 0 ){
			return 1/ ( damage_eps - att_over_def );
		}else{
			return att_over_def + damage_eps;
		}
	}
		
	// ==== Unit Paramaters	
	
	double size;            // determines probability to be hit and cover bonus
	double agility;         // determines priority in damaging que
	double supportDistance; //
		
	//attack bonusses
	double soft_attack;
	double hard_attack;
	double air_attack;
	double naval_attack;
	
	// armored warfare
	double armor;       // if larger than  armor_cutoff it is hard target. 
	double penetration; // hard attack
	
	// bonusses
	double defenceBonus;  // defence multiplier in cover 	
	
	// movement
	double speed;
	double speed_halfTerrain; 
	double speed_worstTerrain;
	
	// consumption
	double food_consumption;   // all time
	double fuel_consumption;   // move or combat
	double shell_consumption;  // shells per combat
	
	// flags
	boolean ground  = false;
	boolean naval   = false;
	boolean air     = false;
	boolean support = false;
	
	// functions

	// ========== IO
	
	private String flagToString( String flag_name, boolean flag ){
		if( flag ){ return flag_name+","; }else{ return ""; }
	}
	
	@Override
	public String toString(){
		return name
			+" "+size
			+" "+agility
			+" "+supportDistance
			+" "+soft_attack
			+" "+hard_attack
			+" "+air_attack
			+" "+naval_attack
			+" "+armor
			+" "+penetration
			+" "+defenceBonus
			+" "+speed
			+" "+speed_halfTerrain
			+" "+speed_worstTerrain
			+" "+food_consumption
			+" "+fuel_consumption
			+" "+shell_consumption+" "
			+flagToString( "ground" , ground   )
			+flagToString( "naval"  , naval    )
			+flagToString( "air"    , air      )
			+flagToString( "support", support  ) 
		;
	}
	
	@Override
	public void fromString( String s ){ 
		String [] words = s.split("\\s+");
		int i = 0;
		name               = words[i];   i++;
		size               = Double.parseDouble( words[i] );   i++;
		agility            = Double.parseDouble( words[i] );   i++;
		supportDistance    = Double.parseDouble( words[i] );   i++;
		soft_attack        = Double.parseDouble( words[i] );   i++;
		hard_attack        = Double.parseDouble( words[i] );   i++;
		air_attack         = Double.parseDouble( words[i] );   i++;
		naval_attack       = Double.parseDouble( words[i] );   i++;
		armor              = Double.parseDouble( words[i] );   i++;
		penetration        = Double.parseDouble( words[i] );   i++;
		defenceBonus       = Double.parseDouble( words[i] );   i++;
		speed              = Double.parseDouble( words[i] );   i++;
		speed_halfTerrain = Double.parseDouble( words[i] );   i++;
		speed_worstTerrain = Double.parseDouble( words[i] );   i++;
		food_consumption   = Double.parseDouble( words[i] );   i++;
		fuel_consumption   = Double.parseDouble( words[i] );   i++;
		shell_consumption  = Double.parseDouble( words[i] );   i++;
		flagsFromString( words[i] );
	}
	
	private void flagsFromString( String s ){
		String [] words = s.split(",");
		for( String word : words ){
			switch( word ){
				case "ground"  : ground = true; break;
				case "naval"   : ground = true; break;
				case "air"     : ground = true; break;
				case "support" : ground = true; break;
			}
		}
	}
	
	// ========== Constructor
	
	public CombatantType( ){ }
	public CombatantType( String s ){ fromString( s ); }
		
}

// =================== Other utils
/*
class AgilityComparator implements Comparator<CombatantType> {
    @Override
    public int compare(CombatantType a, CombatantType b) {
        return a.agility < b.agility ? -1 : a.agility == b.agility ? 0 : 1;
    }
}
*/