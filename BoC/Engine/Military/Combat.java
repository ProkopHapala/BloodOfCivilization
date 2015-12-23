package BoC.Engine.Military;

import BoC.Engine.GameObject;
import BoC.Engine.WorldSite;
import java.util.*;

public class Combat extends GameObject {
    Army Attacker, Deffender;
	WorldSite      site;
	double    start_time;
	
	// temp vars
	ArrayList<Brigade> att_units, def_units;  
	
	BrigadePriorityComparator priority_comparator;
		
	// artilery barrage and air bombardment which will lower enemy combat rediness
	public void pre_fireSuppoort(  ){    
		
	}
	
	// precission artilery and air strikes to targets uncovered during the battle
	// + elimination of disorganized routing units
	public void post_fireSuppoort(  ){
		
	}
	
	public int combatTurn(  ){
		
		pre_fireSuppoort(  );
		
		for( Brigade b : att_units ){ b.prepare_for_combat( site, true ); } 
		att_units.sort( priority_comparator );
			
		for( Brigade b : def_units ){ b.prepare_for_combat( site, false ); } 
		def_units.sort( priority_comparator );
		
		// execute combat acction of att_units and att_units according to its priority
		Iterator<Brigade> it_att = att_units.iterator();
		Iterator<Brigade> it_def = def_units.iterator();
		Brigade batt = it_att.next();
		Brigade bdef = it_att.next();
		while ( true ) {
			if( ( batt != null ) && ( bdef != null ) ){
				if( batt.combat_priority > bdef.combat_priority ){
					batt.do_combat( att_units, def_units );
					batt = it_att.next();
				}else{
					bdef.do_combat( def_units, att_units );
					bdef = it_att.next();
				}
			}else if( batt != null ){
				batt.do_combat( att_units, def_units );
				batt = it_att.next();
			}else if( bdef != null ){
				bdef.do_combat( def_units, att_units );
				bdef = it_att.next();
			} else {  // no next batt nor bdef
				break;
			}
			
		}
		
		post_fireSuppoort(  );
		
		return check_end();
	} 
	
	public int check_end(){
		boolean att_fine = false;
		boolean def_fine = false; 	
		for( Brigade b : att_units ){ att_fine |= b.combat_capable(); } 
		for( Brigade b : def_units ){ def_fine |= b.combat_capable(); }
		if( att_fine && def_fine ){
			return 0;    // fight continues
		}else if( att_fine ){
			return 1;    // attacker won
		}else if( def_fine ){
			return 2;   // deffender won
		}else{
			return 3;   // fight endend without winner
		}
	}
	
}