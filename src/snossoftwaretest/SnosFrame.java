/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This initializes and prepares the GUI swing components needed to 
 * display, manage and maintain Client’s SMS alerts status (SAFE or INTRUDER). 
 * It also defines and implements the needed methods and/or functions.
 */
//  @param This initializes and prepares the GUI swing components needed to display, manage and maintain Client’s SMS alerts status (SAFE or INTRUDER). It also defines and implements the needed methods and/or functions.
package snossoftwaretest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 //import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

/**
 * This class initializes and prepares the GUI swing components needed to 
 * display, manage and maintain Client’s SMS alerts status (SAFE or INTRUDER). 
 * It also defines and implements the needed methods and/or functions.
 * @param snos1-This is a JLabel object that represents and holds the first SNOS client name per frame;
 * @param snos2-This is a JLabel object that represents and holds the 2nd SNOS client name per frame;
 * @param snos3-This is a JLabel object that represents and holds the 3rd SNOS client name per frame;
 * @author AFAM;
 * @see SmsMain.java, ReadmessageTimer.java, ReadMessages1.java
 * @version 1.0
 *
 * 
 */

public class SnosFrame extends javax.swing.JInternalFrame  {
private SmsMain smsMain_Object;//This is simply the object of this class.
private static int snosCounter=0;//represent the first SNOS label number per frame
private static int snosCounter2=0;//represent the 2nd SNOS label number per or attached to a frame
private static int snosCounter3=0;//represent the 3rd SNOS label number per or attached to a frame 
private JMenuItem phone_call_Action,sms_send_Action,email_send_Action,print_Action;//These menu items respectively hold actions taken for phone calls, sms, email and printing.
public static List labeList=new ArrayList<JLabel>();//This holds the signal labels  for either 'intruder' or 'safe' security. status
private Reconsoft customer = new Reconsoft();// This is simply a database instance
private String [] phone_contacts,mail_contacts;//These obviously holds info for clients contact's phones and email addresses.
private int confirm_Dialog_Response;//This holds the response from confirm dialog question at each point in time.
private int countclient=0;//This is just a counter for SNOS 1 label attached to a snos frame
private int countclient2=0;//This is just a counter for SNOS 2 label attached to a snos frame
private int countclient3=0;//This is just a counter for SNOS 3 label attached to a snos frame
private int email_List_lent,phone_List_lent;//These hold the length or size of email and phone lists.
private JPanel printpan;//This is a panel used for printing
public volatile boolean currentIsActionTaken=false;//This just checks wether an action has been taken or not.
public static String admin_email_password=""; //This is the password of the administrator's system emmail address 
public static String admin_email_address="";//This is the admin email address used to forward email from this app. 
private static Properties config_file_prop;//This holds the configuration files properties parameters
 private String sms_id= ReadMessages1.getSMS_Message_id();//This holds the id of an sms retrieved from the database
 ArrayList<String []> ClientsNameList=customer.queryRegisteredSNOSCustomers();// This holds the list of client names
 int client_List_lent=ClientsNameList.size();//get the lent or size of the client list
 /**
 * Creates new form SnosFrame
 */
 public SnosFrame() 
 {
     //set up the initial GUI components
     initComponents();
     //There are two types of label: 'safe' and 'intruder'
     //set the safelabel true-since 'safe' is the default security status
     safeLabel.setVisible(true);
     //set the background color to be blue-this is also the default color
     safeLabel.setForeground(Color.BLUE);
     safeLabel.setText("SAFE");//set the text with 'SAFE'
     intruderLabel.setForeground(Color.BLUE);//set this likewise blue
     intruderLabel.setText("SAFE");//set it to be 'SAFE' since it is the default status.To be changed to 'RED' when an alert i s received.
     intruderLabel2.setForeground(Color.BLUE);//set this to be the case as well.
     intruderLabel2.setText("SAFE");//second intruder label
     //The SNOS number counter and it gets the number of registered SNOS numbers
     snosCounter=SmsMain.getSnosNumber();
     //get the client count
     countclient=SmsMain.clientcount();
     //NB: There are 3 counters that represent the number of SNOS labels shown per frame:
     //snosCounter, snosCounter2 and snosCounter3
     //Has the first counter reached the number of registered clients?
     if(snosCounter==SmsMain.getInitialSnosNumber())
       {
           System.out.println("snosCounter1 is:"+snosCounter);
           snosCounter2=1;
           //Hide both the 2nd and 3rd  SNOS client name labels
           snos2.setVisible(false);
           snos3.setVisible(false);
           //equally hide the corresponding status/intruder labels
           intruderLabel.setVisible(false);
           intruderLabel2.setVisible(false);
           //
           countclient2=1;
           //MainFrame.setSnosNumber(0);
           //snosCounter2=-1;
       }
    
       else
       {
           //then increment this counter and assign to next logical-2nd- SNOS counter since there are still clients name to display
           snosCounter2=snosCounter+1;
           countclient2=countclient+1;
        System.out.println("snosCounter1 is:"+snosCounter);
       }
       //Has the second counter reached the number of registered clients?
       if(snosCounter2==SmsMain.getInitialSnosNumber())
       {
           System.out.println("snosCounter2 is :"+snosCounter2);
           //attach the number to the label
          snos2.setText("SNOS "+snosCounter2);
          
           snosCounter3=1;
           //Hide the third label because the last client name and SNOS number has been displayed.
           snos3.setVisible(false);
           snosCounter3=0;
           //hide-also- the corresponding intruderlabel
           intruderLabel2.setVisible(false);
           countclient3=1;
       }
       else
       {
            System.out.println("snosCounter2 is :"+snosCounter2);
            //then increment this counter and assign to the next logical-3rd- SNOS counter since there are still clients name to display
            snosCounter3=snosCounter2+1;
            
            countclient3=countclient2+1;
       }
       //Has the 3rd counter reached the number of registered clients?
       if(snosCounter3==SmsMain.getInitialSnosNumber())
       {
           System.out.println("snosCounter3 :"+snosCounter3);
          
           snos3.setText("SNOS "+snosCounter3);
           
            SmsMain.setSnosNumber(0);
       }
       else
       {
           SmsMain.setSnosNumber(snosCounter3);
           SmsMain.setcountNumber( countclient3);
           System.out.println("snosCounter3 :"+snosCounter3);
       }
       // The names should be in this format
        //if(alist.isEmpty())
      //  {
         // snos1.setText("EMMANUEL.AFAM ");
         // snos2.setText("EKUWEM.CHARLES ");
         // snos3.setText("OKONKWO.PRECIOUS ");  
       // }else
       // {
         //for(int i=0;i<len;i++)
      // {
      
      if(countclient >=client_List_lent|| countclient2>=client_List_lent || countclient3>=client_List_lent)
      {
          
      }else
      {
          //format the name in the form of  SURNAME.FIRSTNAME
          //Examples: EMMANUEL.AFAM
     String [] me=ClientsNameList.get(countclient);
     String [] me2=ClientsNameList.get(countclient2);
     String [] me3=ClientsNameList.get(countclient3); 
        snos1.setText(me[0]+"."+me[1]);
       snos2.setText(me2[0]+"."+me2[1]);
       snos3.setText(me3[0]+"."+me3[1]);  
      }
    
        // if(i>len)
        // {
          //snos1.setText("EMMANUEL.AFAM ");
          //snos2.setText("EKUWEM.CHARLES ");
          //snos3.setText("OKONKWO.PRECIOUS ");     
         //}else
         //{
          
        // }
        // System.out.println(me[0]+" "+me[1]);  
        //}  
     //   }
        
      
       
       //Set their names to be 'SNOS'counter . E.g: SNOS1,SNOS2, SNOS3..... 
       snos1.setName("SNOS"+snosCounter);
       snos2.setName("SNOS"+snosCounter2);
       snos3.setName("SNOS"+snosCounter3);
       //get their corrosponding labels
       JLabel signallabel=  safeLabel;
       JLabel signallabel2= intruderLabel;
       JLabel signallabel3= intruderLabel2;
       
       //Set this label in the HOME Form class to be used later
       SmsMain.setSignalLabel(signallabel);
       SmsMain.setSignalLabel(signallabel2);
       SmsMain.setSignalLabel(signallabel3);
       
       
    }
         
    /*
     * Create a constructor that will be used in HOME JFrame to generate internal frame-using this class
     */     
    public SnosFrame(SmsMain mf)
    {
       this();
       this.smsMain_Object=mf;
       
       //startProgressBar();
       
    }
    
    /**
     *This method adds, sets up and configures different action taken activities(like phone calls, sms sending, email sending and printing)
     * for this software. These activities are activated and displayed once there is a security breach.
     * It can be seen as the software respond to this security breach.
     * @param signallabel1
     */
    public void runMyAction( JLabel signallabel1)
    {
        //Has the Modem scanning completed?
        if (ReadMessages1.getIsMessageRed())
        {
            SNOSSoftwareTest.getObj().setVisible(true);
        }
        //Is there a new Inbound or alert message?
        if(ReadMessages1.getIsNewInbound())
            {
                //then get the Label List containg the Lists of all generated SNOS number Labels.
                labeList= SmsMain.getLabeList();
               final  JLabel signallabel;
               //Make the parameter to be the current activated signal label
               signallabel=signallabel1;
               
                   
                     //set the activated signal label to have the SNOS number of the sms sender
                     signallabel.setName(ReadMessages1.getSMS_Message_Sender());
                     //add the following action Listener to it.
                     signallabel.addMouseListener(new MouseAdapter()
                      {
                          
                          /*
                           * Whenever a mouse is taken to this security-breached signal label, do the following:
                           */
                          @Override
                         public void mouseEntered(MouseEvent e) 
                         {
                             //Show a pop up menu with the Lists of possible Actions to perform
                             final  JPopupMenu m2 = new JPopupMenu();
       
                            m2.add(new JMenuItem(signallabel.getName()+"'s ACTION LIST:"));
                            m2.addSeparator();
                            //Add Phone call ability
                            phone_call_Action= new JMenuItem("Phone Call");
                            phone_call_Action.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                            //get the attached snos number name to be used to query database.
                            String snos_para =signallabel.getName();
                            sms_id= customer.RetrieveItem("sms_in", "re_time", ReadMessages1.dat, "id");
                            ArrayList<String []> fonelist=customer.queryListCustomersPhoneContacts(snos_para);
                            if(fonelist.isEmpty())
                            {
                                JOptionPane.showMessageDialog(null,"Customer's Phone Contacts is not available "+snos_para, null,2);
                            }
                            else
                            {
                                phone_List_lent=fonelist.size();
                                StringBuilder sb = new StringBuilder(64);
                                //build the html string builder for the needed message
                                sb.append("<html><table>");
                                sb.append("<tr><td>Please Let the following Numbers Know About the Security Bridge</td></tr>");
                                sb.append("<tr><td>Contact Persons</td><td>Mobile Numbers "+"</td></tr>");
                                //loop and attach the phone numbers that the SNOC man on duty needs to make the needed calls
                                for(int i=0;i<phone_List_lent;i++)
                                {
                                         phone_contacts=fonelist.get(i);
                                         sb.append("<tr>");
                                         sb.append("<td style='color:blue'>").append(phone_contacts[0]).append("</td>");
                                         sb.append("<td style='color:blue'>").append(phone_contacts[1]).append("</td>");
                                         sb.append("</tr>");
                                }
                                sb.append("</table></html>");
                                //confirm that this is the number you want to call
                                confirm_Dialog_Response = JOptionPane.showConfirmDialog(null, sb, "Confirm "+snos_para, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (confirm_Dialog_Response == JOptionPane.OK_OPTION) 
                                {
                                    String action_datee=new java.util.Date().toString();
                                    //Do you want to carry out another action like sending sms, email, printing, etc?
                                    int resp=JOptionPane.showConfirmDialog(null,"DO You Want To Perform Another Action ?", "Confirm ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                    if(resp==JOptionPane.YES_OPTION)
                                    {
                                        //update the action table in the db
                                        customer.UpdateActionTaken("Phone Call","A Call has been put to your Contact Persons",action_datee,sms_id);
                                    }else if(resp==JOptionPane.NO_OPTION)
                                    {
                                        customer.UpdateActionTaken("Phone Call","A Call has been put to your Contact Persons",action_datee,sms_id);
                                        JOptionPane.showMessageDialog(null," PHONE CALL ACTION TAKEN COMPLETED SUCCESSFULLY", null,2);                                      
                                       // }
                                    }
                                    //reset the INTRUDER LABEL to be 'SAFE' and 'BLUE' from 'RED' since an action or respond has been carried out.
                                    signallabel.setText("SAFE");
                                    signallabel.setForeground(Color.BLUE);
                                    currentIsActionTaken=true;

       } 
           else if (confirm_Dialog_Response == JOptionPane.CANCEL_OPTION) 
       {
   JOptionPane.showMessageDialog(null," You Have Cancelled Phone Call Contact Box ?\n PHONE CALL ACTION NOT TAKEN", null,2);
    
       } else if (confirm_Dialog_Response == JOptionPane.CLOSED_OPTION) 
       {
  //JOptionPane.showMessageDialog(null,"Are Sure You Want to Close the Phone Call Contact Box ?", null,2);
       }  
       
        }}
        });
            m2.add(phone_call_Action);
            m2.addSeparator();
            // Does the Secuirty man on duty wants to carry out a Send E-mail action/respond?
            email_send_Action=new JMenuItem("Send E-Mail");
            email_send_Action.addActionListener(new ActionListener(){
                @Override
        public void actionPerformed(ActionEvent e){
                    //get the sms id
           sms_id= customer.RetrieveItem("sms_in", "re_time", ReadMessages1.dat, "id");
           //load the config parameters to get the admin email address and password
            config_file_prop=AppConfig.LoadFromConfigFiles();         
          admin_email_address=config_file_prop.getProperty("sender_address");
         admin_email_password=config_file_prop.getProperty("sender_password");
         if( admin_email_address==null || admin_email_password==null)
         {
              SmsMain.setIsTimerStopped(true);
            JOptionPane.showMessageDialog(null, " One of the configuration parameters for sending email \nand receiving/sending sms has been tampered with.\nPlease do check the configuration file of this application.\nThank you.","WRONG CONFIGURATION PARAMETERS",0); 
            
            System.exit(0);
         }
         if( admin_email_address.equals("") || admin_email_password.equals("")){
            SmsMain.setIsTimerStopped(true);
            JOptionPane.showMessageDialog(null, " One of the configuration parameters for sending email \nand receiving/sending sms has been tampered with.\nPlease do check the configuration file of this application.\nThank you.","WRONG CONFIGURATION PARAMETERS",0); 
            
            System.exit(0);
            
        }
        //get the SNOS number to be used to qwery db for the client's contacts email addresses         
        String snos_para =signallabel.getName();
        ArrayList<String []> Maillist=customer.queryListCustomersE_Mail_Contacts(snos_para);
        
         if(Maillist.isEmpty())
        {
            //no contacts exists then
        JOptionPane.showMessageDialog(null,"Customer's E-mail Contacts is not available", null,2);
        }else
        {
          email_List_lent=Maillist.size();
             //build the appropriate message to display
              StringBuilder sb = new StringBuilder(64);
              sb.append("<html><table>");
              sb.append("<tr><td>E-Mail has been Forwarded to the Following</td></tr>");
              sb.append("<tr><td>CONTACT PERSONS</td><td>E-MAIL</td></tr>");
              for(int i=0;i<email_List_lent;i++)
              {
                  //loop over the contacts email
                  mail_contacts=Maillist.get(i);
                 sb.append("<tr>");     
                 sb.append("<td style='color:blue'>").append(mail_contacts[0]).append("</td>");
                 sb.append("<td style='color:blue'>").append(mail_contacts[1]).append("</td>");
                 sb.append("</tr>");
                        }
                sb.append("</table></html>");

         
         
       confirm_Dialog_Response = JOptionPane.showConfirmDialog(null, sb, "Confirm "+snos_para, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
           
          if (confirm_Dialog_Response == JOptionPane.OK_OPTION) 
       {
           
          String action_type= email_send_Action.getText();
          //String sms_id= ReadMessages1.getSMS_Message_id();
          String action_datee=new java.util.Date().toString();
          
        int resp=JOptionPane.showConfirmDialog(null,"DO You Want To Perform Another Action ?", "Confirm ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
         if(resp==JOptionPane.YES_OPTION)
         {
             // E-mail Sending method  begin
              for(int i=0;i<email_List_lent;i++)
           {
             mail_contacts=Maillist.get(i);
             new SnosSendEmail().SNOSemailSendToOne(admin_email_address, admin_email_password,"SECURITY BRIDGE ALERT",mail_contacts[2]+"  "+"\n PLEASE DO NOT REPLY THIS EMAIL!", mail_contacts[1]);
             }// E-mail sending method ends
              //update the action table.
               customer.UpdateActionTaken("E-mail Sent","E-Mail Has Been Sent To You Contact Persons",action_datee,sms_id);
              
              JOptionPane.showMessageDialog(null," E-MAIL ACTION TAKEN COMPLETED SUCCESSFULLY", null,2);
             
         }else if(resp==JOptionPane.NO_OPTION)
         {
             
       // E-mail Sending method  begin
              for(int i=0;i<email_List_lent;i++)
           {
             mail_contacts=Maillist.get(i);
             new SnosSendEmail().SNOSemailSendToOne(admin_email_address, admin_email_password,"SECURITY BRIDGE ALERT",mail_contacts[2]+"  "+"\n PLEASE DO NOT REPLY THIS EMAIL!", mail_contacts[1]);
           }
               // E-mail sending method ends
              
         customer.UpdateActionTaken("E-mail Sent","E-Mail Has Been Sent To Your Contact Persons",action_datee,sms_id);
           
         JOptionPane.showMessageDialog(null," E-MAIL ACTION TAKEN COMPLETED SUCCESSFULLY", null,2);
          
         //signallabel.setText("SAFE");
        // signallabel.setForeground(Color.BLUE);   
         }
       } else if (confirm_Dialog_Response == JOptionPane.CANCEL_OPTION) 
       {
        JOptionPane.showMessageDialog(null," You Have Cancelled E-mail Contact Box ?\n E-MAIL ACTION NOT TAKEN", null,2);
    
       } else if (confirm_Dialog_Response == JOptionPane.CLOSED_OPTION) 
       {
 //JOptionPane.showMessageDialog(null,"Are Sure You Want to Close the Phone Call Contact Box ?", null,2);
       }   
    
        }
       
       
       }
         });
            m2.add(email_send_Action);
            m2.addSeparator();
            /*
             * Set up the SMS send Action/response as well.
             * And repeat the similiar approach as above
             */
            sms_send_Action=new JMenuItem("Send SMS");
            sms_send_Action.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
            sms_id= customer.RetrieveItem("sms_in", "re_time", ReadMessages1.dat, "id");
            
            
            
           String snos_para =signallabel.getName();
         
         ArrayList<String []> fonelist=customer.queryListCustomersPhoneContacts(snos_para);
                     
      if(fonelist.isEmpty())
        {
 JOptionPane.showMessageDialog(null,"Customer's sms Contacts is not available "+snos_para, null,2);
        }else
        {
             int len2=fonelist.size();
             
             
  
    for(int i=0;i<len2;i++)
           {
             phone_contacts=fonelist.get(i);
             String gsm=phone_contacts[1];
             String add=phone_contacts[2];
              String text=phone_contacts[3];
             if(SNOSSoftwareTest.rm.SendMessages(gsm, text, add))
                                        {
                                            
                                         StringBuilder sb = new StringBuilder(64);
    sb.append("<html><table>");
    sb.append("<tr><td> SMS ALERT has been Forwarded to the Following</td></tr>");
  
    sb.append("<tr><td>Contact Persons</td><td>Mobile Numbers "+"</td></tr>");
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append(phone_contacts[0]).append("</td>");
    sb.append("<td style='color:blue'>").append(phone_contacts[1]).append("</td>");
    sb.append("</tr>");
           // }
    sb.append("</table></html>");

         
  confirm_Dialog_Response = JOptionPane.showConfirmDialog(null, sb, "Confirm "+snos_para, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
           
             if (confirm_Dialog_Response == JOptionPane.OK_OPTION) 
       {
           
          String action_type= email_send_Action.getText();
          //String sms_id= ReadMessages1.getSMS_Message_id();
          String action_datee=new java.util.Date().toString();
          
        int resp=JOptionPane.showConfirmDialog(null,"DO You Want To Perform Another Action ?", "Confirm ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
         if(resp==JOptionPane.YES_OPTION)
         {
        customer.UpdateActionTaken("SMS Sent","SMS Has Been Sent To You Contact Persons",action_datee,sms_id);
             
         }else if(resp==JOptionPane.NO_OPTION)
         {
             
         customer.UpdateActionTaken("SMS Sent","SMS Has Been Sent To You Contact Persons",action_datee,sms_id);
          // PLEASE CALL SMS SENSDING METHOD
        JOptionPane.showMessageDialog(null," SMS ACTION TAKEN COMPLETED SUCCESSFULLY", null,2);
         //signallabel.setText("SAFE");
         //signallabel.setForeground(Color.BLUE);   
         }
       } else if (confirm_Dialog_Response == JOptionPane.CANCEL_OPTION) 
       {
        JOptionPane.showMessageDialog(null," You Have Cancelled SMS Contact Box ?\n SMS ACTION NOT TAKEN", null,2);
    
       } else if (confirm_Dialog_Response == JOptionPane.CLOSED_OPTION) 
       {
 //JOptionPane.showMessageDialog(null,"Are Sure You Want to Close the Phone Call Contact Box ?", null,2);
       }
      
              }
              else
                                 {
                                     System.out.println("SMS/Message could not be sent to "+phone_contacts+" of "+ReadMessages1.sender);
                                     javax.swing.JOptionPane.showMessageDialog(null, "SMS  couldn't be sent to :"+ReadMessages1.sender+"'s contact \nThus action couldn't be taken for the moment.\nMAKE SURE THAT THEIR IS ENOUGH CREDIT IN YOUR GSM MODEM OR RECHARGE THE MODEM SIM\nYou can also try again later on\nThank you ");
                                    
                                 }
            
         
        }
       
        
        }}
         });
            m2.add(sms_send_Action);
             m2.addSeparator();
             /*
              * Set up the Print Details Action/Reponse and repeat the similiar procedure as above.
              */
             print_Action=new JMenuItem("Print Details");
            print_Action.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         //get the snos number to be used to access the database   
         String snos_para =signallabel.getName();
         
         
         String cos_print[] =customer.queryListCustomers_printable_personnal(snos_para);
         if(cos_print.length==0){
             JOptionPane.showMessageDialog(null,"Customer's Personal Info for printing is not available "+snos_para, null,2);
         }
         else
         {
         //build the needed message/information formatting
              StringBuilder sb = new StringBuilder(64);
    sb.append("<html><table>");
    sb.append("<tr><td style='font-size: 12px;font-weight: bold;margin-bottom:5px;margin-top: 5px; text-align:center'>").append("CUSTOMER'S SECURITY ALERT DETAILS</td></tr>");
    
    
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("CUSTOMER'S NAME: ").append(cos_print[0]+" "+cos_print[1]+" "+cos_print[2]).append("</td>");
    sb.append("</tr>");
         
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("MOBILE NUMBER: ").append(cos_print[3]).append("</td>");
    sb.append("</tr>");
    
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("ADDRESS: ").append(cos_print[4]).append("</td>");
    sb.append("</tr>");
    
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("PROPERTY: ").append(cos_print[5]).append("</td>");
    sb.append("</tr>");
    
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("STATE:  ").append(cos_print[6]).append("</td>");
    sb.append("<td style='color:blue'>").append("LGA:  ").append(cos_print[7]).append("</td>");
    sb.append("</tr>");
    
    
     sb.append("<tr><td style='font-size: 14px;font-weight: bold;margin-bottom:5px;margin-top: 5px; text-align:center'>").append("CUSTOMER'S CONTACT PERSON'S INFORMATION</td></tr>"); 
    
     sb.append("<tr><td> <table cellpadding='0' cellspacing='0' align='center'  >"
             + "<tr>");
     
        sb.append("<th width=150 scope=col> Contact Persons</th>");
        sb.append("<th width=100 scope=col>Mobile Number</th>");
        sb.append("<th width=350 scope=col>ADDRESS</th>");
        sb.append("</tr>");
     
      sb.append(" <tr><td height='5'>&nbsp;&nbsp;</td></tr>");
     
     ArrayList<String []> fonelist=customer.queryListCustomersPhoneContacts(snos_para);
   
        int len4=fonelist.size();
        //loop for the customer's  contacts's gsm numbers
      for(int i=0;i<len4;i++)
           {
              
             phone_contacts=fonelist.get(i);
             
    sb.append("<tr>");     
    sb.append("<td style='color:blue; text-align:center'>").append(phone_contacts[0]).append("</td>");
    sb.append("<td style='color:blue; text-align:center'>").append(phone_contacts[1]).append("</td>");
    sb.append("<td style='color:blue; text-align:center'>").append(phone_contacts[2]).append("</td>");
    sb.append("</tr>");
            }
    
     sb.append("</table></td></tr>");
     
     
     
  sb.append("<tr><td style='font-size: 14px;font-weight: bold;margin-bottom:5px;margin-top: 5px; text-align:center'>").append("SECURITY BRIDGE INFORMATION</td></tr>"); 
 
 
 
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("SENSOR MESSAGE RECEIVED:  ").append(ReadMessages1.getSMS_Message_TEXT()).append("</td>");
    sb.append("</tr>");
    
    
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("SENSOR:  ").append("DOOR CONTACT").append("</td>");
    sb.append("</tr>");
    
     String act_datee=new java.util.Date().toString();
    sb.append("<tr>");     
    sb.append("<td style='color:blue;text-align: right'>").append("Date: ").append(act_datee).append("</td>");
    sb.append("</tr>");
    
    sb.append("</table></html>");
    
    printpan = new JPanel();
    
    
     JLabel printL = new JLabel(sb.toString());
     
    printpan.add(printL);
     //Set up the Jframf for printing purposes.
         
    JFrame printf = new JFrame("CUSTOMER'S SECURITY ALERT DETAILS");
    printf.setLayout(new FlowLayout());
    //printf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    printf.add(printpan);
    printf.pack();
    printf.setLocationRelativeTo(null);
    printf.setVisible(true);
    PrintClientInfor.printComponent(printpan);
         
        }
        }
         });
            m2.add(print_Action);
            m2.pack();
                    Point pos = new Point();
                    // get the preferred size of the menu...
                    Dimension size = m2.getPreferredSize();
                    // Adjust the x position so that the left side of the popup
                    // appears at the center of  the component
                    pos.x = (signallabel.getWidth() / 2);
                    // Adjust the y position so that the y postion (top corner)
                    // is positioned so that the bottom of the popup
                    // appears in the center
                    pos.y = (signallabel.getHeight() / 2) - size.height;
                    m2.show(signallabel, pos.x, pos.y);
                            
                             
                             
                             
                      }
                     }); 
                     
                     
                 
                     
                
            }
           
                   
	        }  
   
     /*
      * Get the List of Customer's registered sensors from the database
      */
      public void PopCustomerSensor()
      {
          JPopupMenu m2 = new JPopupMenu();
          String sensorp=snos1.getName();
          ArrayList<String []> alist2=customer.queryRegisteredSENSORCustomers(sensorp);
          if(alist2.isEmpty())
          {
              m2.add(new JMenuItem("NO ACTIVE SENSOR "+sensorp));            
          }
          else
          {
              //then build and add them to a JMenu Item object.
              int len2=alist2.size();
              m2.add(new JMenuItem("ACTIVE SENSORS "+ sensorp));
              m2.addSeparator();            
              for(int i=0;i<len2;i++)
              {
                  String [] item=alist2.get(i);
                  phone_call_Action= new JMenuItem(item[0]);
                  m2.add(phone_call_Action);
              }
          }
          m2.pack();
          Point pos = new Point();
          // get the preferred size of the menu...
          Dimension size = m2.getPreferredSize();
          // Adjust the x position so that the left side of the popup
          // appears at the center of  the component
          pos.x = (snos1.getWidth() / 2);
          // Adjust the y position so that the y postion (top corner)
          // is positioned so that the bottom of the popup
          // appears in the center
          pos.y = (snos1.getHeight() / 2) - size.height;
          m2.show(snos1, pos.x, pos.y);
      }
      /*
       * Almost the same as IMMEDIATE above method
       */
      public void PopCustomerSensor2(){
         JPopupMenu m2 = new JPopupMenu();
         String sensorp=snos2.getName();
        ArrayList<String []> alist2=customer.queryRegisteredSENSORCustomers(sensorp);
                      
           
      if(alist2.isEmpty())
        {
         m2.add(new JMenuItem("NO ACTIVE SENSOR "+sensorp));
        // m2.addSeparator();            
        }else
        {
             int len2=alist2.size();
           m2.add(new JMenuItem("ACTIVE SENSORS "+sensorp));
           m2.setBackground(Color.WHITE);
           m2.setForeground(Color.GREEN);
           m2.addSeparator();            
                       
           for(int i=0;i<len2;i++)
         {
         String [] item=alist2.get(i);
           phone_call_Action= new JMenuItem(item[0]);
            m2.add(phone_call_Action);
            //m2.addSeparator();
         }
          // m2.addSeparator();
        }
            
            m2.pack();
                    Point pos = new Point();
                    // get the preferred size of the menu...
                    Dimension size = m2.getPreferredSize();
                    // Adjust the x position so that the left side of the popup
                    // appears at the center of  the component
                    pos.x = (snos2.getWidth() / 2);
                    // Adjust the y position so that the y postion (top corner)
                    // is positioned so that the bottom of the popup
                    // appears in the center
                    pos.y = (snos2.getHeight() / 2) - size.height;
                    m2.show(snos2, pos.x, pos.y);
                   
    }
     /*
      * Same as above
      */
      public void PopCustomerSensor3(){
         JPopupMenu m2 = new JPopupMenu();
         String sensorp=snos3.getName();
        ArrayList<String []> alist2=customer.queryRegisteredSENSORCustomers(sensorp);
                      
           
      if(alist2.isEmpty())
        {
         m2.add(new JMenuItem("NO ACTIVE SENSOR "+sensorp));
        // m2.addSeparator();            
        }else
        {
             int len2=alist2.size();
           m2.add(new JMenuItem("ACTIVE SENSORS "+sensorp));
           m2.addSeparator();            
                       
           for(int i=0;i<len2;i++)
         {
         String [] item=alist2.get(i);
           phone_call_Action= new JMenuItem(item[0]);
            m2.add(phone_call_Action);
            //m2.addSeparator();
         }
          // m2.addSeparator();
        }
            
            m2.pack();
                    Point pos = new Point();
                    // get the preferred size of the menu...
                    Dimension size = m2.getPreferredSize();
                    // Adjust the x position so that the left side of the popup
                    // appears at the center of  the component
                    pos.x = (snos3.getWidth() / 2);
                    // Adjust the y position so that the y postion (top corner)
                    // is positioned so that the bottom of the popup
                    // appears in the center
                    pos.y = (snos3.getHeight() / 2) - size.height;
                    m2.show(snos3, pos.x, pos.y);
                   
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpop1 = new javax.swing.JPopupMenu();
        snosPan = new javax.swing.JPanel();
        snostype = new javax.swing.JLabel();
        snosstatus = new javax.swing.JLabel();
        snos2 = new javax.swing.JLabel();
        safeLabel = new javax.swing.JLabel();
        intruderLabel = new javax.swing.JLabel();
        intruderLabel2 = new javax.swing.JLabel();
        snos3 = new javax.swing.JLabel();
        snos1 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setForeground(new java.awt.Color(51, 255, 204));
        setAutoscrolls(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("snosFram"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 200));

        snosPan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SNOS LIVE MONITOR", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        snostype.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        snostype.setText("CUSTOMERS");

        snosstatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        snosstatus.setText("STATUS");

        snos2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        snos2.setForeground(new java.awt.Color(204, 0, 204));
        snos2.setLabelFor(snos2);
        snos2.setText("SNOS2");
        snos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                snos2MouseEntered(evt);
            }
        });

        safeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        safeLabel.setForeground(new java.awt.Color(0, 0, 255));
        safeLabel.setText("INTRUDER");
        safeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                safeLabelMouseEntered(evt);
            }
        });

        intruderLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        intruderLabel.setForeground(new java.awt.Color(255, 0, 51));
        intruderLabel.setText("INTRUDER");
        intruderLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                intruderLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                intruderLabelMouseEntered(evt);
            }
        });
        intruderLabel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                intruderLabelAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        intruderLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        intruderLabel2.setForeground(new java.awt.Color(255, 0, 51));
        intruderLabel2.setText("INTRUDER");
        intruderLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                intruderLabel2MouseEntered(evt);
            }
        });
        intruderLabel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                intruderLabel2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        snos3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        snos3.setForeground(new java.awt.Color(204, 0, 204));
        snos3.setLabelFor(snos3);
        snos3.setText("SNOS3");
        snos3.setName(""); // NOI18N
        snos3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                snos3MouseEntered(evt);
            }
        });

        snos1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        snos1.setForeground(new java.awt.Color(204, 0, 204));
        snos1.setText("SNOS1");
        snos1.setName(""); // NOI18N
        snos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                snos1MouseEntered(evt);
            }
        });

        snosPan.setBackground(new java.awt.Color(240, 169, 98));

        javax.swing.GroupLayout snosPanLayout = new javax.swing.GroupLayout(snosPan);
        snosPan.setLayout(snosPanLayout);
        snosPanLayout.setHorizontalGroup(
            snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(snosPanLayout.createSequentialGroup()
                .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, snosPanLayout.createSequentialGroup()
                        .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(snos2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(snos1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(snosPanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(snostype, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(snosPanLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(snosstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, snosPanLayout.createSequentialGroup()
                        .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(safeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(intruderLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
            .addGroup(snosPanLayout.createSequentialGroup()
                .addComponent(snos3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intruderLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        snosPanLayout.setVerticalGroup(
            snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(snosPanLayout.createSequentialGroup()
                .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(snostype)
                    .addComponent(snosstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(snosPanLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(snos2)
                            .addComponent(intruderLabel)))
                    .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(snos1)
                        .addComponent(safeLabel)))
                .addGap(33, 33, 33)
                .addGroup(snosPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(snos3)
                    .addComponent(intruderLabel2))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(snosPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(snosPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void intruderLabelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_intruderLabelAncestorAdded
        // TODO add your handling code here:
       //mf.myAdmin2(mf, snosPan).setVisible(true);
    }//GEN-LAST:event_intruderLabelAncestorAdded

    private void intruderLabel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_intruderLabel2AncestorAdded
        // TODO add your handling code here:
         
    }//GEN-LAST:event_intruderLabel2AncestorAdded

    private void intruderLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_intruderLabelMouseEntered
        // TODO add your handling code here:
       // this.SetPop2();
        
    }//GEN-LAST:event_intruderLabelMouseEntered

    private void intruderLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_intruderLabelMouseExited
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_intruderLabelMouseExited

    private void safeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_safeLabelMouseEntered
        // TODO add your handling code here:
        //this.display("Fine", "safelabel ");
     //  this.SetPop();
        
    }//GEN-LAST:event_safeLabelMouseEntered

    private void intruderLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_intruderLabel2MouseEntered
        // TODO add your handling code here:
       // this.SetPop3();
    }//GEN-LAST:event_intruderLabel2MouseEntered

    private void snos1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_snos1MouseEntered
        // TODO add your handling code here:
        //call this sensore add method
        this.PopCustomerSensor();
    }//GEN-LAST:event_snos1MouseEntered

    private void snos2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_snos2MouseEntered
        // TODO add your handling code here:
        //call this sensore add method
        this.PopCustomerSensor2();
    }//GEN-LAST:event_snos2MouseEntered

    private void snos3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_snos3MouseEntered
        // TODO add your handling code here:
        //call this sensore add method
        this.PopCustomerSensor3();
    }//GEN-LAST:event_snos3MouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel intruderLabel;
    private javax.swing.JLabel intruderLabel2;
    private javax.swing.JPopupMenu jpop1;
    private javax.swing.JLabel safeLabel;
    private javax.swing.JLabel snos1;
    private javax.swing.JLabel snos2;
    private javax.swing.JLabel snos3;
    private javax.swing.JPanel snosPan;
    private javax.swing.JLabel snosstatus;
    private javax.swing.JLabel snostype;
    // End of variables declaration//GEN-END:variables

   
}
