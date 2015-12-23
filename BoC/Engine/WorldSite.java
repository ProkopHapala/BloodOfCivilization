package BoC.Engine;

import BoC.utils.Drawable;
import BoC.utils.StringIO;
import BoC.Engine.Economy.ComodityType;
import BoC.Engine.Economy.ResourceDeposit;
import BoC.Engine.Economy.Route;
import BoC.Engine.Economy.NaturalResource;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class WorldSite extends GeneralSite {
	
    public SiteType   type;
    public double     height;
    public City       city;
    public Set<Route> routes;
	public HashMap<ComodityType,ResourceDeposit> deposits;
	
	double workforce;
	
	// ========== IO
	
	//@Override
	public String getName(){
		return ix+" "+iy;
	}
	
	@Override
	public String toString(){
		return ix+" "+iy+" "+height+" "+type.name;
	}
	
	@Override
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		ix     = Integer.parseInt( words[0] );
		iy     = Integer.parseInt( words[1] );   
		height = Double.parseDouble( words[2] );         
		type   = Globals.siteTypes.get( words[3]  );  
	}
	
	// resources
	
	/*
	double gatherComodity( ComodityType comodity, double dt ){
		double amount = 0.0d;
		if( comodity == Globals.foot_type ){
			amount += dt * getYield( );
		}
		NaturalResource resource = resources.get( comodity );
		if( resource != null ){
			amount += dt * resource.getYield(  );
		}
		return amount;
	}
	*/
	
	public ResourceDeposit addNewDeposit( ResourceDeposit deposit_new ){
		if( deposits == null ){ deposits = new HashMap<>(); }
		ResourceDeposit deposit_old = deposits.get( deposit_new.type );
		if( deposit_old == null ){
			deposits.put( deposit_new.type, deposit_new );
		}
		return deposit_old;
	}
	
	public String caption_deposist( ){
		String s = "";
		for ( ResourceDeposit deposit : deposits.values() ){
			s += deposit.type.name+";";
		}
		return s;
	}
	
	
	// ================= Graphics
	
	@Override
	public void paint( GraphicsCanvas canvas ) {
		Graphics2D g2 = canvas.g2;
		int sz = canvas.tile_size;		
		//System.err.println( "site.paint "+ix+" "+iy+" " );
		int sx = canvas.map2screen_x( ix );
		int sy = canvas.map2screen_y( iy );
		if( type.sprite != null ){
			//g2.drawImage( type.img, ix*sz, iy*sz, null );
			type.sprite.paint( sx, sy, canvas);
		}else{
			g2.setColor( type.color );
			g2.fillRect( sx, sy, sz, sz );
		};	
		if( deposits != null ){
			g2.setColor( Color.BLACK );
			g2.fillOval( sx, sy, sz, sz );
			g2.drawString( caption_deposist( ), sx, sy );
		};
		
		if( city != null ){	city.paint( canvas );	}
    }
	
	// ========== Constructor
	
	public WorldSite(){};
	public WorldSite( int ix, int iy, double height, SiteType type ){	this.ix=ix; this.iy=iy; this.height=height; this.type=type;	} 
	public WorldSite( String s ){ fromString( s ); }
	
}