
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

@FunctionalInterface
interface Parser<T> {
    public T parse(String s);
}

// ========== Static objects

public class FileSystem {
	
	/*
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
	
	public static String MapToStringNumber( Map<Named,Number> mp ){
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
	*/
	
	/*
	public static <TYPE> void MapFromString_Double( Map<TYPE,Double> mp, Map<String,TYPE> dict, String s ){ 
		String [] words     = s.split("\\s+");
		for ( int i=0; i<words.length; i++ ){
			String [] tuple = s.split("=");
			TYPE obj        = dict.get( tuple[0] );
			Double num      = Double.parseDouble( tuple[1] );
			mp.put( obj, num );	
		}
	}
	
	public static <TYPE> void MapFromString_Integer( Map<TYPE,Integer> mp, Map<String,TYPE> dict, String s ){ 
		String [] words     = s.split("\\s+");
		for ( int i=0; i<words.length; i++ ){
			String [] tuple = s.split("=");
			TYPE obj        = dict.get( tuple[0] );
			Integer num     = Integer.parseInt( tuple[1] );
			mp.put( obj, num );	
		}
	}
	*/
	
	public static < TYPE, NUM extends Number> void MapFromString( Map<TYPE, NUM> mp, Map<String,TYPE> dict, String s, Parser<NUM> parser) {
		//System.out.println( "++++ s:   |"+s+"|" );
		for (String word : s.split("\\s+")) {
			//System.out.println( word );
			int idx  = word.indexOf('=');
			TYPE obj = dict.get( word.substring(0, idx) );
			//System.out.println( word+"|"+word.substring(0, idx)+ "|"+word.substring(idx + 1)+"|"+obj+"|"+parser+"|"+mp );
			mp.put( obj, parser.parse( word.substring(idx + 1) ) );
		}
	}
	
	/*
	public static <TYPE extends Named> void MapFromString( Map<TYPE,Double> mp, Map<String,TYPE> dict, String s ){ 
		String [] words     = s.split("\\s+");
		for ( int i=1; i<words.length; i++ ){
			String [] tuple = s.split("=");
			TYPE obj        = dict.get( tuple[0] );
			Double num      = Double.parseDouble( tuple[1] );
			mp.put( obj, num );	
		}
	}
	*/
	
	/*
	public static <TYPE, NUM extends Number> 
	void MapFromString( Class<NUM> clazz, Map<TYPE,NUM> mp, Map<String,TYPE> dict, String s ){ 
		String [] words     = s.split("\\s+");
		NUM  num;
		try{
			num = clazz.newInstance();
		} catch (Exception e) { e.printStackTrace(); }
		for ( int i=1; i<words.length; i++ ){
			String [] tuple = s.split("=");
			TYPE obj        = dict.get( tuple[0] );
			//NUM  num      = NUM.valueOf( tuple[1] );
			if( num instanceof Integer ){
				num = Integer.parseInt( tuple[1] );
			}else{
				num = Double.parseDouble( tuple[1] );
			}
			mp.put( obj, num );	
		}
	}
	*/
	
/*	
	public static <NUM extends Number> 
	void MapFromString( Map<String,NUM> mp, String s ){ 
		String [] words     = s.split("\\s+");
		
		for ( int i=1; i<words.length; i++ ){
			String [] tuple = s.split("=");
			String name_string = tuple[0];
			String val_string  = tuple[1];
			
			
			// 1: is there any overriden string->Number conversion ?
			NUM num = Number.decode( val_string );
			
			// 2: alternative using some reflections ? Does not work either
			//NUM  num;
			//if( num instanceof Integer ){
			//	num = Integer.parseInt( val_string );
			//}else{
			//	num = Double.parseDouble( val_string );
			//}
			
			mp.put( name_string, num );	
		}
	}
*/	
	
	
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
