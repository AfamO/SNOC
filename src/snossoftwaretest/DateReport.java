/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class prepares the GUI needed to display the date’s reports; 
 *  it especially prepares and defines the table logic for the report generation..
 * @author Charles;
 * @see Date_Report.java,Action_Model.java,ReportTableModel.java
 * @version 1.0 
 */
public class DateReport extends JFrame {
    
    
    private JPanel snosReportPane;
    DateReport( String pa){
       //ReportPage();
        snosReportPane = new JPanel(); 
      snosReportPane.setLayout(new BorderLayout());
       Date_Report  b = new Date_Report (pa);
      snosReportPane.add(b);
        JFrame con= new JFrame();
        con.add(snosReportPane);
       con.setSize(950, 600);
        con.setLocationRelativeTo(null);
        con.setVisible(true);
        con.setResizable(false);
 
    }
    
}
