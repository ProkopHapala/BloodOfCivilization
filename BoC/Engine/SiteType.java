package BoC.Engine;

import BoC.utils.FileSystem;
import BoC.utils.Sprite;
import BoC.utils.StringIO;

import BoC.Engine.Economy.NaturalResource;
import BoC.Engine.Economy.ComodityType;

import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class SiteType implements StringIO {

	public String name;
	public double terrain;             // how rugh is terrain for transit
	public double bush;                // how much vegetation there is for cover ( in urban areas these are houses )
	public double temperature;
	public double water;
	
	public HashMap<ComodityType,NaturalResource> resources;
	public double workforce_renorm;
	
	Color color = null;
	/*
	//String imgName = "img/ground_Grass.png";
	String         imgName = "img/ground_Grass.png";
	BufferedImage  img = null; 
	*/
	Sprite sprite;
	
	// ========== IO
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name+" "+terrain+" "+bush+" "+temperature+" "+water;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		name            = words[0];
		terrain         = Double.parseDouble( words[1] );     
		bush            = Double.parseDouble( words[2] );         
		temperature     = Double.parseDouble( words[3] );  
		water           = Double.parseDouble( words[4] );  
		
		String svisual = words[5].trim();
		if( svisual.charAt(0) == 'i' ){
			sprite = new Sprite( svisual );
		}else{
			int c = Integer.parseInt( svisual, 16);
			color = new Color( c );
		}
		
	}
	
	// ========== Constructor
	
	void init(){
		resources = new HashMap<>(); // HashMap<ComodityType,Double>
	}
	
	public SiteType( ){ init(); }
	public SiteType( String s ){ init(); fromString( s ); }
	
}