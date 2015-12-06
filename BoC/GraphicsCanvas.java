
package BoC;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class GraphicsCanvas extends JPanel {

	
	static public int winWidth = 1024;
	static public int winHeight = 768;
	
	public static float zoom = 10.0f;
	public static float x0   =  0.0f;
	public static float y0   =  0.0f;
	
	int nx;
	int ny;
	int ix_min,ix_max,iy_min,iy_max;
	
	Graphics2D g2;
	
	public GraphicsCanvas() {
		//map = new Map(true);
		Graphics2D  g2;
		setBackground( new Color(43, 29, 15, 255) );
		setDoubleBuffered(true);
		setIgnoreRepaint(true);
	}

	public void scroolTo( int ix, int iy ){
		ix_min = ix;            iy_min = iy;
		iy_max = ix_min + nx;   iy_max = iy_min + ny;
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
		Globals.map.paint( this );
	}

}
