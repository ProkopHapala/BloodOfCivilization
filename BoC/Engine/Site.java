package BoC.Engine;

import BoC.utils.Drawable;
import BoC.utils.StringIO;
import BoC.Engine.Economy.NaturalResource;
import BoC.Engine.Economy.Route;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class Site implements StringIO, Drawable {
    public SiteType   type;
    public double     height;
    public int        ix,iy;
    public City       city;
    public Set<Route> routes;
	public HashMap<City,NaturalResource> resources;
	
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
	
	// ================= Graphics
	
	@Override
	public void paint( GraphicsCanvas canvas ) {
		Graphics2D g2 = canvas.g2;
		int sz = canvas.tile_size;		
		//System.out.println( "site.paint "+ix+" "+iy+" " );
		int sx = canvas.map2screen_x( ix );
		int sy = canvas.map2screen_y( iy );
		if( type.sprite != null ){
			//g2.drawImage( type.img, ix*sz, iy*sz, null );
			type.sprite.paint( sx, sy, canvas);
		}else{
			g2.setColor( type.color );
			g2.fillRect( sx, sy, sz, sz );
		};	
		if( city != null ){	city.paint( canvas );	}
    }
	
	// ========== Constructor
	
	public Site(){};
	public Site( int ix, int iy, double height, SiteType type ){	this.ix=ix; this.iy=iy; this.height=height; this.type=type;	} 
	public Site( String s ){ fromString( s ); }
}