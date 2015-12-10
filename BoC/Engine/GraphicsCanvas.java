
package BoC.Engine;

//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import javax.swing.JPanel;

import BoC.Engine.Economy.Route;
import BoC.Engine.Military.Army;
import java.awt.*;
//import java.awt.geom.Line2D;
import javax.swing.*;

public class GraphicsCanvas extends JPanel {

	public Graphics2D g2;
	
	public static final int default_tile_size = 16;
	
	
	// ==== Transfromation between map [tile] and screen [pixel] coordinates
	
	public int tile_size;
	public int tile_size_half;
	
	public float      inv_tile_size;
	public int tiles_per_screen_x,tiles_per_screen_y;
	
	public int ix_min,ix_max,iy_min,iy_max;
	
	// ==== Settings for visualization of different things
	
	public BasicStroke route_stroke = new BasicStroke(3);
	public Color       route_color  = new Color( 10,10,200 ); 
	

	public GraphicsCanvas() {
		//map = new Map(true);
		Graphics2D  g2;
		setBackground( new Color(43, 29, 15, 255) );
		setDoubleBuffered(true);
		setIgnoreRepaint(true);
	}
	
	
	public void setTileSize( int sz ){
		//if( ( sz & 1)==1 ){ sz++; }
		tile_size          = sz;
		tile_size_half     = tile_size >> 1;
		inv_tile_size      = 1.0f / tile_size;
		tiles_per_screen_x = (int)( getWidth()  * inv_tile_size );
		tiles_per_screen_y = (int)( getHeight() * inv_tile_size );
		System.out.println( tile_size+" "+tile_size_half+" "+inv_tile_size+" "+tiles_per_screen_x+" "+tiles_per_screen_y );
		scroolBy( 0, 0 );
	}
	
	public float screen2map_x( int x    ){ return x * inv_tile_size  + ix_min; }
	public float screen2map_y( int y    ){ return y * inv_tile_size  + iy_min; }
	public int   map2screen_x( float ix ){ return (int)(( ix - ix_min ) * tile_size); }
	public int   map2screen_y( float iy ){ return (int)(( iy - iy_min ) * tile_size); }
	
	
	public int tileHash( int sx, int sy ){
		short ix = (short) screen2map_x( sx );
		short iy = (short) screen2map_y( sy );
		return (ix<<16) ^ iy;
	}
	
	
	public Site getSite( int sx, int sy ){
		int ix = (int) screen2map_x( sx );
		int iy = (int) screen2map_y( sy );
		//System.out.println( sx+" "+sy+" ->  "+ix +" "+ iy );
		return Globals.worldMap.getSite( ix, iy );
	}
	
	public boolean tileInView( int ix, int iy ){
		//System.out.println( ix+" "+iy+"      "+ix_max+" "+ix_min+" "+iy_max+" "+iy_min    );
		return ( ix < ix_max ) && ( ix > (ix_min-1) ) && ( iy < iy_max ) && ( iy > (iy_min-1) ); 
	}
	
	public boolean boxInView( int ix1, int iy1, int ix2, int iy2 ){
		//System.out.println( ix1+" "+ix2+" "+iy1+" "+iy2+"      "+ix_max+" "+ix_min+" "+iy_max+" "+iy_min    );
		return ( ( ix1 < ix_max ) && ( ix2 > (ix_min-1) ) ) && ( ( iy1 < iy_max ) && ( iy2 > (iy_min-1) ) ); 
	}
	
	/*
	public boolean lineInView( int ix1, int iy1, int ix2, int iy2 ){
	}
	*/
	
	public void scroolTo( int ix, int iy ){
		ix_min = ix;            iy_min = iy;
		ix_max = ix_min + tiles_per_screen_x;   iy_max = iy_min + tiles_per_screen_y;
		System.out.println( "scroolTo: "+ix_min+" "+iy_min+" "+ix_max+" "+iy_max );
	}
	
	public void scroolBy( int dix, int diy ){	scroolTo( ix_min+dix, iy_min+diy );	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;
		//map.paint(g2);
		//GlobalVars.paint(g2);
	}

	public void paint( Graphics g ) {
		//Create Graphics2D object, cast g as a Graphics2D
		g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight() );
		/*
		g2.setColor(Color.ORANGE);
		g2.fillRect(0, 0, 150, 150);
		g2.setColor(Color.BLACK);
		//g2d.drawOval(50, 50, 40, 40);
		g2.fillOval(50, 50, 40, 40);
		*/
		Globals.worldMap.paint( this );
		for ( Route route : Globals.routes.values() ){ route.paint(this); }
		for ( Army army   : Globals.armies.values() ){ army .paint(this); }
	}

}
