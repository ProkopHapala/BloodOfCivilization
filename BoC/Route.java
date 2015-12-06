package BoC;

import java.awt.Graphics2D;
import java.util.*;

public class Route{
	String name;
    RouteType    type;
    City         city_from, city_to;
    Site []      sites; 
    Set<Convoy>  convoys;
	
	// ========= static 
	
	public static boolean switchedCityOrder( String name_a, String name_b ){
		return name_a.compareTo( name_b ) > 0;
	}
	
	public static String nameConcat( String name_a, String name_b ){ 
		return name_b +"-"+ name_a;
	} 
			
	public static String nameFromCityNames( String name_a, String name_b ){  
		boolean switched = switchedCityOrder( name_a, name_b );
		if( switched ){	return nameConcat(name_b,name_a); }else{ return nameConcat(name_b,name_a); }
	}
	
	// ========= Convoy Movement
	
    public double getDistance( Site s1, Site s2 ){
        // later there will be probably more.
        int dx = s1.ix - s2.ix;
        int dy = s1.iy - s2.iy;
        return Math.sqrt( dx*dx + dy*dy );
    }
	
    public double getSpeed( Convoy convoy, Site s1, Site s2 ){
		// later there will be probably more.
		return 1;
    }

    public double moveConvoy( Convoy convoy, double delta_t ){
        int di     = convoy.forward ? 1 : -1;
        int site_j = convoy.site_i + di;
        Site s1 = sites[ convoy.site_i ];
        Site s2 = sites[        site_j ];
        double dist_left = getDistance(         s1, s2 ) - convoy.site_dist; 
        double speed     = getSpeed   ( convoy, s1, s2 ); 
        double dist_done = speed * delta_t; 
        if( dist_done < dist_left ){   // next point not reached
            convoy.site_dist += dist_done;
            return 0;
        }else{                         // next point passed
            double t_left = delta_t - ( dist_left / speed );
            if ( ( site_j == 0 ) || ( site_j == (sites.length-1) ) ){
                    return t_left;
            }else{
                convoy.site_dist = 0;
                convoy.site_i    = site_j;
                return moveConvoy( convoy, t_left );
            }
        }
    }
	
	// ========== IO
	
	public String toString(){
		return city_from.name+" "+city_to.name;  //ix+" "+iy+" "+height+" "+type.name;
	}
	
	public void fromString( String s ){
		String [] words = s.split("\\s+");
		city_from = Globals.cities.get( words[0] );
		city_to   = Globals.cities.get( words[1] );
	}
	
	// ================= Graphics
	
	public void paint( GraphicsCanvas canvas ) {
		Graphics2D g2 = canvas.g2;
		//g2.setColor( type.color );
		//g2.fillRect( ix*16, iy*16, 16, 16 );
		
		//g2.drawLine( city_from.site.ix*16, city_from.site.iy*16, city_to.site.ix*16, city_to.site.iy*16, );
		g2.drawLine( city_from.site.ix*16, city_from.site.iy*16, city_to.site.ix*16, city_to.site.iy*16 );
    }
	
	// ========= Constructor
	
	public Route( City city_a, City city_b ){
		boolean switched = switchedCityOrder( city_a.name, city_b.name  );
		if( switched ){  city_from = city_b; city_to=city_a; }else{ city_from = city_a; city_to=city_b; }
		name = nameConcat( city_from.name, city_to.name );
	} 
	public Route( String s ){ fromString( s ); }
	
}