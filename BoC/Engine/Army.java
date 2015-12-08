package BoC.Engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class Army extends GameObject implements Drawable {
	public Player owner;
    public HashMap<CombatantType,Brigade>        brigades;
	public HashMap<ComodityType,ComodityManager> store;
	
	public Site site;
	public double x,y;	// exact position  ?
	public City city;  // can be null if not min city
	
	public Site    move_target;
	
	// posibilities ?
	public double organized;
	
	// ======== temp vars
	
	//double speed;
	
	// ================= Turn update
	
	public void update( double dt ){
		if( move_target != null ){
			move_to_target( dt );
		}
	}
	
	public void move_to_target( double dt ){
		double speed = getSpeed( );
		double lmax  = speed * dt;
		double dx    = move_target.ix - x;
		double dy    = move_target.iy - y;
		double r2    = dx*dx + dy*dy;
		//System.out.println( x +" "+ y +" "+move_target.ix+" "+move_target.iy+" "+dx +" "+ dy+" "+speed );
		if( ( lmax*lmax ) < r2 ){ // cannot reach in one turn
			double r  = Math.sqrt( r2 );
			double renorm  = lmax / r;
			x += dx * renorm;
			y += dy * renorm;
		}else{
			x = move_target.ix;
			y = move_target.iy;
		}
		check_site();
	}
	
	// ================= Basic rutines
	
	public void target_reached(){
		move_target = null;
	}
	
	public void setTarget( Site target ){
		move_target = target;
	}
	
	public void check_site(){
		site = Globals.worldMap.getSite( (int)x, (int)y );
		if( ( move_target != null ) && ( site == move_target ) ){
			target_reached();
		}
	}
	
	public void setPos( double x_, double y_ ){
		x = x_; y = y_; 
		site = Globals.worldMap.getSite( (int)x, (int)y );
		//System.out.println(  (int)x +" "+ (int)y + "|" + site );
	}
	
	public double getSpeed( ){
		double min_speed = Double.POSITIVE_INFINITY;
		for( Brigade b : brigades.values() ){ 
			double b_speed = b.evalSpeed( site );  
			//System.out.println( b.name +" "+ b_speed );
			min_speed = ( b_speed < min_speed ) ? b_speed : min_speed; 
		}
		return min_speed;
	}
	
	
	// ================= IO
	

	
	// ================= Graphics
	
	@Override
	public void paint( GraphicsCanvas canvas ){
		int sz = canvas.tile_size;
		Graphics2D g2 = canvas.g2;
		g2.setColor ( Color.RED );
		int ix = (int) (x * sz);
		int iy = (int) (y * sz);	
		g2.fillRect  ( ix, iy, 5, 5 );
		g2.drawString( name, ix, iy );
	}
	
	// ========== Constructor
	
	public void init(){
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