/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import java.awt.BorderLayout;
import javax.swing.*;
/**
 * This does not really do anything. 
 * @author Charles;
 * @see Snos_ReportModel.java, SnosTypeReport.java
 * @version 1.0
 *
 */
public class Snos_Report extends JFrame{
    private JPanel snosReportPane;
    Snos_Report( String pa){
       //ReportPage();
        snosReportPane = new JPanel(); 
      snosReportPane.setLayout(new BorderLayout());
       Snos_TypeReport  b = new Snos_TypeReport (pa);
      snosReportPane.add(b);
        JFrame con= new JFrame();
        con.add(snosReportPane);
       con.setSize(950, 600);
        con.setLocationRelativeTo(null);
        con.setVisible(true);
        con.setResizable(false);
 
    }
   
    
}
