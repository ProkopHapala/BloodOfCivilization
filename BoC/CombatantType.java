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
	double terrain_speed;
	
	// consumption
	double fuel_consumption;   // move or combat
	double food_consumption;   // all time
	double shell_consumption;  // shells per combat
	
	// flags
	boolean ground;
	boolean naval;
	boolean air;
	boolean support;
	
	// functions
	
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