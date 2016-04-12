/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 * This class prepares the table needed to display the date’s reports; 
 *  it especially prepares and defines the table logic for the report generation..
 * @author Charles;
 * @see DateReport.java,Action_Model.java,ReportTableModel.java
 * @version 1.0 
 */

public class Date_ReportModel  extends AbstractTableModel {
    private Vector totalrows;
    private String snospara;
   // private String sec;
    
    /** Creates a new instance of NameTableModel */
    public Date_ReportModel(String para) {
         this.snospara = para; 
        totalrows = new Vector ( );
    }
    
     String [ ] heading = { "SNOS Name","Message Content","Date Received","Client Name","Address","Phone Number"};
               
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
              pro.dateMessageInfor1(snospara);
              totalrows = pro.getRows();
                // totalrows=mr.getRows();
                
              
        }
        
        
    
    
}
