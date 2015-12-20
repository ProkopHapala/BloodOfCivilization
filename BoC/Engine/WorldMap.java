package BoC.Engine;

import BoC.utils.Drawable;
import BoC.utils.*;

import java.util.*;
import java.awt.Graphics2D;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class WorldMap implements TxtStorable, Drawable {
	
    public int block_side_pow, block_side_n, block_side_mask;
    public int block_area_pow, block_area_n, block_area_mask;
        
    public int n_blocks_x, n_blocks_y;
	public int nx,ny;

    private Site [][] sites;

	// ================= Map intexing rutines
	
	int getBlockIndex  ( int x, int y ){ return (( y >> block_side_pow  ) *  n_blocks_y     ) + ( x >> block_side_pow  ); }
	int getInBlockIndex( int x, int y ){ return (( y &  block_side_mask ) << block_side_pow ) + ( x &  block_side_mask ); }
	
	int block_x( int iblock ){ return ( iblock % n_blocks_x ) << block_side_pow; }
	int block_y( int iblock ){ return ( iblock / n_blocks_x ) << block_side_pow; }
	    
    public boolean checkValidPointPositive( int x, int y ){	return ( ( x < nx ) && ( y < ny  ) );	}
	public boolean checkValidPoint         ( int x, int y ){	return ( ( x < nx ) && ( y < ny  ) && ( x >= 0 ) && ( y >= 0 ) ); }
    
	public Site getSite( int x, int y ){
		int iblock = getBlockIndex  ( x, y );
		int jblock = getInBlockIndex( x, y );
		Site [] block =  sites[ iblock ];
		if ( block != null ){ return block[ jblock ]; }else{ return null; }
	}
	
	public boolean setSite( int x, int y, Site site ){
		int iblock = getBlockIndex  ( x, y );
		int jblock = getInBlockIndex( x, y );
		boolean alocated_block = ( sites[ iblock ] == null );
		if ( alocated_block ){ sites[ iblock ] = new Site[ block_area_n ]; }
        sites[ iblock ][ jblock ] = site;
		return alocated_block;
	}
	
	public boolean checkCleanBlock( Site [] block ){
		for( Site site : block ){  
			if( site != null ) return false;
		}
		return true;
	}
	
	public void cleanBlocks( ){
		for( int i=0; i<sites.length; i++ ){  
			if( checkCleanBlock( sites[i] ) ) sites[i] = null;
		}
	}
	
	// ================= Generator
	
	public void GenerateRandom( int seed ){
		Random random    = new Random( seed );
		//Collection<SiteType> typeCol = Globals.siteTypes.values();
		SiteType [] types = new SiteType[ Globals.siteTypes.size() ];
		Globals.siteTypes.values().toArray( types );
		for (int iy=0; iy<ny; iy++ ){
			for (int ix=0; ix<nx; ix++ ){
				int itype = ( random.nextInt() & 0xFFFFFF ) % types.length;
				//System.err.println( " site: "+ix+" "+iy+" "+itype  );
				SiteType type = types[ itype ];
				double height = random.nextDouble();
				Site site = new Site( ix, iy, height, type );
				setSite( ix, iy, site );
				
			}
		}
	}
	
	// ================= IO
	
	@Override
	public void writeToTxt( BufferedWriter writer ) throws IOException {
		writer.write( n_blocks_x +" "+ n_blocks_y+" "+block_side_pow+"\n" );
		for( Site [] block : sites ){
			if( block != null ){ 
				for( Site site  : block ){
					if( site != null ) writer.write( site.toString() + "\n" );
				}
			}	
		}	
	}
	
	@Override
	public void readFromTxt( BufferedReader reader ) throws IOException {
		String line;
		line            = reader.readLine();
		String [] words = line.split( "\\s+" );
		n_blocks_x      = Integer.parseInt( words[0] );
		n_blocks_y      = Integer.parseInt( words[1] );
		block_side_pow  = Integer.parseInt( words[2] );
		while( null != ( line = reader.readLine() )  ){
			//System.err.println( line );
			Site site = new Site( line );
			setSite( site.ix, site.iy, site );
		};
	}
	
	// ================= Graphics
	
	public int paintBlock( GraphicsCanvas canvas, Site [] block ){
		int n_painted = 0;
		if( block != null ){
			for ( Site site : block ){	
				if( site !=null ){
					//System.err.println( site.ix +" "+ site.iy +" "+ canvas.tileInView( site.ix, site.iy ));
					if( canvas.tileInView( site.ix, site.iy ) ){
						site.paint( canvas ); 
						n_painted++;
					}
				}
			}
		}
		return n_painted;
	}
	
	@Override
	public void paint( GraphicsCanvas canvas ) {
		//System.err.println( " Map.paint "  );
		int nt_painted = 0;
		int nb_painted = 0;
		for ( int i=0; i<sites.length; i++ ){
			int ibx = block_x( i );
			int iby = block_y( i );
			//System.err.println( "Map block "+i+" "+ibx+" "+iby );
			if ( canvas.boxInView( ibx, iby, ibx+block_side_n, iby+block_side_n ) ){
				//System.err.println( "Map block "+i+" "+ibx+" "+iby+" " );
				nt_painted += paintBlock( canvas, sites[i] );
				nb_painted++;
			}
		}
		//System.err.println( " Map.paint n_painted = "+nb_painted+" "+nt_painted );
	}
	
	// ================= Constructor
	
	public void evalBlockParams( int block_side_pow_ ){
		block_side_pow  = block_side_pow_;
        block_side_n    = 1 << block_side_pow;
        block_side_mask = block_side_n - 1; 
        block_area_pow  = block_side_pow << 1;
        block_area_n    = 1 << block_area_pow;
        block_area_mask = block_area_n - 1;
		
		//System.err.println( "evalBlockParams: "+block_side_pow+" "+block_side_n+" "+block_area_pow+" "+block_area_n );
    }
	
	public void reallocate( int n_blocks_x_, int n_blocks_y_, int block_side_pow_ ){
		evalBlockParams( block_side_pow_ );
		n_blocks_x = n_blocks_x_; 
		n_blocks_y = n_blocks_y_;
		nx         = n_blocks_x_ << block_side_pow;
		ny         = n_blocks_y_ << block_side_pow;	
		//System.err.println( "reallocate: "+n_blocks_x+" "+n_blocks_y+" "+nx+" "+ny );
		sites      = new Site[ n_blocks_x * n_blocks_y ][];
	}
	
	public WorldMap( int n_blocks_x_, int n_blocks_y_, int block_side_pow_ ){
		reallocate    ( n_blocks_x_, n_blocks_y_, block_side_pow_ );
		//System.err.println( "WorldMap: "+nx+" "+ny+" "+block_side_n );
		//GenerateRandom( 154545 );
	}
	
}