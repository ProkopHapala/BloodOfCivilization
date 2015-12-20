
package BoC.Engine;

import BoC.Engine.Economy.*;
import BoC.Engine.Military.*;
import BoC.utils.*;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


import java.util.*;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Globals {
	
	// global constants
	
	//public static Main   form;
	
	//public static Events EVENTS = new Events();
	
	// global variables
	
	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

	// ======== Game Content
	
	public static WorldMap worldMap;

	public static HashMap< String, City          > cities         = new HashMap<>( ); // split for each player ?
	public static HashMap< String, Route         > routes         = new HashMap<>( );
	
	public static HashMap< String, SiteType      > siteTypes      = new HashMap<>( );
	public static HashMap< String, ComodityType  > comodityTypes  = new HashMap<>( );
	public static HashMap< String, MachineType   > machineTypes   = new HashMap<>( );
	public static HashMap< String, Technology    > technologies  = new HashMap<>( );
	public static HashMap< String, CombatantType > combatantTypes = new HashMap<>( );
	
	public static HashMap< String, Army > armies = new HashMap<>( );   // this should be probably split for each player ?
	
	public static HashMap< String, Player > players = new HashMap<>( );
	
	static ComodityType foot_type;
	
	
	// ======== Game dynamics globals
	
	static double dt = 0.01d;
	
	public static double explotationFunction( double workforce, double capacity, double maxYield ){
		double saturation = workforce / capacity;
		return maxYield * (  1 / (  1 +  ( saturation * saturation ) ) );
	}
	
	public static void initGame(){
		initComodityTypes( "ComodityTypes.txt" );
		initMachineTypes ( "MachineTypes.txt"  );
		initTechnologies ( "Technologies.txt"  );
		initSiteTypes    ( "SiteTypes.txt"     );
		
		worldMap = new WorldMap( 4, 4, 3  );
		worldMap.GenerateRandom( 154545 );
		
		initCities( "Cities.txt" );
		initRoutes( "Routes.txt" );
		
		//map.saveToTxt( "map.txt" );
		//FileSystem.saveToTxt  ( "map.txt", Globals.worldMap );
		//FileSystem.loadFromTxt( "map.txt", map );
		
		initCombatantTypes( "CombatantTypes.txt" );
		initArmies        ( "Armies.txt"         );
		
	}
	
	public static void initSiteTypes( String filename ){
		System.out.println( " =============== initSiteTypes "  );
		//FileSystem.loadObjectMap( SiteType.class, siteTypes, "SiteTypes.txt" );
		//System.err.println( "initCities comodityTypes: "+comodityTypes );
		BufferedReader reader = FileSystem.getReader( filename );
		String line;
		try{
			while( true ){
				while ( true ){
					line = reader.readLine();
					if( line == null ){ return; } else if( !line.trim().isEmpty() ){ break; }
				}
				//System.err.println( "initCities City       Line: "+line );	
				//Debugger.log("message here");
				SiteType stype = new SiteType( line );
				siteTypes.put( stype.name, stype );
	
				line = reader.readLine(); 
				//System.err.println( "initCities comodities Line: "+line );	
				line = line.substring( line.indexOf(":")+1 );
				FileSystem.MapFromString( stype.resources, comodityTypes, line, ";", NaturalResource.class );
				//System.err.println( "initCities city.comodities >>>> "+city.comodities );
				
			}
		} catch (Exception e) { e.printStackTrace(); };
	}
	
	/*
	public static void initCities(){
		FileSystem.loadObjectMap( City.class, cities, "Cities.txt" );
	}
	*/
	
	public static void initRoutes( String filename ){
		System.out.println( " =============== initRoutes "  );
		FileSystem.loadObjectMap( Route.class, routes, filename );
	}
		
	public static void initComodityTypes( String filename ){
		System.out.println( " =============== initComodityTypes "  );
		FileSystem.loadObjectMap( ComodityType.class, comodityTypes, filename );
	}
	
	public static void initMachineTypes( String filename ){
		System.out.println( " =============== initMachineTypes "  );
		FileSystem.loadObjectMap( MachineType.class, machineTypes, filename );
	}
	
	public static void initTechnologies( String filename ){
		System.out.println( " =============== initTechnologies "  );
		BufferedReader reader = FileSystem.getReader( filename );
		String line;
		try{
			while( true ){
				while ( true ){
					line = reader.readLine();
					if( line == null ){ return; } else if( !line.trim().isEmpty() ){ break; }
				}
				
				//System.err.println( "============="+line );
				
				Technology tech = new Technology ( line );
				technologies.put( tech.name, tech );
				
				line = reader.readLine(); 
				line = line.substring( line.indexOf(" ")+1 );
				FileSystem.MapFromString( tech.consumes, comodityTypes, line, Double::valueOf  );
				
				line = reader.readLine(); 
				line = line.substring( line.indexOf(" ")+1 );
				FileSystem.MapFromString( tech.produces, comodityTypes, line, Double::valueOf  );
				
				line = reader.readLine(); 
				line = line.substring( line.indexOf(" ")+1 );
				FileSystem.MapFromString( tech.machines, machineTypes, line, Integer::valueOf  );
										
				//System.err.println( tech.toString() );
			}
		} catch (Exception e) { e.printStackTrace(); };
	}
	
	public static void initCities( String filename ){
		System.out.println( " =============== initCities() "  );
		//System.err.println( "initCities comodityTypes: "+comodityTypes );
		BufferedReader reader = FileSystem.getReader( filename );
		String line;
		try{
			while( true ){
				while ( true ){
					line = reader.readLine();
					if( line == null ){ return; } else if( !line.trim().isEmpty() ){ break; }
				}
				//System.err.println( "initCities City       Line: "+line );	
				//Debugger.log("message here");
				City city = new City( line );
				cities.put( city.name, city );
	
				line = reader.readLine(); 
				//System.err.println( "initCities comodities Line: "+line );	
				line = line.substring( line.indexOf(":")+1 );
				FileSystem.MapFromString( city.comodities, comodityTypes, line, ";", ComodityManager.class );
				//System.err.println( "initCities city.comodities >>>> "+city.comodities );
				
				line = reader.readLine(); 
				//System.err.println( "initCities comodities Line: "+line );	
				line = line.substring( line.indexOf(":")+1 );
				FileSystem.MapFromString( city.factories, technologies, line, ";", Factory.class );											
				//System.err.println( "initCities city.factories >>>> "+city.factories );
			}
		} catch (Exception e) { e.printStackTrace(); };
	}
	
	public static void initCombatantTypes( String filename ){
		System.out.println( " =============== initCombatantTypes() "  );
		FileSystem.loadObjectMap( CombatantType.class, combatantTypes, filename );
		//for ( CombatantType type: combatantTypes.values() ){ System.err.println( type.toString() ); }
	}
	
	public static void initArmies( String filename ){
		System.out.println( " =============== initArmies() "  );
		BufferedReader reader = FileSystem.getReader( filename );
		String line;
		try{
			while( true ){
				while ( true ){
					line = reader.readLine();
					if( line == null ){ return; } else if( !line.trim().isEmpty() ){ break; }
				}
				//System.err.println( "initCities City       Line: "+line );	
				//Debugger.log("message here");
				Army army = new Army( line );
				armies.put( army.name, army );
	
				line = reader.readLine(); 
				//System.err.println( "initCities comodities Line: "+line );	
				line = line.substring( line.indexOf(":")+1 );
				FileSystem.MapFromString( army.comodities, comodityTypes, line, ";", ComodityManager.class );
				//System.err.println( "initArmies army.comodities >>>> "+army.comodities );
				
				line = reader.readLine(); 
				//System.err.println( "initCities comodities Line: "+line );	
				line = line.substring( line.indexOf(":")+1 );
				FileSystem.MapFromString( army.brigades, combatantTypes, line, ";", Brigade.class );											
				//System.err.println( "initArmies army.brigades >>>> "+army.brigades );
				//army.reassingBrigades();
				
			}
		} catch (Exception e) { e.printStackTrace(); };
				
	}
	
	public static void update(){
		for( Army army : armies.values() ){ army.update(dt); }
	}
	
			
	static public void exit() {
		System.exit(0);
	}
	
}
