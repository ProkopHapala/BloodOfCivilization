
package BoC;

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

interface TxtStorbale {
	public void readFromTxt( BufferedReader reader ) throws IOException;
	public void writeToTxt ( BufferedWriter writer ) throws IOException;
	
}

interface StringIO {
	public void fromString( String s );
}

interface Named {
	public String getName( );
}

// ========== Static objects

public class FileSystem {
	
	public static String MapToString( Map<Named,StringIO> mp ){
		StringBuilder sb = new StringBuilder( 3 * mp.size() );
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			sb.append( sb + "\n"                   );
			sb.append( pair.getValue().toString()  );
			sb.append( "\n"                        );
			//System.out.println( pair.getKey() + " = " + pair.getValue() );
		}
		return sb.toString(); 
	}
	
	public static BufferedImage loadImage( String filename ){
		String full_path = Globals.data_path + filename;
		//System.out.println( " loadImage full_path: " + full_path );
		try{
			File file = new File( full_path );
			//System.out.println( " loadImage full_path: file.getCanonicalPath() " + file.getCanonicalPath() );
			return ImageIO.read( new File( Globals.data_path + filename ) );
		} catch (Exception e) { 
			System.out.println( " error in loadImage file: " + full_path );
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedReader getReader( String filename ){
		String full_path = Globals.data_path + filename;
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
		String full_path = Globals.data_path + filename;
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
	
	public static void loadFromTxt( String filename, TxtStorbale strorable ) {
		BufferedReader reader = getReader( filename );
		try { strorable.readFromTxt( reader ); reader.close(); } catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void saveToTxt( String filename, TxtStorbale strorable ){
		BufferedWriter writer = getWriter( filename );
		try { strorable.writeToTxt( writer ); writer.close(); } catch (Exception e) { e.printStackTrace(); }
	}
	
}
