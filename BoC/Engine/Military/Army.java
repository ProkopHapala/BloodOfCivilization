package BoC.Engine.Military;

import BoC.Engine.City;
import BoC.Engine.Economy.ComodityManager;
import BoC.Engine.Economy.ComodityType;
import BoC.utils.Drawable;
import BoC.Engine.GameObject;
import BoC.Engine.Globals;
import BoC.Engine.GraphicsCanvas;
import BoC.Engine.Player;
import BoC.Engine.WorldSite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class Army extends GameObject implements Drawable {
	public Player owner;
    public HashMap<CombatantType,Brigade>        brigades;
	public HashMap<ComodityType,ComodityManager> comodities;
	
	public WorldSite site;
	public double x,y;	// exact position  ?
	public City city;  // can be null if not min city
	
	public WorldSite    move_target;
	
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
	
	public void setTarget( WorldSite target ){
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
	
	@Override
	public String toString(){
		return name+" "+x+" "+y+" "+organized;
	}
	
	@Override
	public void fromString( String s ){
		//System.out.println( s );
		String [] words = s.split("\\s+");
		name          =                      words[0]  ;
		double x_     = Double.parseDouble(  words[1] );
		double y_     = Double.parseDouble(  words[2] );   
		organized     = Double.parseDouble(  words[3] );         
		
		setPos( x_, y_ );
	}
	
	/*
	public void reassingBrigades(){
		for( Brigade b : brigades.values() ){ b.assignToArmy( this );	}
	}
	*/
	
	// ================= Graphics
	
	@Override
	public void paint( GraphicsCanvas canvas ){
		int sz = canvas.tile_size;
		int sx = canvas.map2screen_x( (float)x );
		int sy = canvas.map2screen_y( (float)y );
		Graphics2D g2 = canvas.g2;
		g2.setColor ( Color.RED );
		g2.fillRect  ( sx, sy, 5, 5 );
		g2.drawString( name, sx, sy );
	}
	
	// ========== Constructor
	
	public void init(){
		brigades    = new HashMap<>();
		comodities  = new HashMap<>();
	}
	
	public Army( ){ init(); }
	public Army( String s ){ init(); fromString( s );  }
	public Army( String name_, double x_, double y_ ){
		init();
		name = name_;
		setPos(x_,y_);
	}
	
}