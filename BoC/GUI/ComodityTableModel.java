
package BoC.GUI;

import java.util.*;
import BoC.Engine.Economy.ComodityManager;
import javax.swing.table.AbstractTableModel;

public class ComodityTableModel extends AbstractTableModel {
	
		boolean canModity = true;
	
	    public final static String[]   columnNames = { "type", "store", "price" };
		public final static boolean[]  editable    = { false,  true,    true    };
		//final static class   Object[] dtypes   = { String.class, Double.class, Double.class   }; 
		public ArrayList<ComodityManager> data;
		
		public void setData( Collection<ComodityManager> data_ ){
			if( data_ != null ){
				data = new ArrayList ( data_ );
				fireTableDataChanged();
				System.out.println( "ComodityTableModel.setData : "+data );
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
				ComodityManager item = data.get( row );
				switch( col ){
					case 0: return item.type.name;
					case 1: return item.stored;
					case 2: return item.price;
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
				ComodityManager item = data.get( row );
				switch( col ){
					case 0: item.type.name = ( String ) value; break;
					case 1: item.stored    = ( Double ) value; break;
					case 2: item.price     = ( Double ) value; break;
				}
				fireTableCellUpdated(row, col);
			}
		}
        		
}
