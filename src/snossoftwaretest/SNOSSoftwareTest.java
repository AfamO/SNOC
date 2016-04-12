
package snossoftwaretest;

import javax.swing.*;
/**
 * This is the entry-point of the application with main method
 * and the method that instantiates SMS/alerts class 
 * as well as pre-start up methods/functions
 * @author AFAM;
 * @see SmsMain.java,ReadMessageTimer.java,ReadMessages1.java
 * @version 1.0 
 */

public class SNOSSoftwareTest{
public static ReadMessages1 rm;
private static JTabbedPane tab;
private static JMenu smsbar;
private static SmsMain sm;
private static JFrame jm;
public static int countTimer=0;
private static ReadMessageTimer jpan;

 private static Reconsoft rs=new Reconsoft();
 private static   UserInforGet home_bg;   
    /*
     * This main method first of all customizes the app-if this is the firs time and it has not been customized for a particular city-and 
     * Secondly searches or checks to ensure that rxtx dll(needed to read sms since SMSLib API is being used) is found in this system's window path
     * and finally searches and scans for the available serial communication ports and gsm Modem.
     */
     public static void main(String[] args) {
         
         
         home_bg=rs.QueryHome_Name_Logo();
         
        if(home_bg.getNoc_name()==null||home_bg.getNoc_city()==null||home_bg.getLogo()==null)
       {
           setup_page bis = new setup_page();
           bis.setLocationRelativeTo(null);
           bis.setSize(500, 400);
           bis.setVisible(true);
           bis.setResizable(false);
           bis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       
      }else {
         if(SmsMain.checkforDll("rxtxserial"))
        {
             
             SmsMain.setIsTimerStopped(true);
            JOptionPane.showMessageDialog(null, " One of the configuration parameters for sending and receiving sms has been tampered with.\nPlease do check to ensure that rxtx.dll is not missing in the library path.\nThank you.","EMPTY CONFIGURATION PARAMETERS-DLL",0); 
             
         }
         else
        {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            
            public void run() {
                  sm = new SmsMain();
        sm.setSize(1090, 720);
        //sm.setSize(1140, 720);
        sm.setLocationRelativeTo(null);
        sm.setResizable(false);
        //sm.setVisible(true);
        sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            jpan = new  ReadMessageTimer();
              countTimer++;  
             System.out.println("\nThe CountTimer is: " +countTimer+"\n");
       
                
        
            }
        });
         try
        {   
           
           rm=new ReadMessages1();
           rm.GetMessages();
           
           
        }
        catch(Exception ex)
        {
            //javax.swing.JOptionPane.showMessageDialog(null, "Are you sure that your \nmodem is conected at the right port?");
            ex.printStackTrace();
        }
 
        
       }
     }
         
   }
    /*
     * This just holds the SmsMain class object
     */
    public static SmsMain getObj()
    {
        return sm;
    }
    /*
     * This just holds the ReadMessageTimer class object.
     */
    public static ReadMessageTimer getTimerObj()
    {
        return jpan;
    }
    
}

 
 