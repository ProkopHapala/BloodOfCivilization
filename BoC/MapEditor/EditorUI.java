
package BoC.MapEditor;

import BoC.Engine.Military.Army;
import BoC.Engine.*;

import BoC.utils.*;

import java.awt.Point;
import java.awt.event.KeyEvent;

class EditorUI {
	
	final static int SITE_MODE = 1;
	final static int CITY_MODE = 2;
	final static int ARMY_MODE = 3;
	
	static int mode = 1;
	
	public static boolean recordMouseMove = false; // just for optimization ? 
	public static boolean recordMouseDrag = true; // just for optimization ? 
	
	public static Player PLAYER;
	public static Point  mousePos_pressed  = new Point(0, 0);
	public static Point  mousePos_released = new Point(0, 0);
	/*
	public static Point  mousePos_dragged  = new Point(0, 0);
	public static Point  mousePos_moved    = new Point(0, 0);
	*/
	static int lastDragTileHash = 0;
	static int lastMoveTileHash = 0;
	
	static SiteType selected_siteType = null;
	static Army     selected_army     = null;
	static City     selected_city     = null;
	
	
	static public int scrool_speed = 1;
	
	
	public static void LMB_pressed( ){
		System.out.println( " LMB_pressed " + mousePos_pressed.x +" "+ mousePos_pressed.y );
		switch( mode ){
			case SITE_MODE:
				if( selected_siteType != null ){ changeSiteType(  mousePos_pressed.x, mousePos_pressed.y, selected_siteType ); 	}
				break;
			case CITY_MODE: selectCity( mousePos_pressed.x, mousePos_pressed.y ); break;
			case ARMY_MODE: selectArmy( mousePos_pressed.x, mousePos_pressed.y ); break;
		}
	}
		
	public static void RMB_pressed( ){
		switch( mode ){
			case SITE_MODE:
			case CITY_MODE:
			case ARMY_MODE:
			break;
		}
	}
	
	public static void LMB_released( ){
		switch( mode ){
			case SITE_MODE:
			case CITY_MODE:
			case ARMY_MODE:
			break;
		}
	}
		
	public static void RMB_released( ){
		switch( mode ){
			case SITE_MODE:
			case CITY_MODE: reposCity( mousePos_released.x, mousePos_released.y );	break;
			case ARMY_MODE:	reposArmy( mousePos_released.x, mousePos_released.y );	break;
		}
	}
	
	public static void LMB_dragged( int mx, int my ){
		switch( mode ){
			case SITE_MODE:
				if( selected_siteType != null ){ 
					int tileHash = EditorMain.canvas.tileHash( mx, my );
					if( tileHash != lastDragTileHash ){
						System.out.println( " LMB_pressed " + mousePos_pressed.x +" "+ mousePos_pressed.y+" "+tileHash+" "+lastDragTileHash );
						changeSiteType(  mx, my, selected_siteType ); 
						lastDragTileHash = tileHash;
					}
				}
				break;
			case CITY_MODE:
			case ARMY_MODE:
			break;
		}
	}
		
	public static void RMB_dragged( int mx, int my ){
		switch( mode ){
			case ARMY_MODE:
			case CITY_MODE:
			case SITE_MODE:
		}
	}
	
	public static void LMB_moved( int mx, int my ){
		switch( mode ){
			case SITE_MODE:
			case CITY_MODE:
			case ARMY_MODE:
			break;
		}
	}
		
	public static void RMB_moved( int mx, int my ){
		switch( mode ){
			case ARMY_MODE:
			case CITY_MODE:
			case SITE_MODE:
		}
	}
	
	
	
	// ========== Keyboard
	
	
	public static void keyPressed( int keyCode ){
		switch( keyCode ){
			case KeyEvent.VK_S: 
				System.out.println( " [S]-key: save worldMap " );
				FileSystem.saveToTxt  ( "map.txt", Globals.worldMap );
				break;
			case KeyEvent.VK_L: 
				System.out.println( " [L]-key: load worldMap " );
				FileSystem.loadFromTxt  ( "map.txt", Globals.worldMap );
				break;
			case KeyEvent.VK_C: mode = CITY_MODE; break;
			case KeyEvent.VK_A: mode = ARMY_MODE; break;
			case KeyEvent.VK_X: mode = SITE_MODE; break;
			case KeyEvent.VK_UP:     EditorMain.canvas.scroolBy(  0, 1 ); break;
            case KeyEvent.VK_DOWN:   EditorMain.canvas.scroolBy(  0,-1 ); break;
            case KeyEvent.VK_LEFT:   EditorMain.canvas.scroolBy(  1, 0 ); break;
            case KeyEvent.VK_RIGHT : EditorMain.canvas.scroolBy( -1, 0 ); break;
			case KeyEvent.VK_ADD      : zoom( 2.0f ); break;
			case KeyEvent.VK_SUBTRACT : zoom( 0.5f ); break;
		}
	}
		
	// =========== rutines
	
	static void zoom( float f ){
		EditorMain.canvas.setTileSize( (int)( EditorMain.canvas.tile_size * f ) ); 
		if ( EditorMain.canvas.tile_size < GraphicsCanvas.default_tile_size ){
			scrool_speed = (int) ( GraphicsCanvas.default_tile_size / EditorMain.canvas.tile_size );
		}else{
			scrool_speed = 1;
		}
		System.out.println( scrool_speed+" "+EditorMain.canvas.tile_size  );
	}
	
	static void selectArmy( int mx, int my ){
		selected_army = null;
		Site site = EditorMain.canvas.getSite( mousePos_pressed.x, mousePos_pressed.y );
		System.out.println( " Site " + site );
		for( Army army : Globals.armies.values() ) { 
			if( army.site == site ) {
				selected_army = army;
				System.out.println( " Selected Army: "+selected_army );
				break;
			}
		}
	}
	
	static void reposArmy( int mx, int my ){
		if( selected_army != null ){
			float x = EditorMain.canvas.screen2map_x( mx );
			float y = EditorMain.canvas.screen2map_y( my );
			selected_army.setPos( x, y );
		}
	}
	
	static void selectCity( int mx, int my ){
		selected_city = null;
		Site site = EditorMain.canvas.getSite( mousePos_pressed.x, mousePos_pressed.y );
		System.out.println( " Site " + site );
		for( City city : Globals.cities.values() ) { 
			if( city.site == site ) {
				selected_city = city;
				System.out.println( " Selected city: "+selected_city );
				break;
			}
		}
	}
	
	static void reposCity( int mx, int my ){
		if( selected_city != null ){
			int x = (int) EditorMain.canvas.screen2map_x( mx );
			int y = (int) EditorMain.canvas.screen2map_y( my );
			selected_city.setPos( x, y );
		}
	}
	
	// =========== Operations
	
	static void changeSiteType( int ix, int iy, SiteType siteType ){
		System.out.println( "changeSiteType "+ix+" "+iy+" "+siteType.name );
		Site site = EditorMain.canvas.getSite( ix, iy );
		if( site == null ){
			site = new Site();
			site.ix = ix; site.iy = iy;
			Globals.worldMap.setSite(ix, iy, site);
		}
		site.type = siteType;
	}
	
	
	
		
}
