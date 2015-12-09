
package BoC.MapEditor;

import BoC.Engine.Military.Army;
import BoC.Engine.*;

import java.awt.Point;

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
	
	public static void LMB_pressed( ){
		System.out.println( " LMB_pressed " + mousePos_pressed.x +" "+ mousePos_pressed.y );
		switch( mode ){
			case SITE_MODE:
				if( selected_siteType != null ){ changeSiteType(  mousePos_pressed.x, mousePos_pressed.y, selected_siteType ); 	}
				break;
			case CITY_MODE:
			case ARMY_MODE:
			break;
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
			case ARMY_MODE:
			case CITY_MODE:
			case SITE_MODE:
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
	
	
	
	
	
	// =========== rutines
	
	
	
	
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
