
package BoC;

//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import javax.swing.JPanel;

import java.awt.*;
//import java.awt.geom.Line2D;
import javax.swing.*;

interface Drawable {
	public void paint( GraphicsCanvas canvas );
}

public class GraphicsCanvas extends JPanel {

	Graphics2D g2;
	
	static public int Width = 1024;
	static public int Height = 768;

	
	// ==== Transfromation between map [tile] and screen [pixel] coordinates
	
	public int tile_size;
	public int tile_size_half;
	
	float      inv_tile_size;
	public int tiles_per_screen_x,tiles_per_screen_y;
	
	int ix_min,ix_max,iy_min,iy_max;
	
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
		if( (sz & 1)==1 ){ sz++; }
		tile_size      = sz;
		tile_size_half = tile_size >> 1;
		inv_tile_size  = 1.0f / tile_size;
		tiles_per_screen_x = (int)( Width  * inv_tile_size );
		tiles_per_screen_y = (int)( Height * inv_tile_size );
		
	}
	
	public float screen2map_x( int ix ){ return ix * inv_tile_size  - ix_min; 	}
	public float screen2map_y( int iy ){ return iy * inv_tile_size  - iy_min; }
	
	
	public Site getSite( int sx, int sy ){
		int ix = (int) screen2map_x( sx );
		int iy = (int) screen2map_y( sy );
		System.out.println( sx+" "+sy+" ->  "+ix +" "+ iy );
		return Globals.worldMap.getSite( ix, iy );
	}
	
	
	public void scroolTo( int ix, int iy ){
		ix_min = ix;            iy_min = iy;
		iy_max = ix_min + tiles_per_screen_x;   iy_max = iy_min + tiles_per_screen_y;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;
		//map.paint(g2);
		//GlobalVars.paint(g2);
	}

	public void paint( Graphics g ) {
		//Create Graphics2D object, cast g as a Graphics2D
		g2 = (Graphics2D) g;
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
