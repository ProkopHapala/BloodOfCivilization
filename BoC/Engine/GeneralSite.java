
package BoC.Engine;

import BoC.utils.Drawable;
import BoC.utils.StringIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Set;

public class GeneralSite implements StringIO,Drawable{
    public int        ix,iy;
	
	@Override
	public String getName(){ return ix+" "+iy; }
	
	@Override
	public String toString(){ return ix+" "+iy; }
	
	@Override
	public void fromString( String s ){	}
	
	@Override
	public void paint( GraphicsCanvas canvas ) {
    }
	
	public GeneralSite(){}; 
	public GeneralSite( String s ){ fromString( s ); }
}
