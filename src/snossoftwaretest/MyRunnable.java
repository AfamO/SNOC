/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Vector;

/**
 *This defines  most of the thread object's methods needed to carry out different 
 * operations concurrently like scanning modem, detecting SMS alerts, 
 * sending remote data to remote database as well as sending email to clients.
 * @author Afam
 * @see ReadMessagesl.java, SnosFrame.java
 * @version 1.0 
 */
public class MyRunnable extends Thread 
{
    int count=0;
    public String type;//this defines the type of thread or action you want the thread to perform at any point in time
    private volatile boolean IsEmailSent=false;
    private static Properties prop;
    private  static String message_sms_id;
    static JLabel current_signal_label;//the current signal label being acted upon at the moment.
    static volatile int ActionNumber=0;
    static  int labelCounter=0;
    static boolean Isemailactionsent=false;
    static boolean Isphonecallmade=false;
    static private Reconsoft bis = new Reconsoft();
    JLabel signallabel;
    String email_sender;
    String sender;
    SmsMain sm;
    Vector values=new Vector();
    SnosFrame sf=SmsMain.snosFrameObject;
    public MyRunnable(String type)
    {
        Isemailactionsent=false;
        Isphonecallmade=false;
       // Issmssent=false;
       //sf =new SnosFrame();
        //get the list of SNOS numbers labels that help you track differnt SNSOS number labels.
        SnosFrame.labeList= SmsMain.getLabeList();
        this.type=type;
        //load the property files that contain configuration parameters.
        prop=AppConfig.LoadFromConfigFiles();
        //get the SNOS number of the sms sender from the sms alert class.
        sender=ReadMessages1.sender;
        //get the email address that will be used to forward email alerts from this app.
        email_sender=prop.getProperty("sender_address");
        System.out.println("My Sender email address is::"+email_sender+"\nThank you.");
        //validate the email_sender alert.
        if(email_sender.equals(""))
        {
            JOptionPane.showMessageDialog(null, " One of the configuration parameters for sending email \nand receiving/sending sms has been tampered with.\nPlease do check the configuration file of this application.\nThank you.","WRONG CONFIGURATION PARAMETERS",0);
            System.exit(0);
        }
    }
    /*
     * This is a run method that defines the various Logic of the Thread class.
     * It implements thread and conditions that scan gsm Modem, 
     * send sms and email alerts and remote host alert IMMEDIATELY on sms alert arrival.
     */
    public  void run(){
         try{
             //This is set while 'true' so to ensure that the thread always executes uninterruptedly.
             while (true)
             {
                 //pause the thread for a while
                 Thread.sleep(300);
                 //get the module
                 int diff=count%50;
                 //Did the app called the scan modem thread. I mean does it wnat to scan the Modem to find availabe Ports
                 if(type.equals("scanmodem"))
                 {
                     //was this Modem diconnected at any point in time-while the app is running?
                     if(!ReadMessages1.IsPortConnected(ReadMessages1.portInuse))
                     {
                         //Then issue the appropriate message.
                         javax.swing.JOptionPane.showMessageDialog(null, "YOUR MODEM HAS JUST BEEN REMOVED!\nThere are no SERIAL COMMUNICATION/MODEM ports installed/available in your system\nOr REMOVE from your system the device(s) using up your serial ports.\nAlso make sure that your MODEM is PROPERLY CONNECTED to your system.\nThank you.","NO AVAILABLE MODEM/SERIAL PORTS ",0);
                     }
                     else
                     {
                     }
                 }
                 //Has a new sms inbound message arrive?
                 if(ReadMessages1.getIsNewInbound())
                 {
                     //Is the new thread started equals to alert?
                     if(type.equals("alert"))
                     {
                         //get the SNOS label list containing the List of SNOS number labels
                         SnosFrame.labeList= SmsMain.getLabeList();
                         //is the sender gsm number unknown to this app?
                         if(ReadMessages1.getSnosPosition()==-1)
                         {
                             //Do nothing
                         }
                         else
                         {
                             //otherwise get the SNOS label using the snos number position
                             signallabel=(JLabel)SnosFrame.labeList.get(ReadMessages1.getSnosPosition());
                             //set the color to red-indicating danger and security breach.
                             signallabel.setForeground(Color.RED);
                             //Make a noise to alert somebofy at the SNOC
                             Toolkit.getDefaultToolkit().beep();
                             //Write the 'intruder' on the label test field.
                             signallabel.setText("INTRUDER");
                             //finally display the Label
                             signallabel.setVisible(true);
                             //add and activate the Action Listener method so that the personat the SNOC can perform some actions.
                             sf.runMyAction(signallabel);
                             //assign the signal label to be the current one
                             current_signal_label=signallabel;
                             //Has the SNOC watchman done something for the security breach?
                             if(sf.currentIsActionTaken)
                             {
                                 //Wait for a while
                                 synchronized(this)
                                 {
                                     this.wait();
                                 }
                             }
                             else
                             {
                                 //Animate a little:constantly display and hide the hide the intruder label as it it is 
                                 //brinking whenever the random diff varibale is 0 and when it is not 0 after finding the modulus-with 
                                 //50 as a denominator.
                                 if(diff==0)
                                 {
                                     Toolkit.getDefaultToolkit().beep();
                                     current_signal_label.setVisible(false);
                                 }
                                 else
                                 {
                                     Toolkit.getDefaultToolkit().beep();
                                     current_signal_label.setVisible(true);
                                 }
                             }
                             //}
                         }
                     }
                     //Did the app start a thread that will forward received alert to a remote web host?
                     else if(type.equals("remotesms"))
                     {
                         //first of all add the alert to local database-for record keeping
                         bis.AddSmsInforTOlOCALDB(ReadMessages1.sender, ReadMessages1.text,ReadMessages1.dat , "unsent");
                         //get the alert and its parameters
                         String date=ReadMessages1.dat;
                         String get_sms_id=bis.queryReceivedSMS_Getid(date);
                         message_sms_id=get_sms_id;
                         //Create the sms info in the action table as part of action to be taken
                         bis.AddAction_To_RecievedSMS(ReadMessages1.sender,ReadMessages1.text,"NO ACTION",message_sms_id,"NO Text",date,"NO Date");
                         //Finally add to remote sms database
                         
                         if(bis.AddSmsInfor(ReadMessages1.sender, ReadMessages1.text, ReadMessages1.dat, ReadMessages1.messageStatus))
                         {
                          //notify and update-in local sms table of the local db- that the remote sms has been sent
                          //JOptionPane.showMessageDialog(null,"The ID FROM remotesmsrunnable is:"+message_sms_id+ "at time:\n"+new java.util.Date().toLocaleString());
                          bis.UpdateSMSStatus( "sent",  message_sms_id);
                          System.out.println("REMOTE insertion SUCCESSFULL!");
                          System.out.println("REMOTE insertion SUCCESSFULL!!");
                          System.out.println("REMOTE insertion SUCCESSFULL!!!");
                          //makes the boolean to be true
                          ReadMessages1.IsRemoteSent=true;
                          //wait for a while
                          synchronized(this)
                          {
                              this.wait();
                          }
                         }
                         else
                         {
                             //then render this to be false
                             ReadMessages1.IsRemoteSent=false;
                         }
                     }
                     //Did the app start email thread-I mean does it want to send sms alert to the client email address?
                     else if(type.equals("email"))
                     {
                    while( IsEmailSent==false)
                    {
                        //get the client's email address
                        String recipient=bis.RetrieveItem("snos_client", "snos_type", ReadMessages1.sender, "Client_email");
                        System.out.println("The Email Sender is::"+email_sender+" while GSM Sender="+sender+" and Recipient is::"+recipient);
                        //attempt to send an email to him/her
                        if(new SnosSendEmail().SNOSemailSendToOne(email_sender,ReadMessages1. pass,ReadMessages1.getSMS_Message_Sender(),ReadMessages1.text +"  "+"\n PLEASE DO NOT REPLY THIS EMAIL!",recipient))
                        {
                            //since it went successfull
                            ReadMessages1.IsEmailSent=true;
                            IsEmailSent=true;
                            synchronized(this)
                            {
                                this.wait();
                            }
                        }
                        else
                        {
                            //since it failed
                            ReadMessages1.IsEmailSent=false;
                            
                            synchronized(this)
                            {
                                this.wait();
                            }
                        }
                         }
                     }

                
          //  }
       //if the count is less than 24 don nothing
       if(count<24)
       {
           
       }
       // increment the counter to be used in the modulus diff calculation for intruder's label apparent brinking/or animation
       count++;
     }
             }
		   }
	        catch (InterruptedException e)
		   {
                       System.out.println("The Interruption is from: "+e.getMessage()+"ln Cause is:");
                       e.printStackTrace();
		   }
}
      
        
       public static void SetSMS_Message_id(String mess)
     {
         
         message_sms_id=mess;
     }
    public static String  getSMS_Message_id()
     {
       return message_sms_id;  
     }
   
}
