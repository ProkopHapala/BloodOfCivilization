
package BoC.GUI;

import java.util.*;
import BoC.Engine.Military.Brigade;
import javax.swing.table.AbstractTableModel;

public class BrigadeTableModel extends AbstractTableModel {
	
		boolean canModity = true;
	
	    public final static String[]   columnNames = { "type", "store", "price" };
		public final static boolean[]  editable    = { false,  true,    true    };
		//final static class   Object[] dtypes   = { String.class, Double.class, Double.class   }; 
		public ArrayList<Brigade> data;
		
		public void setData( Collection<Brigade> data_ ){
			if( data_ != null ){
				data = new ArrayList ( data_ );
				fireTableDataChanged();
				//System.out.println( "BrigadeTableModel.setData : "+data );
			}
		}
		
		@Override
        public int getColumnCount() { return columnNames.length; }

		@Override
        public int getRowCount(    ) { 
			if( data != null ){ return data.size(); }else { return 0; }        
		}

		@Override
        public String getColumnName(int col) { return columnNames[col]; }

		@Override
        public Object getValueAt( int row, int col ) { 
			if( data != null ){
				Brigade item = data.get( row );
				switch( col ){
					case 0: return item.type.name;
					case 1: return item.n_tot;
					case 2: return item.n_capable;
					case 3: return item.n_alive;
				}
			}
			return null;
		}

		@Override
        public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }

		@Override
        public boolean isCellEditable(int row, int col) { return editable[ col ]; }

		@Override
        public void setValueAt( Object value, int row, int col ) {
			if( data != null ){
				Brigade item = data.get( row );
				switch( col ){
					case 0: item.type.name = ( String ) value; break;
					case 1: item.n_tot      = ( Integer ) value; break;
					case 2: item.n_capable  = ( Integer ) value; break;
					case 3: item.n_alive    = ( Integer ) value; break;
				}
				fireTableCellUpdated(row, col);
			}
		}
        		
}
