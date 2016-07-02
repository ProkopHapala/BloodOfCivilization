
package BattleTester;

public class Division {
	
	// constants
	static float mortar_power = 8.0f;   // with respect to tank
	static float MG_power     = 10.0f;  // with respect to soldier
	static float flamer_power = 30.0f;  // with respect to soldier
	
	static int AT_man     = 4;
	static int MG_man     = 2;
	static int mortar_man = 2;
	
	// parameters
	float AP             =  100; // only for tanks and AT
	float Armor          =  80; // only for tanks
	//float moral          =  ; // only for infantry ?
	//float combat_speed   =  ; // ? how fast tanks are able to cross area
	
	// factors
	float entrench_factor = 1.0f;
	
	// actors 
	Brigade tanks;
	Brigade infantry;
	int ATs,     AT_reserve;      // agains tanks in defence
	int MGs,     MG_reserve;      // against infantery; dist defence; close defence+offence; 
	int flamers, flamer_reserve;  // against infantery in offence close combat entrenched units
	int RPGs,    RPGs_reserve;	  // against tanks in close combat
	int mortars, mortar_reserve;  // close artilery support against infantery
	
	int ninf_dist(){
		return infantry.n - ATs - mortars;  
	} 
	
	float dist_power_def ( TerrainType terrain ){ 
		//return ( infantry.n - AT_man*ATs - mortar_man  *mortars + (MG_power - MG_man)*MGs )*terrain.def_light
		return ( infantry.n + MG_power*MGs         )*terrain.def_light
			 + ( tanks.n    + mortar_power*mortars )*terrain.def_heavy; 
	}
	
	float dist_power_att ( TerrainType terrain ){ 
		//return ( infantry.n              - mortar_man  *mortars                           )*terrain.att_light
		return ( infantry.n + MG_power*MGs         )*terrain.att_light
			 + ( tanks.n    + mortar_power*mortars )*terrain.att_heavy;
	}
	
	float close_power_inf( TerrainType terrain, float n_suppressed ){ 
		float  npassed         = infantry.n - n_suppressed;
		float  suppress_factor = npassed/infantry.n; 
		//return suppress_factor*( infantry.n   - (MG_power - MG_man)*MGs - flamers   )*terrain.att_light
		return suppress_factor*( infantry.n   - MG_power*MGs )*terrain.att_light
			+                  flamers                        *terrain.flamer;
	}
}
