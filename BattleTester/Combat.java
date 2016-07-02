
package BattleTester;

public class Combat {
	
	// consts
	float inf_disable_rate = 0.1f;
	//float inf_kill_rate  = 0.1f;
	//float tank_kill_rate = 0.5f;
	
	// parameters
	TerrainType terrain;
	
	// variables
	Division att;
	Division def;
	Division defSupp;      // combat support from nearby units (?) 
	float def_IndirectSupport=0.0f;
	float att_IndirectSupport=0.0f;
	//float att_Artilery;
	//float att_AirSupport;
	//float def_Artilery;
	//float def_AirSupport;
	
	
	void resolve( boolean att_tankCQC, boolean def_tankCQC, boolean surprise ){
		
		// --- armored assault
		float att_pierce = penetration_func( att.AP * def.entrench_factor, def.Armor );
		float def_pierce = penetration_func( def.AP, att.Armor     );
		att.tanks.kill( def_pierce *       ( def.ATs + def.tanks.n ) );
		att.tanks.kill( def_pierce *       ( att.tanks.n           ) );
		
		//float att_loss   = def_pierce *    ( def.ATs + def.tanks.n ); 
		//float def_loss   = def_pierce *    ( att.tanks.n           );
		
		// --- infantry assault
		float att_power     = ( att.dist_power_att( terrain ) + att_IndirectSupport ) * def.entrench_factor;
		float def_power     = ( def.dist_power_def( terrain ) + def_IndirectSupport );
		float pass_chance   = powerBalance_func( att_power, def_power );
		//float loss = loss_func( odds );
		//float pass = pass_func( odds );
		float   natt_suppressed = (    pass_chance  * def_power );
		float   ndef_suppressed = ( (1-pass_chance) * att_power );
		float   natt_inf        = att.infantry.n - natt_suppressed;
		float   ndef_inf        = att.infantry.n - natt_suppressed;
		float   inf_att_loss    = natt_suppressed * inf_disable_rate; // this should depend on will power? trade loss per harder attack 
		float   inf_def_loss    = natt_suppressed * inf_disable_rate; 
		
		// --- CQC
		att_power  = att.close_power_inf( terrain, natt_suppressed );
		def_power  = def.close_power_inf( terrain, ndef_suppressed );
		if( att_tankCQC ){
			att_power  += att.tanks.n  * terrain.tankCQC;
			att.tanks.kill( def.RPGs * terrain.RPG );
		}
		if( def_tankCQC ){
			def_power += def.tanks.n   * terrain.tankCQC;
			def.tanks.kill( att.RPGs * terrain.RPG );
		}
		pass_chance   = powerBalance_func( att_power, def_power );
	}
	
	
	// ---- detailed functions
	
	float penetration_func( float penetration, float armor ){
		float x = (penetration-armor)/armor;
		return x/(1+Math.abs(x));
	}
	
	float powerBalance_func( float att, float def ){		
		float x = (att-def)/def;
		return x/(1+Math.abs(x));
	}
	
	float entrench2armor( float entrench ){		return entrench*10;
	}
	
	float loss_func( float odds ){ 
		float x = 1-odds; 
		return x*x;
	}
	
	float pass_func( float odds ){ return 1-odds*odds; }
	
	
}
