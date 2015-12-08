package BoC.Engine;

import BoC.utils.FileSystem;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class SiteType extends GameObject {
	public double terrain;      // how rugh is terrain for transit
	public double bush;         // how much vegetation there is for cover ( in urban areas these are houses )
	public double fertility;
	public double temperature;
	public double water;
	
	Color color = null;
	//String imgName = "img/ground_Grass.png";
	String         imgName = "img/ground_Grass.png";
	BufferedImage  img = null; 
	
	// ========== IO
	
	@Override
	public String toString(){
		return name+" "+terrain+" "+bush+" "+fertility+" "+temperature+" "+water;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		name            = words[0];
		terrain         = Double.parseDouble( words[1] );     
		bush            = Double.parseDouble( words[2] );         
		fertility       = Double.parseDouble( words[3] );  
		temperature     = Double.parseDouble( words[4] );  
		water           = Double.parseDouble( words[5] );
		
		if( words[6].charAt(0) == 'i' ){
			imgName         = words[6];
			this.img = FileSystem.loadImage( imgName );
		}else{
			int c = Integer.parseInt( words[6], 16);
			color = new Color( c );
		}
		
	}
	
	// ========== Constructor
	
	public SiteType( ){
	/*	
		name         = "default";
		terrain      = 0.0d;
		bush         = 0.0d;
		fertility    = 1.0d;
		temperature  = 20.0d;
		water        = 0.1d;	
		color = new Color( 150, 200, 100 );
	*/	
	}
		
	public SiteType( String s ){ fromString( s ); }
	
}