
package BoC.GUI;

import BoC.Engine.Economy.Factory;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;


public class FactoryTableModel extends AbstractTableModel{
	
	boolean canModity = true;

	public final static String[]   columnNames = { "technology", "size", "activity" };
	public final static boolean[]  editable    = { false,  true,    true    };
	//final static class   Object[] dtypes   = { String.class, Double.class, Double.class   }; 
	public ArrayList<Factory> data;

	public void setData( Collection<Factory> data_ ){
		if( data_ != null ){
			data = new ArrayList ( data_ );
			fireTableDataChanged();
			//System.out.println( "FactoryTableModel.setData : "+data );
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
			Factory item = data.get( row );
			switch( col ){
				case 0: return item.technology.name;
				case 1: return item.size;
				case 2: return item.activity;
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
			Factory item = data.get( row );
			switch( col ){
				case 0: item.technology.name = ( String ) value; break;
				case 1: item.size            = ( Double ) value; break;
				case 2: item.activity        = ( Double ) value; break;
			}
			fireTableCellUpdated(row, col);
		}
	}
	
}
