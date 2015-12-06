package BoC;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

import BoC.Globals;

public class Site{
    SiteType   type;
    double     height;
    int        ix,iy;
    City       city;
    Set<Route> routes;
	
	HashMap<City,NaturalResource> resources;
	
	// ========== IO
	
	public String toString(){
		return ix+" "+iy+" "+height+" "+type.name;
	}
	
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		ix     = Integer.parseInt( words[0] );
		iy     = Integer.parseInt( words[1] );   
		height = Double.parseDouble( words[2] );         
		type   = Globals.siteTypes.get( words[3]  );  
	}
	
	// ================= Graphics
	
	public void paint( GraphicsCanvas canvas ) {
		Graphics2D g2 = canvas.g2;
		//float zoom = Globals.zoom;
		//int sx     = (int)( ( ix - Globals.x0 )*zoom ); 
		//int sy     = (int)( ( iy - Globals.y0 )*zoom ); 
		//if( ( ix>0 ) && ( ix<Globals.winWidth ) && ( iy>0 ) && ( iy<Globals.winHeight ) ){	
				//int c = 0xFF000000 | ( (   ) << 24 ) | ( (   ) << 24 ) | ( (   ) << 24 );
				///g2.setColor( Color.RED );
				//int c = (int)(255*height);
				//g2.setColor( new Color( c, c, c ) );
				//g2.fillOval( sx, sy, (int)zoom, (int)zoom );
		
		
				if( type.img != null ){
					g2.drawImage( type.img, ix*16, iy*16, null );
				}else{
					g2.setColor( type.color );
					g2.fillRect( ix*16, iy*16, 16, 16 );
				};	
		
		
				if( city != null ){
					city.paint( canvas );
				}
				//System.out.println( sx+" "+sy+"  "+type.img );
		//}
    }
	
	// ========== Constructor
	
	public Site( int ix, int iy, double height, SiteType type ){	this.ix=ix; this.iy=iy; this.height=height; this.type=type;	} 
	public Site( String s ){ fromString( s ); }
}