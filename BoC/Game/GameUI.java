
package BoC.Game;

import BoC.Engine.Military.Army;
import BoC.Engine.*;

import BoC.utils.*;

import java.awt.Point;
import java.awt.event.KeyEvent;

class GameUI {
	
	public static boolean recordMouseMove = false;
	public static boolean recordMouseDrag = true; // just for optimization ? 
	
	public static Player PLAYER;
	public static Point  mousePos_pressed  = new Point(0, 0);
	public static Point  mousePos_released = new Point(0, 0);
	public static Point  mousePos_moved    = new Point(0, 0);
	
	final static int SITE_MODE = 1;
	final static int CITY_MODE = 2;
	final static int ARMY_MODE = 3;
	
	
	static int mode = ARMY_MODE;
	
	static Army selected_army = null;
	static City selected_city = null;
	
	static public int scrool_speed = 1;
	
	
	public static void LMB_pressed( ){
		System.err.println( " LMB_pressed " + mousePos_pressed.x +" "+ mousePos_pressed.y );
		switch( mode ){
			case SITE_MODE: break;
			case CITY_MODE: selectCity( mousePos_pressed.x, mousePos_pressed.y ); GameMain.cityView.setCity(selected_city); GameMain.cityView.frame.setVisible(true); break;
			case ARMY_MODE: selectArmy( mousePos_pressed.x, mousePos_pressed.y ); GameMain.armyView.setArmy(selected_army); GameMain.armyView.frame.setVisible(true); break;
		}
	}
	
	public static void LMB_released( ){
		//System.err.println( " RMB_released " + mousePos_released.x +" "+ mousePos_released.y );
		switch( mode ){
			case SITE_MODE:  break;
			case CITY_MODE:  break;
			case ARMY_MODE:  break;
		}
	}
	
	public static void RMB_pressed( ){
		System.err.println( " RMB_pressed " + mousePos_pressed.x +" "+ mousePos_pressed.y );
		switch( mode ){
			case SITE_MODE:  break;
			case CITY_MODE:  break;
			case ARMY_MODE:
				if( selected_army != null ){
					Site site = GameMain.canvas.getSite( mousePos_pressed.x, mousePos_pressed.y );
					selected_army.setTarget( site );
					System.err.println( " Army: "+selected_army+" move to "+selected_army.move_target );
				}
				break;
		}
	}
		
	public static void RMB_released( ){
		//System.err.println( " RMB_released " + mousePos_released.x +" "+ mousePos_released.y );
		switch( mode ){
			case SITE_MODE: break;
			case CITY_MODE: break;
			case ARMY_MODE: break;
		}
	}
	
	
	public static void LMB_dragged( int mx, int my ){
		switch( mode ){
			case SITE_MODE: break;
			case CITY_MODE: break;
			case ARMY_MODE: break;
		}
	}
		
	public static void RMB_dragged( int mx, int my ){
		switch( mode ){
			case SITE_MODE: break;
			case CITY_MODE: break;
			case ARMY_MODE: break;
		}
	}
	
	public static void LMB_moved( int mx, int my ){
		switch( mode ){
			case SITE_MODE: break;
			case CITY_MODE: break;
			case ARMY_MODE: break;
		}
	}
		
	public static void RMB_moved( int mx, int my ){
		switch( mode ){
			case SITE_MODE: break;
			case CITY_MODE: break;
			case ARMY_MODE: break;
		}
	}
	
	
	// ========== Keyboard
	
	public static void keyPressed( int keyCode ){
		switch( keyCode ){
			case KeyEvent.VK_C: mode = CITY_MODE; System.err.println( "CITY_MODE" ); break;
			case KeyEvent.VK_A: mode = ARMY_MODE; System.err.println( "ARMY_MODE" ); break;
			case KeyEvent.VK_X: mode = SITE_MODE; System.err.println( "SITE_MODE" ); break;
			case KeyEvent.VK_UP       : GameMain.canvas.scroolBy(  0, scrool_speed ); break;
            case KeyEvent.VK_DOWN     : GameMain.canvas.scroolBy(  0,-scrool_speed ); break;
            case KeyEvent.VK_LEFT     : GameMain.canvas.scroolBy(  scrool_speed, 0 ); break;
            case KeyEvent.VK_RIGHT    : GameMain.canvas.scroolBy( -scrool_speed, 0 ); break;
			case KeyEvent.VK_ADD      : zoom( 2.0f ); break;
			case KeyEvent.VK_SUBTRACT : zoom( 0.5f ); break;
		}
	}
	
	// ========== Per Frame Update
	
	static void update(){
		// FIXME: TODO: Generating the table each frame could be very performance intensive !!! Find better solution  
		if( ( selected_army != null ) && ( GameMain.armyView.frame.isVisible() ) ){ GameMain.armyView.setArmy(selected_army); }
	}
	
	
	// =========== Operations
	
	static void zoom( float f ){
		GameMain.canvas.setTileSize( (int)( GameMain.canvas.tile_size * f ) ); 
		if ( GameMain.canvas.tile_size < GraphicsCanvas.default_tile_size ){
			scrool_speed = (int) ( GraphicsCanvas.default_tile_size / GameMain.canvas.tile_size );
		}else{
			scrool_speed = 1;
		}
		System.err.println( scrool_speed+" "+GameMain.canvas.tile_size  );
	}
	
	static void selectCity( int mx, int my ){
		selected_city = null;
		Site site = GameMain.canvas.getSite( mousePos_pressed.x, mousePos_pressed.y );
		System.err.println( " Site " + site );
		for( City city : Globals.cities.values() ) { 
			if( city.site == site ) {
				selected_city = city;
				System.err.println( " Selected city: "+selected_city );
				break;
			}
		}
	}
	
	static void selectArmy( int mx, int my ){
		selected_army = null;
		Site site = GameMain.canvas.getSite( mousePos_pressed.x, mousePos_pressed.y );
		System.err.println( " Site " + site );
		for( Army army : Globals.armies.values() ) { 
			if( army.site == site ) {
				selected_army = army;
				System.err.println( " Selected Army: "+selected_army );
				break;
			}
		}
	}
	
	// =========== rutines
		
}
