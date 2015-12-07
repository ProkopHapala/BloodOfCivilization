
package BoC;

import java.util.*;

public class Brigade extends GameObject {
    
	// ==== Static
	
	// ==== State Variables
	
	Army army;
	CombatantType type;
	int n_tot;
	int n_capable;
	int n_alive;
	
	// ==== temp vars
	double attack_effectivness;  // multiplier 
	double actual_defence;
	double combat_priority;
	
	double actual_speed;

	// ==== Methods
	
	public double evalSpeed( Site site ){
		double f = site.type.terrain;
		return type.terrain_speed * f + (1-f)*type.speed;
	}
	
	public double distance( Site site ){
		int dx = ( army.site.ix - site.ix );
		int dy = ( army.site.ix - site.ix );
		return Math.sqrt( dx*dx + dy*dy );
	}
	
	public void prepare_for_combat( Site site, boolean attacking ){
		actual_speed    = evalSpeed( site ); 
		combat_priority = type.agility - distance(site) / actual_speed;
		
		attack_effectivness = 1.0d;
		actual_defence      = 1.0d;
		if( !attacking ){	
			actual_defence  *= type.defenceBonus;
		}
	}
	
	public void getDamage( double dmg ){
		// disabling damage
		int dcapable = (int) Math.round( dmg );
		int n_capable_ = n_capable - dcapable;
		if ( n_capable_ > 0 ){	
			n_capable = n_capable_;
		}else{
			n_capable = 0;
		}
		// killing damage
		int dalive = (int) Math.round( dmg * CombatantType.kill_rate );
		int n_alive_ = n_alive - dalive;
		if ( n_capable_ > 0 ){	
			n_alive = n_alive_;
		}else{
			n_alive = 0;
		}
	}
	
	public void do_combat( Collection<Brigade> friends, Collection<Brigade> enemies ){
		Brigade target = chooseTarget( enemies );
		double dmg = damageTo( target );
		target.getDamage( dmg );
	}
	
	public boolean combat_capable(){
		return ( n_capable > 0 );
	}
	
	
	double damageTo_air( Brigade enemy ){
		double damage = 0.0d;
		return damage;
	}
	
	double damageTo_ground( Brigade enemy ){
		double attack = 0.0d;
		CombatantType enemy_type = enemy.type;
		boolean hardTarget =  enemy_type.armor > CombatantType.armor_cutoff;
		if( hardTarget ){
			double over_armor = type.penetration - enemy_type.armor;
			if( over_armor > 0 ){
				attack = type.hard_attack;
			}
		}{
			attack = type.soft_attack;
		}
		double actual_attack = attack * attack_effectivness;
		double damage        = CombatantType.damage_function( actual_attack - enemy.actual_defence );
		
		return damage;
	}
	
	double damageTo( Brigade enemy ){
		if( enemy.type.air ){
			return damageTo_air( enemy );
		}else{
			return damageTo_ground( enemy );
		}
	}
	
	double estimateScore( Brigade enemy ){
		if( enemy.type.air ){
			return damageTo_air( enemy );
		}else{
			return damageTo_ground( enemy );
		}
	}
	
	Brigade chooseTarget( Collection<Brigade> enemies ){
		Brigade choosen=null;
		double best_score = Double.NEGATIVE_INFINITY;
		for ( Brigade enemy : enemies ){ 
			double score = estimateScore( enemy );
			if( score < best_score ){
				best_score = score;
				choosen    = enemy;
			}
		}
		return choosen; 
	}
	
		
}

// =================== Other utils
class BrigadePriorityComparator implements Comparator<Brigade> {
    @Override
    public int compare(Brigade a, Brigade b) {
        return a.combat_priority < b.combat_priority ? -1 : a.combat_priority == b.combat_priority ? 0 : 1;
    }
}