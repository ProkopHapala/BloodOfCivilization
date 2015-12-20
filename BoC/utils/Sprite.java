
package BoC.utils;

import BoC.Engine.GraphicsCanvas;

import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/*
possibly this will improve performance ?
http://stackoverflow.com/questions/196890/java2d-performance-issues
*/

public class Sprite {
	String         name      = "img/ground_Grass.png";
	BufferedImage  original  = null; 
	BufferedImage  actual    = null; 
	float normalized_width;
	float normalized_height;	
	int tile_size            = GraphicsCanvas.default_tile_size;
		
	public void resize( int w, int h ){
		// see refferences
		// http://stackoverflow.com/questions/196890/java2d-performance-issues
		// https://community.oracle.com/docs/DOC-983611
		// http://stackoverflow.com/questions/543130/java-2d-and-resize
		//int ow = original.getWidth();
		//int oh = original.getHeight();
		GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage new_image = gfx_config.createCompatibleImage( w, h, original.getTransparency() );
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC ); 
		// if downscaled by factor >2 wee should do multistep
		g2d.drawImage( original, 0, 0, w, h, null);
		g2d.dispose();
		actual = new_image;
		//System.err.println( "Sprite.resize "+name );
	}
	
	public void checkCanvas( GraphicsCanvas canvas ){
		if( canvas.tile_size != tile_size ){
			int w = (int)Math.round( canvas.tile_size * normalized_width );
			int h = (int)Math.round( canvas.tile_size * normalized_height );
			resize( w, h );
			tile_size = canvas.tile_size;
		}
	}
	
	public void paint( int ix, int iy, GraphicsCanvas canvas ) {
		checkCanvas( canvas );
		Graphics2D g2 = canvas.g2;	
		g2.drawImage( actual, ix, iy, null );
    }
	
	public Sprite( String name_ ){
		name = name_;
		this.original = FileSystem.loadImage( name );
		normalized_width   = original.getWidth()  /  ( (float) GraphicsCanvas.default_tile_size );
		normalized_height  = original.getHeight() /  ( (float) GraphicsCanvas.default_tile_size );
		resize( original.getWidth(), original.getHeight() );
		//System.err.println( name+" "+original+" "+actual+" "+normalized_width+" "+normalized_height+" "+tile_size );
		//System.err.println( name+" "+normalized_width+" "+normalized_height+" "+tile_size );
	}
	
}
