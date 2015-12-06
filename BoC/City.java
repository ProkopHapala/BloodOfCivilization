package BoC;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class City extends GameObject implements Drawable {
	Player owner;
    Site   site;
    Set<Route> routes; // HasSet ?
	HashMap<ComodityType,ComodityManager> store;
	HashMap<Technology,Factory> factories;
	Set<Army> Armies;
	
	double FactorySpace; // ? is this useful
	double storageSpace; // ? is this useful
	
	// ========= Comodity Management
	
	public double addComodity( ComodityType type, double amount ){
		store.get(type).add(amount);
		return amount;
	}
	
	public double removeComodity( ComodityType type, double amount ){
		store.get(type).remove(amount);
		return amount;
	}
	
	// ========== IO
	
	@Override
	public String toString(){
		return name+" "+site.ix+" "+site.iy+" "+FactorySpace+" "+storageSpace;
	}
	
	@Override
	public void fromString( String s ){
		//System.out.println( s );
		String [] words = s.split("\\s+");
		name          =                      words[0]  ;
		int ix        = Integer.parseInt(    words[1] );
		int iy        = Integer.parseInt(    words[2] );   
		FactorySpace  = Double.parseDouble(  words[3] );         
		storageSpace  = Double.parseDouble(  words[4] );  
		
		site   = Globals.worldMap.getSite( ix, iy );
		site.city = this;
	}
	
	// ================= Graphics
	
	@Override
	public void paint( GraphicsCanvas canvas ){
		Graphics2D g2 = canvas.g2;
		g2.setColor( Color.BLACK );
		g2.fillOval( site.ix*16, site.iy*16, 16, 16 );
		g2.drawString( name, site.ix*16, site.iy*16 );
	}
	
	// ========== Constructor
	
	City( ){ }
    City( String s ){ fromString( s );	}
	
}