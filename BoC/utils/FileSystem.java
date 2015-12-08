
package BoC.utils;

import BoC.Engine.GameObject;

import java.util.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

// ========== Interfaces

// ========== Static objects

public class FileSystem {
	
	public static String data_path = "/home/prokop/git/BloodOfCivilization/data/";
	
	public static < TYPE, NUM extends Number> void MapFromString( Map<TYPE, NUM> mp, Map<String,TYPE> dict, String s, StringParser<NUM> parser) {
		//System.out.println( "++++ s:   |"+s+"|" );
		for (String word : s.split("\\s+")) {
			//System.out.println( word );
			int idx  = word.indexOf('=');
			TYPE obj = dict.get( word.substring(0, idx) );
			//System.out.println( word+"|"+word.substring(0, idx)+ "|"+word.substring(idx + 1)+"|"+obj+"|"+StringParser+"|"+mp );
			mp.put( obj, parser.parse( word.substring(idx + 1) ) );
		}
	}
	
	public static <TYPE extends GameObject> void loadObjectMap( Class<TYPE> clazz, Map<String,TYPE> mp, String filename ){
		BufferedReader reader = FileSystem.getReader( filename );
		String line;
		try{
			while( null != ( line = reader.readLine() )  ){
				//System.out.println( clazz +" "+ mp +" "+ filename  );
				TYPE item = clazz.newInstance( );
				item.fromString( line );
				mp.put( item.getName(), item );
				//System.out.println( item.toString() );
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static BufferedImage loadImage( String filename ){
		String full_path = data_path + filename;
		//System.out.println( " loadImage full_path: " + full_path );
		try{
			File file = new File( full_path );
			//System.out.println( " loadImage full_path: file.getCanonicalPath() " + file.getCanonicalPath() );
			return ImageIO.read( new File( data_path + filename ) );
		} catch (Exception e) { 
			System.out.println( " error in loadImage file: " + full_path );
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedReader getReader( String filename ){
		String full_path = data_path + filename;
		//System.out.println( " writing Map to : " + full_path );
		BufferedReader reader = null;
		try {
			File file = new File( full_path );
			//System.out.println( " writing Map to : " + file.getCanonicalPath() );
			reader    = new BufferedReader( new FileReader( file  ) );
		} catch (Exception e) { 
			System.out.println( " error in getReader file: " + full_path );
			e.printStackTrace(); 
		}
		return reader;
	}
	
	public static BufferedWriter getWriter( String filename ){
		String full_path = data_path + filename;
		//System.out.println( " writing Map to : " + full_path );
		BufferedWriter writer = null;
		try {
			File file = new File( full_path );
			//System.out.println( " writing Map to : " + file.getCanonicalPath() );
			writer    = new BufferedWriter( new FileWriter( file  ) );
		} catch (Exception e) { 
			System.out.println( " error in getWriter file: " + full_path );
			e.printStackTrace(); 
		}
		return writer;
	}
	
	public static void loadFromTxt( String filename, TxtStorable strorable ) {
		BufferedReader reader = getReader( filename );
		try { strorable.readFromTxt( reader ); reader.close(); } catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void saveToTxt( String filename, TxtStorable strorable ){
		BufferedWriter writer = getWriter( filename );
		try { strorable.writeToTxt( writer ); writer.close(); } catch (Exception e) { e.printStackTrace(); }
	}
	
}
