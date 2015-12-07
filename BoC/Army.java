package BoC;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class Army extends GameObject implements Drawable {
	Player owner;
    HashMap<CombatantType,Brigade>        brigades;
	HashMap<ComodityType,ComodityManager> store;
	
	Site site;
	double x,y;	// exact position  ?
	City city;  // can be null if not min city
	
	// posibilities ?
	double organized;
	
	// ================= IO
	
	void setPos( double x_, double y_ ){
		x = x_; y = y_; 
		site = Globals.worldMap.getSite( (int)x, (int)y );
		//System.out.println(  (int)x +" "+ (int)y + "|" + site );
	}
	
	// ================= Graphics
	
	@Override
	public void paint( GraphicsCanvas canvas ){
		int sz = canvas.tile_size;
		Graphics2D g2 = canvas.g2;
		g2.setColor ( Color.RED );
		g2.fillRect  ( site.ix*sz, site.iy*sz, sz, sz );
		g2.drawString( name, site.ix*sz, site.iy*sz );
	}
	
	// ========== Constructor
	
	void init(){
		brigades = new HashMap<>();
		store    = new HashMap<>();
	}
	
	Army( ){ init(); }
	
	Army( String name_, double x_, double y_ ){
		init();
		name = name_;
		setPos(x_,y_);
	}
	
}