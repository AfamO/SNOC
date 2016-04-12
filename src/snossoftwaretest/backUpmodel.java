/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 * This encapsulates the table model 
 * needed to report and display clients 
 * SMS details backed up at the database. 
 * @author Charles;
 * @see BackUpModel.java,ActionModel.java,SMSModel.java,ReportTableModel.java
 * @version 1.0 
 */


public class backUpmodel extends AbstractTableModel {
    private Vector totalrows;
    private String dept;
    private String sec;
    private Reconsoft mr;
  
    private boolean DEBUG = true;
    /** Creates a new instance of NameTableModel */
    public backUpmodel () {
        //this.dept = dept1; 
        totalrows = new Vector ( );
    }
    
     String [ ] heading = { "Sender","Message Content","Date Received","Message Status"};
               
	public String getColumnName (int i ) {
                     return heading [i];
        }

	public int getColumnCount ( ) {
		     return heading.length;
        }

	public int getRowCount ( ) {

		return totalrows.size ( );
	}

    @Override
	public Object getValueAt (int row, int col) {

		return ((String[ ])totalrows.elementAt (row)) [col];
	}

    @Override
	public boolean isCellEditable (int row, int col) {
		
		return false;
	}
	
	public void fire () {
            fireTableChanged(null);
	}

	public void setVector ( ) {
		Reconsoft pro = new Reconsoft();
		//get all the rows and store in totolrows
              pro.querysmsList();
                
              totalrows = pro.getRows();
                // totalrows=mr.getRows();
                
              
        }
        
        
        
        public void setVectorElemnt () {
		Reconsoft pro = new Reconsoft();
		//get all the rows and store in totolrows
             pro.querysmsList();
                
              totalrows = pro.getRows();
                // totalrows=mr.getRows();
                
              
        }
        
    @Override
        public void setValueAt(Object value, int row, int col) {
      if (DEBUG) {
        System.out.println("Setting value at " + row + "," + col + " to " + value
            + " (an instance of " + value.getClass() + ")");
       // Vector n = new Vector();
       totalrows.insertElementAt(value, col);
      }
      //qtbl.setVector();
       fireTableRowsUpdated(row, col);
       
     }
    
}
