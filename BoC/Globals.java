
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
	
	public static WorldMap               worldMap;
	public static HashMap< String, SiteType      > siteTypes      = new HashMap<>( );
	public static HashMap< String, City          > cities         = new HashMap<>( );
	public static HashMap< String, ComodityType  > comodityTypes  = new HashMap<>( );
	public static HashMap< String, MachineType   > machineTypes   = new HashMap<>( );
	public static HashMap< String, Technology    > technologoies  = new HashMap<>( );
	
	public static void initSiteTypes(){
		/*
		SiteType siteType = new SiteType( "plain" );
		siteTypes.put( siteType.name, siteType );
		*/
		BufferedReader reader = FileSystem.getReader( "SiteTypes.txt" );
		String line;
		try{
			while( null != ( line = reader.readLine() )  ){
				SiteType type = new SiteType( line );
				siteTypes.put( type.name, type );
			};
		} catch (Exception e) { e.printStackTrace(); };
	}
	
	public static void initCities(){
		FileSystem.loadObjectMap( City.class, cities, "Cities.txt" );
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
		
	static public void exit() {
		System.exit(0);
	}
	
}
