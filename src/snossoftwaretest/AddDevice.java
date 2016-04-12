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
import java.awt.Color;
import javax.swing.*;
/**
 * Does not really do much.
 * @author Charles;
 * @see AddClient.java,AddDeviceInfo.java
 * @version 1.0 
 */
public class AddDevice extends JFrame{
    
    public AddDevice(){
        this.adduser();
        
    }
    public void adduser(){
        JPanel mainp = new JPanel();
        mainp.setLayout(null);
        
       // mainp.setOpaque(false);
     
        JLabel model = new JLabel( "Name:" );
		model.setBounds( 40, 30, 50, 20 );
                model.setForeground(Color.BLUE);
		mainp.add(model);
                JTextField namef = new JTextField();
		namef.setBounds( 130, 30, 150, 20 );
		mainp.add(namef);
                
                JLabel fonelab = new JLabel( "Phone Number:" );
		fonelab.setBounds( 40, 60, 100, 20 );
                fonelab.setForeground(Color.BLUE);
		mainp.add(fonelab);
                JTextField fonef = new JTextField();
		fonef.setBounds( 130, 60, 150, 20 );
		mainp.add(fonef);
                
                JLabel personlab = new JLabel( "Contact Person:" );
		personlab.setBounds( 40, 90, 100, 20 );
                personlab.setForeground(Color.BLUE);
		mainp.add(personlab);
                JTextField personf = new JTextField();
		personf.setBounds( 130, 90, 150, 20 );
		mainp.add(personf);
                
                
                JLabel locationlab = new JLabel( "Location:" );
		locationlab.setBounds( 40, 120, 100, 20 );
                locationlab.setForeground(Color.BLUE);
		mainp.add(locationlab);
                JTextArea loc= new JTextArea();
               loc.setBounds( 130, 120, 100, 20 );
               loc.setColumns(5);
               loc.setRows(4);
              //loc.setLineWrap(true);
             // loc.setWrapStyleWord(true);
	     mainp.add(new JScrollPane(loc));
            

    

   String content = "Here men from the planet Earth\n"
      + "first set foot upon the Moon,\n" + "July 1969, AD.\n"
      + "We came in peace for all mankind.";
   JTextArea ta = new JTextArea(content, 6, 8);
    ta.setLineWrap(true);
    ta.setWrapStyleWord(true);
    ta.setBounds( 60, 150, 80, 20 );
    mainp.add(new JScrollPane(ta));
                
                JButton connect = new JButton( "Submit" );
		connect.setBounds( 60, 180, 80, 20 );
                connect.setForeground(Color.BLUE);
                mainp.add(connect);
        setTitle("Add Identification Information");
       
        mainp.setLayout(new BorderLayout());
        getContentPane().add(mainp);
    }
    public static void main(String args[]){
        new AddDevice().setVisible(true);
    }
}
