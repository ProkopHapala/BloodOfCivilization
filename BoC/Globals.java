
package BoC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import java.util.*;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Globals {
	
	// global constants
	
	public static Main   form;
	public static Player PLAYER;
	//public static Events EVENTS = new Events();
	
	public static String data_path = "/home/prokop/git/BloodOfCivilization/data/";
	
	public static Point mousePointOnScreen = new Point(0, 0);
	public static String version = "0.0.1";
	public static String INFO = " Blood of Civilization v " + version + " - Made by Prokop Hapala ";

	// global variables
	
	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

	// ======== Game Content
	
	public static WorldMap worldMap;

	public static HashMap< String, City          > cities         = new HashMap<>( ); // split for each player ?
	public static HashMap< String, Route         > routes         = new HashMap<>( );
	
	public static HashMap< String, SiteType      > siteTypes      = new HashMap<>( );
	public static HashMap< String, ComodityType  > comodityTypes  = new HashMap<>( );
	public static HashMap< String, MachineType   > machineTypes   = new HashMap<>( );
	public static HashMap< String, Technology    > technologoies  = new HashMap<>( );
	public static HashMap< String, CombatantType > combatantTypes = new HashMap<>( );
	
	public static HashMap< String, Army > armies = new HashMap<>( );   // this should be probably split for each player ?
	
	public static HashMap< String, Player > players = new HashMap<>( );
	
	// ======== Game dynamics globals
	
	static double dt = 0.01d;
	
	
	public static void initSiteTypes(){
		FileSystem.loadObjectMap( SiteType.class, siteTypes, "SiteTypes.txt" );
	}
	
	public static void initCities(){
		FileSystem.loadObjectMap( City.class, cities, "Cities.txt" );
	}
	
	public static void initRoutes(){
		FileSystem.loadObjectMap( Route.class, routes, "Routes.txt" );
	}
		
	public static void initComodityTypes(){
		FileSystem.loadObjectMap( ComodityType.class, comodityTypes, "ComodityTypes.txt" );
	}
	
	public static void initMachineTypes(){
		FileSystem.loadObjectMap( MachineType.class, machineTypes, "MachineTypes.txt" );
	}
	
	public static void initTechnologies(){
		BufferedReader reader = FileSystem.getReader( "Technologies.txt" );
		String line;
		try{
			while( true ){
				while ( true ){
					line = reader.readLine();
					if( line == null ){ return; } else if( !line.trim().isEmpty() ){ break; }
				}
				
				//System.out.println( "============="+line );
				
				Technology tech = new Technology ( line );
				technologoies.put( tech.name, tech );
				
				line = reader.readLine(); 
				line = line.substring( line.indexOf(" ")+1 );
				//FileSystem.MapFromString_Double( tech.consumes, comodityTypes, line  );
				FileSystem.MapFromString( tech.consumes, comodityTypes, line, Double::valueOf  );
				
				line = reader.readLine(); 
				line = line.substring( line.indexOf(" ")+1 );
				//FileSystem.MapFromString_Double( tech.produces, comodityTypes, line  );
				FileSystem.MapFromString( tech.produces, comodityTypes, line, Double::valueOf  );
				
				line = reader.readLine(); 
				line = line.substring( line.indexOf(" ")+1 );
				//FileSystem.MapFromString_Integer( tech.machines, machineTypes, line  );
				FileSystem.MapFromString( tech.machines, machineTypes, line, Integer::valueOf  );
												
				//System.out.println( tech.toString() );
			}
		} catch (Exception e) { e.printStackTrace(); };
	}
	
	public static void initCombatantTypes(){
		FileSystem.loadObjectMap( CombatantType.class, combatantTypes, "CombatantTypes.txt" );
		for ( CombatantType type: combatantTypes.values() ){ System.out.println( type.toString() ); }
	}
	
	public static void initArmies(){
		Brigade brigade;
			
		Army army1 = new Army( "army1", 18, 19 );   armies.put( army1.name, army1 );
		brigade  = new Brigade( combatantTypes.get("Tank"), army1, 5 );  
		army1.brigades.put( brigade.type, brigade );
		
		Army army2 = new Army( "army2", 15, 15 );   armies.put( army2.name, army2 );
		brigade  = new Brigade( combatantTypes.get("pikemen"), army2, 5 );  
		army2.brigades.put( brigade.type, brigade );
		
		army1.setTarget( worldMap.getSite( 10, 5 ) );
		
	}
	
	
	
	public static void update(){
		for( Army army : armies.values() ){ army.update(dt); }
	}
	
			
	static public void exit() {
		System.exit(0);
	}
	
}
