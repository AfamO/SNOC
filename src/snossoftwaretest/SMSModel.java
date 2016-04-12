/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import javax.swing.table.*;
import java.util.*;
/**
 * This encapsulates the table model needed to report and display clients SMS details-retrieved from local database- in a tabular form.
 * @author Charles;
 * @see GeneReportTable.java,ReportTableModel.java,Action_Model.java
 * @version 1.0 
 */
public class SMSModel extends AbstractTableModel {
     private Vector totalrows;
    private String dept;
    private String sec;
    private Reconsoft mr;
   
    /** Creates a new instance of NameTableModel */
    public SMSModel () {
        //this.dept = dept1; 
        totalrows = new Vector ( );
    }
    
     String [ ] heading = { "Sender","Message Content","Date Received"};
               
	public String getColumnName (int i ) {
                     return heading [i];
        }

	public int getColumnCount ( ) {
		     return heading.length;
        }

	public int getRowCount ( ) {

		return totalrows.size ( );
	}

	public Object getValueAt (int row, int col) {

		return ((String[ ])totalrows.elementAt (row)) [col];
	}

	public boolean isCellEditable (int row, int col) {
		
		return false;
	}
	
	public void fire () {
            fireTableChanged(null);
	}

	public void setVector ( ) {
		Reconsoft pro = new Reconsoft();
		//get all the rows and store in totolrows
              // pro.querysmsList();
                
               totalrows = pro.getRows();
                // totalrows=mr.getRows();
             
		
			//app.doIt();
                      // vis.display();
                       // app.display();
                       // totalrows=app.getRows();
		//
              
        }
    
}
