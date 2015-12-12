package BoC.Engine;

import BoC.utils.Drawable;
import BoC.Engine.Economy.Factory;
import BoC.Engine.Economy.Route;
import BoC.Engine.Economy.Technology;
import BoC.Engine.Economy.ComodityType;
import BoC.Engine.Economy.ComodityManager;
import BoC.Engine.Military.Army;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class City extends GameObject implements Drawable {
	public Player owner;
    public Site   site;
    public Set<Route> routes; // HasSet ?
	public HashMap<ComodityType,ComodityManager> comodities;
	public HashMap<Technology,Factory>           factories;
	public Set<Army> Armies;
	
	public double FactorySpace; // ? is this useful
	public double storageSpace; // ? is this useful
	
	// ========= Comodity Management
	
	public double addComodity( ComodityType type, double amount ){
		comodities.get(type).add(amount);
		return amount;
	}
	
	public double removeComodity( ComodityType type, double amount ){
		comodities.get(type).remove(amount);
		return amount;
	}
	
	public void setPos( int ix, int iy ){
		if( site.city == this ) site.city = null;
		site = Globals.worldMap.getSite( ix, iy );
		site.city = this;
		//System.out.println(  (int)x +" "+ (int)y + "|" + site );
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
		int sz = canvas.tile_size;
		int sx = canvas.map2screen_x( site.ix );
		int sy = canvas.map2screen_y( site.iy );
		Graphics2D g2 = canvas.g2;
		g2.setColor( Color.BLACK );
		g2.fillOval( sx, sy, sz, sz );
		g2.drawString( name, sx, sy );
	}
	
	// ========== Constructor
	
	void init(){
		comodities = new HashMap<>(); // HashMap<ComodityType,Double>
		factories = new HashMap<>();
	}
	
	public City( ){ init(); }
    public City( String s ){ init(); fromString( s );	}
	
}