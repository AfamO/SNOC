/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import static snossoftwaretest.SmsMain.IsSuperAdminLoggedIn;
import static snossoftwaretest.SmsMain.Setuserid;
import static snossoftwaretest.SmsMain.edit_type;
import static snossoftwaretest.SmsMain.mbar;
import static snossoftwaretest.SmsMain.snosFrameObject;
import static snossoftwaretest.SmsMain.loginButton;
/**
 * This is the class that defines implements all the methods, codes and properties 
 * needed for the application GUI's (home page, menu bars, menu items, tabs, etc.) 
 * setup, initializations and other similar functions.
 * @param Mainpan-This is a JPanel object that represents and holds most components of these home page ;
 * @author AFAM, Charles
 * @see SNOSSoftwareTest,SnosFrame.java,ReadMessages1.java
 * @version 1.0 
 */
public class SmsMain extends JFrame  {
    private JTabbedPane tab;// This a tab that houses most of the apps components
    private GeneReportTable sms_report_table,sms_report_table_second=new GeneReportTable();// This are objects of the class that reports and displays SMS tables
    private JPanel home_pane_tab; //This holds 'HOME' tab component
    private JPanel sms_panel_tab; //This holds 'SMS' tab component
    private JPanel help_panel; //This is deprecated- not very much in used, found in 'Help' method tab component
    private JPanel monitor_snos_cctv;  //This holds 'Monitor SNOS' tab component.
    private JPanel sms_back_up; //This holds 'SMS Backup' tab component.
    private JPanel Mainpanel; //This is the page's home panel
    private JPanel cos_idpan;// This panel is used to hold customer's action taken form
    public static JButton loginButton,exitButton;//These tow buttons are respectively used to login into and exit the app .
    private JMenuBar bar;//This is for menu bar. 
    public static  JMenuBar mbar;//This is also for menu bar. 
    private static int SnosNumber=0;//This is a SNOS number counter or incrementer-used for snos frame monitor
    private static Boolean StopTimer=false; //This boolean is used to control the JProgress Timer and splash screen especially during Modem scan-at the app's start-up.
    private static int j=0; //this is just a normal counter.
    private static int Total_InitialSnos_Numbers=0;// This holds the total number of SNOS clients as retrieved from database.
    private static int snos_frame_nos1=0;//This is a global variable that holds the exepected and calculated number of SNOS frame to be displayed during SNOS status monitor.
    private static int SnosFrameNumber=0;//global snosframe number holding the current number of generated SNOS JInternal form frame
    private static String getmyuserid[]=new String[4];// this array holds administrators' user  ids
    public static Boolean IsSnosFrameListDeleted=false;// This checks wether a delete has happened in the SNOS frame ArrayList.
    private static List labeList=new ArrayList<JLabel>();// This holds the snos frame label ArrayList
    private static SmsMain smsMainObject;//This is just the object of this class.
    public static SnosFrame snosFrameObject; // This is just the object of SnosFrame class.
    public static String snos_id=""; //This id is used to track SNOS number during the stages of client registration and update.
    private static JMenu smsbar; //This is for sms menu bar
    private static int get_datacount=0;//This is used to count the client.
    public static String edit_type="";//This is used to track the type of data edit(client, device or contacts) taking place during client's personal, device/object and contacts data modification.
    public static Boolean IsSuperAdminLoggedIn=false;//This is used to track the type of administrator(CSO, super admin, admin,etc) that just logged in
    String userid="";//This holds the administrator's user id during administration's data modification/change
    private List SnosFrameList=new ArrayList<SnosFrame>();//This is an arrayList holding the generated instances of SNOSFrame object for SNOS CCTV-like status monitor
    private JLabel noc_name,noc_city,noc_logo;// This respectively holds the CUSTOMIZED SNOC name, SNOC city and SNOC Logo configured at the first start up of this app.
    
    private Reconsoft databaseObject=new Reconsoft();//This just gets the instance of database object
    private   UserInforGet userInfoObject;//This is just an instance of UserInforGet class that define's app-wide's get and set methods.
    
    SmsMain(){
     //sets the home title 
     setTitle("SECURITY NETWORK OPERATION CENTRE (SNOC)");
     //sets needed icon
     this.setIconImage(new ImageIcon(getClass().getResource("see.jpg")).getImage());
     //call this first set of methods for proper intializations
        ConnectionPage();
        HelpPage();
        SettingPage();
        BackUPPage();
        SMSPage2();
        geneRateSnosFrame(this);
        /*
         * Creates a  Menu Bar adding menu items
         * like file,exit, change setup and send notification(for push notification services to mobile phones running SNOS mobile app)
         */
         bar= new JMenuBar();
         bar.setVisible(false);
         mbar=bar;
        JMenu file=new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        //this allows initial app customization or set up to be changed
        JMenuItem update_setup = new JMenuItem("Change Setup");
        //create push notification for mobile phones using SNOS mobile apps.
        JMenuItem App_notify = new JMenuItem("Send Notification");
        file.setFont(new Font("Verdana",Font.BOLD,14));
        file.setForeground(Color.BLUE);
        file.add(exit);
        file.add(update_setup);
        file.add(App_notify );
        exit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
           System.exit(0); 
        }
    });
        
      App_notify.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        App_Notification bis = new App_Notification();
        bis.setLocationRelativeTo(null);
        bis.setSize(500, 300);
        bis.setVisible(true);
        bis.setResizable(false);
        bis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        }
    }); 
        
        
        
        update_setup.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            //this allows app customization set up modification/change
         userInfoObject=databaseObject.QueryHome_Name_Logo();
         
        if(userInfoObject.getNoc_name()==null||userInfoObject.getNoc_city()==null)
       {
           
       JOptionPane.showMessageDialog(null,"Customization Information does not Exist");
       
       }else
        {
            String software_title=userInfoObject.getNoc_name();
            String city=userInfoObject.getNoc_city();
       setup_change bis = new setup_change(software_title,city);
       bis.setLocationRelativeTo(null);
       bis.setSize(500,400);
       bis.setVisible(true);
       bis.setResizable(false);
       bis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        }
    });
        //View SMS alerts table
        smsbar=new JMenu("SMS");
        JMenuItem add = new JMenuItem("View SMS Table");
        JMenuItem add1 = new JMenuItem("MONITOR SNOS Live");
        smsbar.setFont(new Font("Verdana",Font.BOLD,14));
       smsbar.setForeground(Color.BLUE);
       smsbar.add(add);
       smsbar.add(add1);
       
       add.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
//         Adduser b= new Adduser();
        // b.setSize(400, 300);
         //b.pack();
            SMSPage();
        
        }
    });
       add1.addActionListener(new ActionListener(){
           @Override
        public void actionPerformed(ActionEvent e){
//         Adduser b= new Adduser();
        // b.setSize(400, 300);
         //b.pack();
           //generate SNOS internal frames for CCTV-like security status display
           geneRateSnosFrame(smsMainObject);
        
        }
    });
       
     //Register a client for the first time
       JMenu addClient=new JMenu("Client SetUp_Registration");
       //client personal data
       JMenuItem client = new JMenuItem("Register Client");
       //client's objects/properites-to secure data
       JMenuItem device = new JMenuItem("Register Client's Device_Object Data");
       //client's contacts persons data
       JMenuItem contacts = new JMenuItem("Register Client's Contacts");
       addClient.setFont(new Font("Verdana",Font.BOLD,14));
       addClient.setForeground(Color.BLUE);
       addClient.add(client);
       addClient.add(device);
       addClient.add(contacts);
       client.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         AddClient b= new AddClient();
         b.setResizable(false);
         b.toFront();
         b.setLocationRelativeTo(null);
         b.setVisible(true);
         //b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    });
       device.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         AddDeviceInfo b= new AddDeviceInfo();
         b.setResizable(false);
         b.toFront();
         b.setLocationRelativeTo(null);
         b.setVisible(true);
         //b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    });
       contacts.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         AddContacts b= new AddContacts();
         b.setResizable(false);
         b.toFront();
         b.setLocationRelativeTo(null);
         b.setVisible(true);
         //b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    });
       //This allows one to modify already registered client's data
       JMenu editClient=new JMenu("Client DataEdit_View");
       JMenuItem clientedit = new JMenuItem("Edit Client Info");
       JMenuItem deviceedit = new JMenuItem("Edit Client's Device");
       JMenuItem contactsedit = new JMenuItem("Edit Client's Contacts"); 
       editClient.setFont(new Font("Verdana",Font.BOLD,14));
       editClient.setForeground(Color.BLUE);
       editClient.add(clientedit);
       editClient.add(deviceedit);
       editClient.add(contactsedit);
       clientedit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
             edit_type="client";
            IsSuperAdminLoggedIn=true;
                    Calllogin();
            
        }
    });
       deviceedit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            edit_type="device";
          IsSuperAdminLoggedIn=true;
                    Calllogin();
            
         
        }
    });
       
       contactsedit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            edit_type="contacts";
            IsSuperAdminLoggedIn=true;
                    Calllogin();
             
        }
    });
       /*
        * This sets up adminstration management 
        */
       JMenu admin=new JMenu("Administration");
       JMenu adduser=new JMenu("Add Users");
       JMenu snsos_sensorman=new JMenu("Snos-Sensor Management");
       JMenuItem snos = new JMenuItem("Add Snos Types");
       admin.setFont(new Font("Verdana",Font.BOLD,14));
       admin.setForeground(Color.BLUE);
       admin.add(adduser);
       admin.add(snos);
       admin.add(snsos_sensorman);
       snos.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
       
         AddSnos b= new AddSnos();
         b.setResizable(false);
         b.toFront();
         b.setLocationRelativeTo(null);
         b.setVisible(true);
       
        }
    });
       
        JMenuItem user = new JMenuItem("Add Administrator");
       // JMenuItem user1 = new JMenuItem("Add Moderator");
        adduser.setFont(new Font("Verdana",Font.BOLD,14));
        adduser.setForeground(Color.BLUE);
        adduser.add(user);
       // adduser.add(user1);
        user.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
       
         Operator b= new Operator();
         b.setResizable(false);
         b.toFront();
         b.setLocationRelativeTo(null);
         b.setVisible(true);
       
        }
    });
        /*
         * This allows one to change app's Administrator's registered Login data
         */
      JMenuItem editOperator = new JMenuItem("Change Operator Login details");
        
        adduser.add(editOperator);
         
        editOperator.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            
            userid=javax.swing.JOptionPane.showInputDialog(null, "Please enter your userid");
       
    if(userid.equals("")||userid==null)
    {
      JOptionPane.showMessageDialog(null, "Please userid Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        Reconsoft data=new Reconsoft();
        if(!data.CheckIfItemExist("admin","user_id", userid))
        {
            JOptionPane.showMessageDialog(null, " USERID DOES NOT EXIST,\nCheck your data Or Alert Administrator");
        
        }else
        {
            String stat=data.Showmystatus(userid);
           Setuserid(userid,1);
           Setuserid(stat,2);
          Change_user cal2 = new Change_user();
          cal2.setLocationRelativeTo(null);
          cal2.setVisible(true);
          cal2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
            
        }
            
            
    }
 }
    });
        JMenuItem sen = new JMenuItem("Add Sensor");
       admin.setFont(new Font("Verdana",Font.BOLD,14));
       admin.setForeground(Color.BLUE);
       //admin.add(sen);
       snsos_sensorman.setFont(new Font("Verdana",Font.BOLD,14));
        snsos_sensorman.setForeground(Color.BLUE);
        snsos_sensorman.add(snos);
        snsos_sensorman.add(sen);
       sen.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
       
        AddSensor b= new AddSensor();
          b.setResizable(false);
         b.toFront();
         b.setLocationRelativeTo(null);
         b.setVisible(true);
       
        }
    });
       
        
       JMenuItem assignSensor = new JMenuItem("Assign Sensor To Users");
       admin.setFont(new Font("Verdana",Font.BOLD,14));
       admin.setForeground(Color.BLUE);
       snsos_sensorman.add(assignSensor);
       assignSensor.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
       
         AssignSensor b= new AssignSensor();
         b.setResizable(false);
         b.setLocationRelativeTo(null);
         b.setVisible(true);
       
        }
    });
       JMenuItem edit_snos = new JMenuItem("Edit/Change SnosId");
       admin.setFont(new Font("Verdana",Font.BOLD,14));
       admin.setForeground(Color.BLUE);
       snsos_sensorman.add(edit_snos);
       edit_snos.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            edit_type="snosid";
            IsSuperAdminLoggedIn=true;
                    Calllogin();
         
       
        }
    });
       /*
        * This sets up and allows one to view the App statistics and Reports 
        * like SMS Reports,User's Reports, SNOS/Sensor Reports 
        */
        JMenu stat=new JMenu("Statistics");
       
        JMenu smsreport=new JMenu("SMS Report");
        JMenuItem sms_ID = new JMenuItem("Search SMS By Customer's ID");
        JMenuItem sms_day = new JMenuItem("View Daily SMS");
             smsreport.add(sms_ID);
             sms_ID.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        JOptionPane.showConfirmDialog(null, "Search SMS By Customer's ID is coming soon.." , null, 2); 
        }
    });
             smsreport.add(sms_day);
             sms_day.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        JOptionPane.showConfirmDialog(null, "View Daily SMS is coming soon.." , null, 2); 
        }
    });
        
        
        JMenu userreport=new JMenu("Users Report");
        JMenuItem registered_user = new JMenuItem("Search Registered By Customer's ID");
        JMenuItem user_snos_sensor = new JMenuItem("All Registered Users and their Snos/Sensors");
        userreport.add(user_snos_sensor);
        user_snos_sensor.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         JOptionPane.showConfirmDialog(null, "All Registered Users and their Snos/Sensors is coming soon.." , null, 2); 
        }
    });
        
        JMenu snos_sensorreport=new JMenu("Snos-Sensors Report");
        JMenuItem regsnos = new JMenuItem("All Registered Snos");
        JMenuItem regsensor = new JMenuItem("All Registered Sensors");
        
        snos_sensorreport.add(regsnos);
        regsnos.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         JOptionPane.showConfirmDialog(null, "All Registered Snos is coming soon.." , null, 2); 
        }
    });
        snos_sensorreport.add(regsensor);
        snos_sensorreport.add(regsensor);
        regsensor.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         JOptionPane.showConfirmDialog(null, "All Registered Sensors is coming soon.." , null, 2);  
        }
    });
        
        stat.setFont(new Font("Verdana",Font.BOLD,14));
        stat.setForeground(Color.BLUE);
        stat.add(userreport);
        stat.add(smsreport);
        stat.add(snos_sensorreport);
        userreport.add(registered_user);
        registered_user.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        Report b= new Report();
        b.setResizable(false);
        b.setLocationRelativeTo(null);
        b.setVisible(true);     
        }
    });
        
        JMenu actionR = new JMenu("Action Taken Report");
        
        JMenuItem actD = new JMenuItem("View Daily Action Taken");
        JMenuItem actID = new JMenuItem("View Action By Customer's ID");
        JMenuItem act_ALL = new JMenuItem("View All Action Taken");
        actionR.add(actID);
        actionR.add(actD);
        actionR.add(act_ALL);
        stat.add(actionR); 
        
         actD.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
          
         SimpleDateFormat ft= new SimpleDateFormat("dd-MMM-yyyy");
         
             Date me= new Date();
             String dat =ft.format(me);
          
           Reconsoft data = new Reconsoft();
           ArrayList<String []> actionlist=data.queryCustomersDAILY_LIST_ACTION_Taken(dat);
           
           if(actionlist.isEmpty())
           {
              
           JOptionPane.showConfirmDialog(null, "NO Action Taken Information Found For the day" , null, 2);
           
           }else
           {
             StringBuilder sb = new StringBuilder(64);
      
    sb.append("<html><table>");
   
    
     sb.append("<tr><td style='font-size: 14px;font-weight: bold;margin-bottom:5px;margin-top: 5px; text-align:center'>").append("LIST OF DAILY ACTION TAKEN  "+"</td></tr>"); 
    
     sb.append("<tr><td> <table cellpadding='0' cellspacing='0' align='center'  >"
             + "<tr>");
     
  
        sb.append("<th width=100 scope=col>Customer's ID</th>");
        sb.append("<th width=250 scope=col>Sensor Message</th>");
        sb.append("<th width=100 scope=col>Action Type</th>");
        sb.append("<th width=350 scope=col>Message Sent</th>");
        sb.append("<th width=100 scope=col>Date Taken</th>");
        sb.append("</tr>");
     
      sb.append(" <tr><td height='5'>&nbsp;&nbsp;</td></tr>");
     
    
   
    int len4=actionlist.size();
        
      for(int i=0;i<len4;i++)
        {
           String action []=actionlist.get(i);
             
   sb.append("<tr>");     
   sb.append("<td style='color:blue; text-align:center'>").append(action[0]).append("</td>");
   sb.append("<td style='color:blue; text-align:center'>").append(action[1]).append("</td>");
   sb.append("<td style='color:blue; text-align:center'>").append(action[2]).append("</td>");
   sb.append("<td style='color:blue; text-align:center'>").append(action[3]).append("</td>");
   sb.append("<td style='color:blue; text-align:center'>").append(action[4]).append("</td>");
   sb.append("</tr>");
           }
    
     sb.append("</table></td></tr>");
     
     
    
    sb.append("</table></html>");
    
    cos_idpan = new JPanel();
    
    
     JLabel printL = new JLabel(sb.toString());
     
    cos_idpan.add(printL);
     
         
    JFrame cos_idf = new JFrame("CUSTOMER'S DAILY ACTION TAKEN");
    cos_idf .setLayout(new FlowLayout());
    cos_idf .setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    cos_idf .add(cos_idpan);
    cos_idf .pack();
    cos_idf .setLocationRelativeTo(null);
    cos_idf .setVisible(true);
          
          
           }
     
       
     
            
             }
  }); 
       
      actID.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           
      String ID =JOptionPane.showInputDialog(null, "Enter Customer's ID"); 
      Reconsoft data = new Reconsoft();
       String cos_print[] =data.queryListCustomers_printable_personnal(ID.trim());
     
       
      if(cos_print==null)
      {
        JOptionPane.showConfirmDialog(null, "NO Customer's Information Found \n Make Sure this Client is Properly Registered" , null, 2); 
        
      }else
      {
          StringBuilder sb = new StringBuilder(64);
      
    sb.append("<html><table>");
    sb.append("<tr><td style='font-size: 12px;font-weight: bold;margin-bottom:5px;margin-top: 5px; text-align:center'>").append("CUSTOMER'S INFORMATION</td></tr>");
    
    
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("CUSTOMER'S NAME: ").append(cos_print[0]+" "+cos_print[1]+" "+cos_print[2]).append("</td>");
    sb.append("</tr>");
         
    sb.append("<tr>");     
    sb.append("<td style='color:blue'>").append("MOBILE NUMBER: ").append(cos_print[3]).append("</td>");
    sb.append("</tr>");
    
    
     sb.append("<tr><td style='font-size: 14px;font-weight: bold;margin-bottom:5px;margin-top: 5px; text-align:center'>").append("CUSTOMER'S LIST OF ACTION TAKEN</td></tr>"); 
    
     sb.append("<tr><td> <table cellpadding='0' cellspacing='0' align='center'  >"
             + "<tr>");
     
  
       
     
      ArrayList<String []> actionlist=data.queryCustomersLIST_ACTION_Taken(ID.trim());
      
      if(actionlist.isEmpty())
      {
        sb.append("<tr><td style='font-size: 14px;font-weight: bold;margin-bottom:5px;margin-top: 5px; text-align:center'>").append("NO ACTION TAKEN INFORMATION FOUND</td></tr>");    
      }else
      {
          
          
        sb.append("<th width=250 scope=col>Sensor Message</th>");
        sb.append("<th width=100 scope=col>Action Type</th>");
        sb.append("<th width=350 scope=col>Message Sent</th>");
        sb.append("<th width=100 scope=col>Date Taken</th>");
        sb.append("</tr>");
     
      sb.append(" <tr><td height='5'>&nbsp;&nbsp;</td></tr>");
        int len4=actionlist.size();
        
      for(int i=0;i<len4;i++)
        {
           String action []=actionlist.get(i);
             
   sb.append("<tr>");     
   sb.append("<td style='color:blue; text-align:center'>").append(action[0]).append("</td>");
   sb.append("<td style='color:blue; text-align:center'>").append(action[1]).append("</td>");
   sb.append("<td style='color:blue; text-align:center'>").append(action[2]).append("</td>");
   sb.append("<td style='color:blue; text-align:center'>").append(action[3]).append("</td>");
   sb.append("</tr>");
           }   
      }
   
   
     sb.append("</table></td></tr>");
     
     sb.append("</table></html>");
    
    cos_idpan = new JPanel();
    
    
     JLabel printL = new JLabel(sb.toString());
     
    cos_idpan.add(printL);
     
         
    JFrame cos_idf = new JFrame("CUSTOMER'S DETAILS OF ACTION TAKEN");
    cos_idf .setLayout(new FlowLayout());
    cos_idf .setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    cos_idf .add(cos_idpan);
    cos_idf .pack();
    cos_idf .setLocationRelativeTo(null);
    cos_idf.setResizable(false);
    cos_idf .setVisible(true);
    PrintClientInfor.printComponent(cos_idpan);
          
      }
        
            
       }
    }); 
      
      act_ALL.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
       
      JPanel actionReportPane = new JPanel(); 
      actionReportPane.setLayout(new BorderLayout());
      Action_show show2 = new  Action_show();
      actionReportPane.add(show2);
        JFrame con= new JFrame();
       con.add(actionReportPane);
       con.setSize(1000, 650);
        con.setResizable(false); 
       con.setLocationRelativeTo(null);
       con.setVisible(true);
       con.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          
            
    }
 }); 
       JMenu help=new JMenu("Help");
        JMenuItem online = new JMenuItem("Online Help...");
        JMenuItem about = new JMenuItem("About SNOS...");
        help.setFont(new Font("Verdana",Font.BOLD,14));
        help.setForeground(Color.BLUE);
        help.add(online);help.add(about);
        about.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          
            about_snos bt = new about_snos();
             bt.setSize(500, 550);
             bt.setVisible(true);
             bt.setLocationRelativeTo(null);
             bt.setResizable(false);
             bt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
            
        }
    }); 
      
        
     online.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          
         try {
         
        // if(adlc_id !=null)
        // {
          String url = "http://www.snosfortress.com/about.jsp";
         
         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));   
        // }else
         //{
          //JOptionPane.showMessageDialog(null,"Make Sure that your Connection configuration file has not been tampered with\n Contact Administrator");   
        // }
         
         
       }
       catch (IOException ex) {
           System.out.println(ex.getMessage());
           JOptionPane.showMessageDialog(null,"Network Failure\n Contact Administrator"); 
       }    
            
        }
    }); 
     // create a logout menu to be added to the menu bar
     JMenu logout=new JMenu("Logout");
     JMenuItem lgout = new JMenuItem("LogOut");
     logout.setFont(new Font("Verdana",Font.BOLD,12));
     logout.setForeground(Color.BLUE);
     logout.add(lgout );
     lgout.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e){
         if(JOptionPane.showConfirmDialog(null,"Are you sure that you want to log out?")==0){
             bar.setVisible(false);
             loginButton.setEnabled(true);
             exitButton.setEnabled(true);

         }
         else
         {

         }


     }
 });  
       bar.add(file);
       bar.add(smsbar);
       bar.add(addClient);
       bar.add(editClient);
       bar.add(admin);
       //bar.add(adduser);
       bar.add(stat);
       bar.add(help);
       bar.add(logout);
        
        Mainpanel = new JPanel();
        Mainpanel.setLayout(new BorderLayout());
        getContentPane().add(Mainpanel);
        
        
        // Create a tabbed pane new javax.swing.ImageIcon(getClass().getResource("/teledomsms/sms_new.jpg"))
       /*
        * Create a tabbed pane and add Home, SMS, Monitor SMS and Back-Up tab links and their corresponding
        * icon images.
        */
		tab = new JTabbedPane();
		tab.addTab( "Home",new ImageIcon(getClass().getResource("/snossoftwaretest/see.jpg")), home_pane_tab );
		tab.addTab( "SMS",new ImageIcon(getClass().getResource("/snossoftwaretest/sms.jpg")), sms_panel_tab,"Read Messages" );
                tab.addTab( "Monitor SNOS",new ImageIcon(getClass().getResource("/snossoftwaretest/hometab.jpg")), monitor_snos_cctv );
                tab.addTab( "Back-up",new ImageIcon(getClass().getResource("/snossoftwaretest/back.jpg")),sms_back_up );
                tab.setForeground(Color.BLUE);
                tab.setBackground(Color.WHITE);
                //Adds to the Main Panel.
                Mainpanel.add(bar,BorderLayout.NORTH);
                Mainpanel.add( tab, BorderLayout.CENTER );
    }
    /*
     * Set up and displays the SMS Report Table displaying currently received 
     * sms alerts and their details in the home tab
     */
    public void myAdmin(SmsMain mf)
    {
        GeneReportTable show = new  GeneReportTable(mf);
        home_pane_tab.add(show);
        show.toFront();
        show.setLocation(100, 100);
        show.setVisible(true);
    }
    /*
    * Set up and displays the SMS Report Table displaying currently received sms 
    * alerts and their details in another panel having sms logo in the tab
    */
    public GeneReportTable myAdmin1(SmsMain mf)
    {
        GeneReportTable show = new  GeneReportTable(mf);
        sms_panel_tab.add(show);
        show.toFront();
        show.setLocation(100, 100);
        return show;
    }
    /*
    * Thiis refreshes and re-set ups and displays the SMS Report Table displaying currently received sms alerts and their details
    */
    public void RefreshGeneReportTable()
    {
        sms_report_table=myAdmin1(smsMainObject);
        sms_report_table_second=sms_report_table;
        
    }
    /*
     * return the GeneReportTable object neededed to refresh this same form by hiding and displaying it again-thus 
     * giving the impression of an apparent 'animation'
     */
    public GeneReportTable getGene()
    {
        return sms_report_table_second;
    }
    /*
     * hides the same GeneReportTable object in question
     */
    public void ExitGene()
    {
        this.getGene().setVisible(false);
    }
    //sets up GeneReportTable for sms page.
    public void SMSPage(){
        
        myAdmin(this);
      
          }
     public static void Setuserid(String myid,int y)
   {
     SmsMain.getmyuserid[y]=myid; 
   }
    
    
   public static String [] getmyuserid()
   {
     return SmsMain.getmyuserid;  
   }
   /*
    * Construct the Help Page
    */
    public void HelpPage(){
        
        SMStabled n = new SMStabled();
        help_panel = new JPanel();
        help_panel.setLayout(new BorderLayout());
        help_panel.add(n);
       
        
        }
    
    
    public static int getSnosNumber()
     {
         
         return SnosNumber;
         
     }
    /*
     * Adds an instance of signal label to a JLabel ArrayList object
     */
     public static void setSignalLabel(JLabel l)
     {
         labeList.add(j++, l);
         
     }
     public static int getInitialSnosNumber()
     {
         return Total_InitialSnos_Numbers;
     }
      /**
     *
     * @return
     */
    public static java.util.List getLabeList()
     {
         
         return labeList;
         
     }
    
    
    public static int clientcount()
    {
      return get_datacount;  
    }
     /**
     *
     * @param no
     */
    
     public static void setSnosNumber(int no)
     {
         
          SmsMain.SnosNumber=no;
         
     }
    public static void setcountNumber(int no)
     {
         
          SmsMain.get_datacount=no+1;
         
     }
    /*
     * This sets up and calls a Login Form
     * that will enable an administrator to Login into the application.
     */
     public  void Calllogin()
     {
         AdminLogin b=new AdminLogin(this,this);
         b.setResizable(false);
         b.toFront();
         b.setLocationRelativeTo(null);
         b.setVisible(true);
     }
     /*
      * This is responsible for generating and displaying JInternal frame into small 
      * 300 by 200 small frames for displaying security status('SAFE' AND 'INTRUDER') 
      * with the corresponding client names and SNOS numbers. 
      */
     public void geneRateSnosFrame(SmsMain mf)
     {
         //get the database instance
         Reconsoft dat = new Reconsoft();
         //get the total number of registered clients
         int Clients_Total_numbers=dat.CountRegisteredClient();
         //ReadMessages1.getRegisterdUserprop().size();
         int snos_frame_nos;
         //get the number of JInternal frame to display based on the total number of clients divide by 3.
         //I mean since 3 names per frame will be displayed.
        snos_frame_nos = Clients_Total_numbers/3;
        //assign the total client number to be total initial snos numbers
        Total_InitialSnos_Numbers=Clients_Total_numbers;
        int y_axis;//Vertical line :This is the number of horizontal snos frame counting from top to down. I mean  the vertical numberrings generated and displayed in the HOME JFRAME
        //get approximate vertical frame numbers-to be used for outer loops.
        y_axis = snos_frame_nos/4;
         int x_axis=4;//this is because 4 internal frames will be displayed per horizontal line.
         //Is the snos_frame number NOT evenly divisble by 4(since we calculated four frames per horizontal line)
         if(snos_frame_nos%4!=0)
         {
             y_axis+=1;//Then get exact number -no longer approximate-by adding extra vertical frame number 
         }
         //Does  the clients total numbers not a multiple of 3(recall 3 SNOS labels per frame), and does it have 1(ONE) as a remainder at any point in time? 
         if(Clients_Total_numbers%3==1)
         {
             y_axis+=1;
             //then add 1 aditional snos frame horizontally-to cater for the additional number
             x_axis+=1;
             //do thesame to the frame number which was just approximate initially.
             snos_frame_nos+=1;
         }
         //Does  the clients total numbers not a multiple of 3(recall 3 SNOS labels per frame), and does it have 2(TWO) as a remainder at any point in time? 
         else if(Clients_Total_numbers%3==2)
         {
           y_axis+=1;
           //then add 1 aditional snos frame horizontally-to cater for the additional number.
           x_axis+=1;
           //do thesame to the frame number which was just approximate initially.
           snos_frame_nos+=1;
       }  
         System.out.println("The snos Number is:"+Clients_Total_numbers);
         System.out.println("The snos Frame Number is:"+snos_frame_nos);
         System.out.println("The Y-axis is:"+y_axis);
         System.out.println("The X-axis is:"+x_axis);
         int widt=0;int heigt=0;
         int y=0;
         
         do
         {
             //the height of the snosframe is 200.So the current height at any point must be a multiple of 200
             heigt=y*200;
             //loop 4 times since the Home Frame ihas been calculated to accept 4 internal 
             //snos frames(which have been measured at 300 x 200 each)
             for(int x=0;x<0x4;x++)
             {
                 //the width of the snosframe is 300.So the current width at any point must be a multiple of 300
                 widt=x*300;
             //Is the current SNOS Number now equals to the total number of registerd clients?
             if(SnosNumber==Clients_Total_numbers)
            {
                //then set this to be zero. 
                SmsMain.setSnosNumber(0);
            }
            SnosFrameNumber++;
            //re-assign this local variable to a global variable that has a global scope-I mean that can be accessed anywhere in the class
            snos_frame_nos1=snos_frame_nos;
            //is the global SNOS frame number counter less than the calculated number of internal snos frame? 
            if(SnosFrameNumber<=snos_frame_nos)
            {
                //increment the SNOS number counter
                SnosNumber++;
                //generate an internal snos Jframe
                SnosFrame show = new  SnosFrame(mf);
                show.setTitle("Monitor for SNOS"+y+"-"+x);
                //add it to a tab in the homepage
                monitor_snos_cctv.add(show);
                show.toFront();
                show.setLocation(widt, heigt);
                show.setVisible(true);
                //add the instance to SNOSFrame ArrayList to be referenced and used later.
                SnosFrameList.add(show);
                //re-assign it to another object to be used later.
                snosFrameObject=show;
                System.out.println("IT IS OKAY WHEN The snos Frame Number is:"+SnosFrameNumber);
                System.out.println("Here the width is: "+widt+" while the height is:"+heigt);
                
                }
                //Does the ArrayList contain less snos frame than needed or contained by the global frame counter?
                else if(SnosFrameList.size()< SnosFrameNumber && IsSnosFrameListDeleted==true)
                {
                    //Does the ArrayList contain less snos frame number?
                    if(SnosFrameList.size()<snos_frame_nos1)
                    {
                        //then still increment the SNOS number counter
                        SnosNumber++;
                        //still generate extra  internal snos Jframe
                        SnosFrame show;
                        show = new  SnosFrame(mf);
                        show.setTitle("Monitor for SNOS"+y+"-"+x);
                        monitor_snos_cctv.add(show);
                        show.toFront();
                        show.setLocation(widt, heigt);
                        show.setVisible(true);
                        SnosFrameList.add(show);
                        snosFrameObject=show; 
                    }
                }
             }
         
            //Increment the current height to 200 to go another NEW horizontal line
             heigt+=200;
             //increment the outer loop counter
             y++;
         }
         //check wether the outer loop has reached the desired number of horizontal lines.
         while(y!=y_axis);
     }
    
     private void CLEAR_ALL_SNOS_FRAMES()
     {
        
         for(int k=0;k<SnosFrameList.size();k++)
         {
             SnosFrame show = (SnosFrame)SnosFrameList.get(k);
             show.setVisible(false);
             
             
         }
         SnosFrameList.clear();
         System.out.println("The SnosFrame Size is: "+SnosFrameList.size());
         IsSnosFrameListDeleted=true;
         
         
     }
      public static Boolean getIsTimerStopped()
     {
         return StopTimer;
     }
     
      public static void setIsTimerStopped(boolean val)
     {
         StopTimer=val;
     }
     /*
      * Sets the BorderLayout
      */
     public void SMSPage2(){
        
       // SMStable n = new SMStable();
       sms_panel_tab = new JPanel();
        sms_panel_tab.setLayout(new BorderLayout());
        //pan1.add(n);
       
        
        }
    
    /*
     * Checks whether the smslib rxtx dll needed to start-and retrieve sms for- this app has been loaded and is found in the correct path?
     */
     public static boolean checkforDll(String dll)
    {
       
        boolean IsDllMissing=false;
        
        try{
       System.loadLibrary(dll);
           
               System.out.println("dll is Loaded");
              // IsDllMissing=true;
           
        }
        catch(java.lang.UnsatisfiedLinkError ex)
        {
              IsDllMissing=true;
            System.out.println("dll is missing");
        }
        return IsDllMissing;
    }


    /*
     * Do the initial configuration like Home Log db access, 
     * image icons loading and other intial configuration before displaying this page
     */
    public void ConnectionPage(){
      
        userInfoObject=databaseObject.QueryHome_Name_Logo();
        
       // JLabel mod= new JLabel("PLEASE SELECT THE PORTS TO CONNECT");
         final ImageIcon icon = new ImageIcon(getClass().getResource("/snossoftwaretest/home4.png"));
         home_pane_tab = new JPanel(){			
            protected void paintComponent(Graphics g){			
            g.drawImage(icon.getImage(), 0,0, null);
            super.paintComponent(g);
            //super.paintComponent(g);
            
        }	
        };
        home_pane_tab.setLayout(null);
        
        home_pane_tab.setOpaque(false);
        //set up Login Button at the home page.
        loginButton= new JButton("LOGIN");
        loginButton.setBounds(300,570, 80, 20 );
        loginButton.setForeground(Color.BLUE);
        loginButton.setFont(new Font("Verdana",Font.BOLD,12));
        loginButton.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e)
              {
                 
                 Calllogin();
              }
                 });
        //set up the Exit Button at the home page
       exitButton= new JButton("EXIT");
       exitButton.setBounds(600,570, 120, 20 );
       exitButton.setForeground(Color.BLUE);
       exitButton.setFont(new Font("Verdana",Font.BOLD,12));
       exitButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               if(JOptionPane.showConfirmDialog(null,"ARE YOU SURE YOU WANT TO SHUT DOWN THIS SOFTWARE?")==0)
               {
                   //shut down this app
                   System.exit(0);
               }
               else
               {

               }
                 
                
              }
                 });         
        noc_name=new JLabel(userInfoObject.getNoc_name().toUpperCase());
        noc_name.setBounds(150,10,950, 40);
        //noc_name.setForeground(new java.awt.Color(0, 85, 134));
        noc_name.setForeground(Color.white);
        noc_name.setFont(new java.awt.Font("Segoe UI Symbol",1, 34));
        noc_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       // company_name.setForeground(Color.BLUE);
        //company_name.setFont(new Font("Verdana",Font.BOLD,36));
        
        String city =userInfoObject.getNoc_city();
     
        noc_city=new JLabel(city.toUpperCase());
        noc_city.setBounds(350,50,500, 40);
        //noc_city.setForeground(new Color(0, 85, 134));
        noc_city.setForeground(Color.WHITE);
        noc_city.setFont(new Font("Segoe UI Symbol",1, 34));
        noc_city.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        
        
        noc_logo=new JLabel(new ImageIcon(userInfoObject.getLogo()));
        noc_logo.setBounds(0,0,150,100);
        
       //add these data(city name, sity address and logo) to the home pane
        home_pane_tab.add(noc_name);
        home_pane_tab.add(noc_city);
        home_pane_tab.add(noc_logo);
        //add login and exit buttons too.
        home_pane_tab.add(loginButton);
        home_pane_tab.add(exitButton);
      
    }
    
    
    
    /**
     *
     */
    
    
    public void SettingPage(){
      monitor_snos_cctv = new JPanel(); 
      monitor_snos_cctv.setLayout(null);
      monitor_snos_cctv.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 0, 153), new java.awt.Color(255, 255, 0)));
      monitor_snos_cctv.setBackground(new java.awt.Color(240, 169, 98));
    }
    /*
     * This shows the back up alerts stored in the database.
     */
    public void BackUPPage(){
      sms_back_up = new JPanel();
      
      sms_back_up.setLayout(new BorderLayout());
      backupShow  b = new backupShow ();
      sms_back_up.add(b);
       
    }
    /*
     * This simply adds JMenu files
     */
    public void AddJmenu(){
       bar= new JMenuBar();
       JMenu file=new JMenu("File");
       file.setFont(new Font("Verdana",Font.BOLD,14));
       file.setForeground(Color.BLUE);
       JMenu addsnos=new JMenu("Add Device");
       addsnos.setFont(new Font("Verdana",Font.BOLD,14));
       addsnos.setForeground(Color.BLUE);
       bar.add(file);bar.add(addsnos);
       Mainpanel.add(bar);
        
    }
}
