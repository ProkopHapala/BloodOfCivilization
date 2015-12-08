
package BoC;

import java.awt.Point;

class UI {
	
	public static boolean recordMouseMove = false;
	
	public static Player PLAYER;
	public static Point  mousePos_pressed  = new Point(0, 0);
	public static Point  mousePos_released = new Point(0, 0);
	public static Point  mousePos_moved    = new Point(0, 0);
	
	final static int ARMY_MODE = 1;
	final static int CITY_MODE = 2;
	final static int SITE_MODE = 3;
	
	
	static int mode = 1;
	
	static Army selected_army = null;
	static City selected_city = null;
	
	public static void LMB_pressed( ){
		System.out.println( " LMB_pressed " + mousePos_pressed.x +" "+ mousePos_pressed.y );
		switch( mode ){
			case ARMY_MODE:
				selected_army = null;
				Site site = Main.canvas.getSite( mousePos_pressed.x, mousePos_pressed.y );
				System.out.println( " Site " + site );
				for( Army army : Globals.armies.values() ) { 
					if( army.site == site ) {
							selected_army = army;
							System.out.println( " Selected Army: "+selected_army );
							break;
					}
				};
				break;
		}
	}
	
	public static void LMB_released( ){
		//System.out.println( " RMB_released " + mousePos_released.x +" "+ mousePos_released.y );
		switch( mode ){
			case ARMY_MODE:
			case CITY_MODE:
			case SITE_MODE:
		}
	}
	
	public static void RMB_pressed( ){
		System.out.println( " RMB_pressed " + mousePos_pressed.x +" "+ mousePos_pressed.y );
		switch( mode ){
			case ARMY_MODE:
				if( selected_army != null ){
					Site site = Main.canvas.getSite( mousePos_pressed.x, mousePos_pressed.y );
					selected_army.setTarget( site );
					System.out.println( " Army: "+selected_army+" move to "+selected_army.move_target );
				}
				break;
			case CITY_MODE:
			case SITE_MODE:
		}
	}
		
	public static void RMB_released( ){
		//System.out.println( " RMB_released " + mousePos_released.x +" "+ mousePos_released.y );
		switch( mode ){
			case ARMY_MODE:
			case CITY_MODE:
			case SITE_MODE:
		}
	}
	
	// =========== rutines
		
}
