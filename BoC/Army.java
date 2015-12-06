package BoC;

import java.util.*;

public class Army{
	Player owner;
    HashMap<CombatantType,Brigade> brigades;
	HashMap<ComodityType,ComodityManager> store;
	
	Site site;
	double x,y;	// exact position 
	City city;  // can be null if not min city
	
	// posibilities ?
	double organized;
}