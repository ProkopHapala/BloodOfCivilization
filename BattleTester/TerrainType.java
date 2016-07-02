
package BattleTester;

public class TerrainType {
	
	// weapon efectivity
	float def_heavy  = 10.0f; // how much fire from defending heavy-weapon  suppress attacking infantry in this area
	float att_heavy  = 6.0f;  // how much fire from attacking heavy-weapon  suppress defending infantry in this area
	float def_light  = 1.0f;  // how much fire from defending heavy-weapon  suppress attacking infantry in this area
	float att_light  = 0.3f;  // how much fire from attacking heavy-weapon  suppress defending infantry in this area
	float flamer     = 30.0f; // flamer
	float RPG        = 1.0f;
	float tankCQC    = 10.0f;
}
