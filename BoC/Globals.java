
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
	
	public static String data_path = "/home/prokop/Dropbox/MyDevSW/java/BloodOfCivilization/data/";
	
	public static Point mousePointOnScreen = new Point(0, 0);
	public static String version = "0.0.1";
	public static String INFO = " Blood of Civilization v " + version + " - Made by Prokop Hapala ";

	// global variables
	
	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public static Map               map;
	public static HashMap< String, SiteType > siteTypes = new HashMap<>( );
	public static HashMap< String, City     > cities    = new HashMap<>( );
	
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
		BufferedReader reader = FileSystem.getReader( "Cities.txt" );
		String line;
		try{
			while( null != ( line = reader.readLine() )  ){
				City city = new City( line );
				cities.put( city.name, city );
				System.out.println( city.toString() );
			};
		} catch (Exception e) { e.printStackTrace(); };
	}
		
	static public void exit() {
		System.exit(0);
	}
	
}
