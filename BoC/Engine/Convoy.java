package BoC.Engine;
        
public class Convoy extends GameObject{
	Route    route;
	boolean  forward;
	double   time_loc;
	
	int      site_i;  // site on road
	double   site_dist;  // distance to next site
	
	
	public boolean update( double time ){
		double t_left = route.moveConvoy( this, time - time_loc );
		time_loc = time;
		if( t_left > 0 ){
			reached_destination();
			time_loc = time - t_left;
			return true;
		}else{
			time_loc = time;
			return false;
		}
	}
	
	public void reached_destination( ){
	
	}
	
}