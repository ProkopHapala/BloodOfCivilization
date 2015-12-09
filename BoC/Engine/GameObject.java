
package BoC.Engine;

import BoC.utils.StringIO;
import BoC.utils.Named;

public class GameObject implements Named, StringIO {
	public String name;
	
	// ========== IO
	
	@Override
	public String getName(){ return name; };
	
	@Override
	public String toString(){ return name; }
	
	@Override
	public void fromString( String s ){ name = s;	}
	
	// ========== Constructor
	
	public GameObject ( ){	} 
	//public GameObject ( String s ){ name=name; }
}
