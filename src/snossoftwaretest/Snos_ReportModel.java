
package snossoftwaretest;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
/**
 * This encapsulates the table model needed to report and 
 * display clients  personal and SMS details-retrieved from local database- in a tabular form. 
 * @author Charles;
 * @see Snos_ReportModel.java, SnosTypeReport.java
 * @version 1.0
 *
 */


public class Snos_ReportModel  extends AbstractTableModel {
     private Vector totalrows;
    private String snospara;
   // private String sec;
    
    /** Creates a new instance of NameTableModel */
    public Snos_ReportModel(String para) {
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
              pro.querySNOSType(snospara);
                
              totalrows = pro.getRows();
                // totalrows=mr.getRows();
                
              
        }
        
        
    
    
}
