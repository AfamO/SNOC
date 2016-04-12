/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author AFAM;
 * @param class
 * 
 * This is the heart of the application: 
 * It defines the logic needed to properly set up modem, search for available ports,
 * connect to the modem, READS and RETRIEVED SMS (IF ANY) and 
 * calls the appropriate objects to display the messages.
 * @see SmsMain.java,SnosSoftwareTest.java,ReadMessageTimer.java
 * @version 1.0 
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
//import javax.comm.CommPortIdentifier;
//import javax.comm.SerialPort;
import gnu.io.SerialPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.crypto.spec.SecretKeySpec;

import javax.swing.JOptionPane;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.AGateway.Protocols;
import org.smslib.GatewayException;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Library;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.OutboundWapSIMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.USSDResponse;
//import org.smslib.crypto.AESKey;
import org.smslib.modem.SerialModemGateway;
//import org.
/**
 ** This is the heart of the application: 
 * It defines the logic needed to properly set up modem, search for available ports,
 * connect to the modem, READS and RETRIEVED SMS (IF ANY) and 
 * calls the appropriate objects to display the messages.
 * @author AFAM;
 * @see InboundNotification,GatewayException,InboundMessage
 * @version 1.0
 
 * 
 */
public class ReadMessages1
{
    private SerialModemGateway gateway;
    private Vector message;
    private SmsMain mf;
    public static String sender, message_sms_id;
    public static String messageStatus;
    private Connector1 dbconnector=new Connector1();
    public static String dat;
    public static int countTimer=0;
    public static String text;
    private String messageSent="";
    private String smsc;
    Thread   eventThread;
    public static int memIndex;
    public static String memloc,portInuse;
    private static int SnosPosition=0;
    private static Properties snosprop=new Properties();
    private Properties usersensorprop=new Properties();
    private static Properties RegisterdUserprop=new Properties();
    private static Properties RegisterdUserprop1=new Properties();
    private static List sensorList=new ArrayList<String>();
    private static ReportTableModel qtbl;
    private static Properties prop;
    private int mcount;
    public static Boolean IsNewInbound=false;
    public static Boolean IsRemoteSent=false;
     public static Boolean IsEmailSent=false;
    private static Boolean Isconnected=false;
    private static Service ServiceObj;
    private static Boolean IsNotModemPortsAvailable=false;
    private int i;
    private static int CountNew;
    private String basicsender;
    List<InboundMessage> msgList1;
    List<String> clients_contactsId=new ArrayList<String>();
    private static Boolean IsMessageRed=false;
    private static final String _NO_DEVICE_FOUND = "  No Device Found";

    private final static Formatter _formatter = new Formatter(System.out);

    static CommPortIdentifier portId;
    static volatile int MyInboxCounter=0;

    static Enumeration<CommPortIdentifier> portList;

    static int bauds=115200;
    private Reconsoft bis = new Reconsoft();
    private static String sim_pin="";
    private static String smsc_number="";
    public static String pass="";
    public static String email_sender="";
    //private Properties prop=AppConfig.LoadFromConfigFiles();
    /**
     *
     *Load the system property files that contain the configuration parameters
   
     * @return String
     * @throws Exception
     */
     public ReadMessages1()
     {
         //load property configuration files and validate them.
         prop=AppConfig.LoadFromConfigFiles();
         sim_pin=prop.getProperty("sim_pin");
         smsc_number=prop.getProperty("smsc_Number");
         email_sender=prop.getProperty("sender_address");
         pass=prop.getProperty("sender_password");
         if(sim_pin==null ||smsc_number==null  || email_sender==null || pass==null)
         {
              SmsMain.setIsTimerStopped(true);
            JOptionPane.showMessageDialog(null, " One of the configuration parameters for sending email \nand receiving/sending sms has been tampered with.\nPlease do check the configuration file of this application.\nThank you.","WRONG CONFIGURATION PARAMETERS",0); 
            
            System.exit(0);
         }
         if(sim_pin.equals("") ||smsc_number.equals("")  || email_sender.equals("") || pass.equals("")){
            SmsMain.setIsTimerStopped(true);
            JOptionPane.showMessageDialog(null, " One of the configuration parameters for sending email \nand receiving/sending sms has been tampered with.\nPlease do check the configuration file of this application.\nThank you.","WRONG CONFIGURATION PARAMETERS",0); 
            
            System.exit(0);
            
        }
        /*
         * Load SNOS properties from database and output them for debug purposes.
         * 
         */
     snosprop= bis.getALLSNOS();
     
      RegisterdUserprop=bis.getALLREGISTEREDSNOS();
     
     Collection coll=RegisterdUserprop.values();
     Enumeration enu=RegisterdUserprop.keys();
     Object[] keys=new Object[RegisterdUserprop.size()]; 
     int p=0;
     while(enu.hasMoreElements())
     {
         keys[p++]=enu.nextElement();
     }
     //Arrays.sort(keys);
     Object[] values=coll.toArray();
     Arrays.sort(values);
     System.out.println("MESSAGES FROM snosPROPS ARE:");
             
             
             int m=0;
           while (!RegisterdUserprop.isEmpty() && m<=RegisterdUserprop.size() &&m<values.length){
               String val=(String)keys[m];
            //String g=(String)snosprop.getProperty("2348064500095");
            System.out.println("WHILE THE KEY IS "+keys[m]+" The VALUE is "+RegisterdUserprop.getProperty(val)+" at position "+m);
               
               // System.out.println("VALUES FROM GENEREPORTTABLE CLASS "+" "+g);
                 System.out.println(" ");
                 m++;
                 
            }
           
           
             int pos;
        pos = getSnosPosition("SNOS2", RegisterdUserprop);
        System.out.println("tHE PoSITION OF SNOS2 IS "+" "+pos);
        
        sensorList = bis.getALLSENSORS();
       
     }
     /**
     * Get the list of serial communication ports found.
     * @return Enumeration
     * @throws Exception
     */
    private static Enumeration<CommPortIdentifier> getCleanPortIdentifiers()
    {
        return CommPortIdentifier.getPortIdentifiers();
    }
    /*
     * searches the position of a SNOS number from the lists in a property file.
     * * @return int
     */
    public final int getSnosPosition(String sender,Properties prop)
    {
        int position=0;
        Collection coll=prop.values();
        Enumeration enu=prop.keys();
        Object[] keys=new Object[prop.size()]; 
        int p=0;
        while(enu.hasMoreElements())
        {
            keys[p++]=enu.nextElement();
        }
        Arrays.sort(keys);
        Object[] values=coll.toArray();
        Arrays.sort(values);
        for(int l=0;l<=keys.length;l++)
        {
            if(keys[l].equals(sender) ||values[l].equals(sender) )
            {
                position=l;
                //System.out.println("tHE PoSITION OF  IS "+" "+l);
                //System.out.println("WHILE THE KEY IS "+keys[l]+" The VALUE is "+prop.getProperty(val)+" at position "+l);
                break;
            }
        }
        return position;
    }
    /*
     * This intantiates and starts a new thread instance for independly 
     * forwarding sms alert to client's email, remote database and contact's 
     * phone number-this last one is for action taken ONLY
     */
    public  void StartAllMessageThread()
    {
        MyRunnable t[]=new MyRunnable[3];
        String type[]={"alert","remotesms","email"};
        for(int k=0;k<t.length;k++)
        {
            t[k]=new MyRunnable(type[k]);
            System.out.println("The first thread is: "+k+" and the type is:"+type[k]);
            t[k].start();
            
            // JOptionPane.showMessageDialog(null,"The thread "+k+" with type "+type[k]+" has started");
            try
            {
                if(type[k].equals("remotesms"))
                {
                    t[k].join(3000);
                    //Thread.sleep(10000);
                }
            }
            catch(Exception e)
            {
            }
        }
          }
    /**
     *This is a method that checks whether this app is CURRENTLY connected to a modem.
     * @return boolean
     * @throws PortInUseException,NoSuchPortException, Exception
     * @param port
     * @see this is seeing
     * @serialData  this is my serial data.
     */
    public static boolean IsPortConnected(String port)
    {
        boolean verify=false;SerialPort serialPort = null;
       try
       {
           
           //System.out.println("\nSearching for Devices...");
        portList = getCleanPortIdentifiers();
       // portLst=
        int i=0;
        
        
           while (portList.hasMoreElements())
        {
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
                {
                    
                       // _formatter.format("%nFound port: %-5s%n", portId.getName());
                        if(portId.getName().equals(port))
                        {
                          //  _formatter.format("%n port is intact: %-5s%n", portId.getName());
                        }
                        else
                        {
                            // _formatter.format("%n port is NOT intact: %-5s%n", portId.getName());
                            //verify=true;
                            
                        }
                        
            portId=CommPortIdentifier.getPortIdentifier(port);
                        serialPort = (SerialPort)portId.open("TeledomSMSLibCommTester", 1971);
                         serialPort.setFlowControlMode(org.smslib.helper.SerialPort.FLOWCONTROL_RTSCTS_IN);
                         InputStream in=serialPort.getInputStream();
                         int c=in.read();
                         if(c==-1)
                         {
                               verify=true;
                             //javax.swing.JOptionPane.showMessageDialog(null,"This Port is In Use :"); 
                         }
                         else
                         {
                             verify=false;
                             //javax.swing.JOptionPane.showMessageDialog(null,"This Port is Not In Use :"); 
                         }
                        if(serialPort==null){
                            verify=true;
                             //javax.swing.JOptionPane.showMessageDialog(null,"This Port is In Use :"); 
                        }
                        else
                        {
                            verify=false;
                            // javax.swing.JOptionPane.showMessageDialog(null,"This Port is Not In Use :"); 
                        }
                       
                }
                
        }
       
           
       }
       
       catch(PortInUseException e){
           verify=true;
           //javax.swing.JOptionPane.showMessageDialog(null,"This Port is In Use :"); 
           _formatter.format("%nTHIS  port is IN USE and thus NOT intact: %-5s%n", portId.getName());
       }
       catch(NoSuchPortException e){
           verify=true;
       }
       catch (Exception ex) {
                ex.printStackTrace();
                
            } 
        finally
                    {
                        if (serialPort != null)
                        {
                            serialPort.close();
                        }
                    }
       return verify;
       
    }
    /*
     * This method get the Lists of all the availabele serial ports
     * so that it the application can connect to one of them.
     * @return List
     * @throws PortInUseException,NoSuchPortException, Exception
     * @param port
     * @see this is seeing
     */
    
    public static List getAllModemPorts()
    {
        List<CommPortIdentifier> portLst=new ArrayList<CommPortIdentifier>();
        System.out.println("\nSearching for Devices...");
        portList = getCleanPortIdentifiers();
        int i=0;
        while (portList.hasMoreElements())
        {
            i++;
            portId = portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
                _formatter.format("%nFound port: %-5s%n", portId.getName());
                //for (int i = 0; i < bauds.length; i++)
                //{
                    SerialPort serialPort = null;
                    _formatter.format("       Trying at %6d...", bauds);
                    try
                    {
                        countTimer++;
                        InputStream inStream;
                        OutputStream outStream;
                        int c;
                        String response;
                        portId=CommPortIdentifier.getPortIdentifier(portId.getName());
                     
                        serialPort = (SerialPort)portId.open("TeledomSMSLibCommTester", 1971);
                        serialPort.setFlowControlMode(org.smslib.helper.SerialPort.FLOWCONTROL_RTSCTS_IN);
                        serialPort.setSerialPortParams(bauds, org.smslib.helper.SerialPort.DATABITS_8, org.smslib.helper.SerialPort.STOPBITS_1, org.smslib.helper.SerialPort.PARITY_NONE);
                        
                        inStream = serialPort.getInputStream();
                        outStream = serialPort.getOutputStream();
                        serialPort.enableReceiveTimeout(1000);
                        c = inStream.read();
                        // String model="me";
                        String model="me"; 
                           //javax.swing.JOptionPane.showMessageDialog(null,"The Model of the Modem is:"+model); 
                               // javax.swing.JOptionPane.showMessageDialog(null,"The Model of the Modem is:"+model);
                        while (c != -1)
                        
                           
                            
                           // countTimer++;
                            c = inStream.read();
                     
                        outStream.write('A');
                        outStream.write('T');
                        outStream.write('\r');
                        
                        Thread.sleep(1000);
                        response = "";
                        StringBuilder sb = new StringBuilder();
                        c = inStream.read();
                        while (c != -1)
                        {
                           
                            countTimer++;
                            sb.append((char) c);
                            c = inStream.read();
                        }
                        response = sb.toString();
                        if (response.indexOf("OK") >= 0)
                        {
                           
                            try
                            {
                                countTimer++;
                                System.out.print("  Getting Info...");
                                outStream.write('A');
                                outStream.write('T');
                                outStream.write('+');
                                outStream.write('C');
                                outStream.write('G');
                                outStream.write('M');
                                outStream.write('M');
                                outStream.write('\r');
                                response = "";
                                c = inStream.read();
                                while (c != -1)
                                {
                                   
                                    response += (char) c;
                                    c = inStream.read();
                                }
                                 
                                portLst.add(portId);
                               
                                System.out.println("\nThe port of the device is "+portId.getName() +"\n" );
                               // System.out.println("\nOriginal Response is: " +response+"\n");
                                //System.out.println("\nThe CountTimer is: " +countTimer+"\n");
                              
                                System.out.println(" Found: " + response.replaceAll("\\s+OK\\s+", "").replaceAll("\n", ""));
                                
                                
                                
                                
                            }
                            catch (Exception e)
                            {
                                System.out.println(_NO_DEVICE_FOUND);
                                countTimer=9;
                            }
                        }
                        else
                        {
                            System.out.println(_NO_DEVICE_FOUND);
                            countTimer=9;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.print(_NO_DEVICE_FOUND);
                        countTimer=9;
                        Throwable cause = e;
                        while (cause.getCause() != null)
                        {
                            cause = cause.getCause();
                        }
                        System.out.println(" (" + cause.getMessage() + ")");
                    }
                    finally
                    {
                        if (serialPort != null)
                        {
                            serialPort.close();
                        }
                    }
                //}
            }
        }
        if(portLst.isEmpty())
        {
            IsNotModemPortsAvailable=true;
            javax.swing.JOptionPane.showMessageDialog(null, "There are no SERIAL COMMUNICATION/MODEM ports installed/available in your system\nOr REMOVE from your system the device(s) using up your serial ports.\nAlso make sure that your MODEM is PROPERLY CONNECTED to your system.\nThank you.","NO AVAILABLE SERIAL PORTS ",0);
            System.exit(1);
        }
        
         return portLst;
    }
    
   /*
    * This method actually connects to a MODEM the first time the app starts
    * @return boolean
     * @throws GatewayException,SMSLibException,TimeoutException,IOException,InterruptedException
     * @param port
     * @see this is seeing
    */
    public void ConnectToAModem(){
        List<CommPortIdentifier> portList1;
        InboundNotification inboundNotification = new InboundNotification();
        OutboundNotification outboundNotification = new OutboundNotification();
        // Create the notification callback method for inbound voice calls.
        CallNotification callNotification = new CallNotification();
        //Create the notification callback method for gateway statuses.
        GatewayStatusNotification statusNotification = new GatewayStatusNotification();
        OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
        try
        {
                System.out.println("SNOS: Read sms messages from a serial gsm modem.");
                System.out.println(Library.getLibraryDescription());
                System.out.println("Version: " + Library.getLibraryVersion());
                // Create the Gateway representing the serial GSM modem.
                portList1=getAllModemPorts();
                System.out.println("The number of ports is ONLY "+portList1.size());
                if(portList1.size()>1)
                    {
                        portList1.remove(portList1.get(0));
                    }
                 gateway = new SerialModemGateway("modem_"+portList1.get(0).getName(), portList1.get(0).getName(), 115200, "Huawei", "E160");
                 portInuse=portList1.get(0).getName();
                //System.out.println("The Gateway response is: "+gateway.getModemDriver().getResponse());
                // Set the modem protocol to PDU (alternative is TEXT). PDU is the default, anyway...
                gateway.setProtocol(Protocols.PDU);
                // Do we want the Gateway to be used for Inbound messages?
                gateway.setInbound(true);
                // Do we want the Gateway to be used for Outbound messages?
                gateway.setOutbound(true);
                // Let SMSLib know which is the SIM PIN.
                gateway.setSimPin(sim_pin);
                gateway.setSmscNumber(smsc_number);
                Service.getInstance().setOutboundMessageNotification(outboundNotification);
                // Set up the notification methods.
                Service.getInstance().setInboundMessageNotification(inboundNotification);
                Service.getInstance().setCallNotification(callNotification);
                Service.getInstance().setGatewayStatusNotification(statusNotification);
                Service.getInstance().setOrphanedMessageNotification(orphanedMessageNotification);
                // Add the Gateway to the Service object.
                Service.getInstance().addGateway(gateway);
    //try {
        // Similarly, you may define as many Gateway objects, representing
        // various GSM modems, add them in the Service object and control all of them.
        // Start! (i.e. connect to all defined Gateways)
        Service.getInstance().startService();
    } 
    catch (TimeoutException ex) {
        ex.printStackTrace();
    } 
    catch (GatewayException ex) {
        ex.printStackTrace();
    }
    catch (SMSLibException ex) {
        ex.printStackTrace();
    } 
    catch (IOException ex) {
        ex.printStackTrace();
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
                Isconnected=true;
                //assign the service object to be used later to read sms messages.
                ServiceObj= Service.getInstance();
        }
    /*
    * This method actually disconnects to a MODEM the from the app.
    * @return boolean
     * @throws GatewayException,SMSLibException,TimeoutException,IOException,InterruptedException
     * @param port
     * @see this is seeing
    */
    public static void DisConnectModem(){
        if(Isconnected==true)
        {
           //try {
                try {
                    ServiceObj.stopService();
                } catch (TimeoutException ex) {
                    ex.printStackTrace();
                } catch (GatewayException ex) {
                    ex.printStackTrace();
                }
                 catch (SMSLibException ex) {
                     ex.printStackTrace();
            } 
                catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            Isconnected=false;
        
        }
    }
    public static String  getSMS_Message_Sender()
     {
       return sender;  
     }
    
    
    
     public static String  getSMS_Message_TEXT()
     {
       return text;  
     }

    public Boolean getIsconnected()
    {
        return Isconnected;
    }
    public static Properties getSnosProp()
    {
        return snosprop;
    }
    public static Properties getRegisterdUserprop()
    {
        return RegisterdUserprop;
    }
    public static int getSnosPosition()
    {
        return SnosPosition;
    }
    public static Boolean getIsNotModemPortsAvailable()
    {
        return IsNotModemPortsAvailable;
    }
    public void SetSMS_Message_id(String mess)
     {
         
         message_sms_id=mess;
     }
    public static String  getSMS_Message_id()
     {
       return message_sms_id;  
     }
    /*
    * This method actually READS THE SMS ALERTS FROM THE MODEMthe.
    * @return Properties
     * @throws GatewayException,SMSLibException,TimeoutException,IOException,InterruptedException
     * @param port
     * @see this is seeing
    */
    public Properties GetMessages() throws Exception
        {
        prop=new Properties();
             i=0;
             msgList1=new ArrayList<InboundMessage>();
             Enumeration<String> enu1;
             message=new Vector();
            //GetSet gs=new GetSet();
              List<InboundMessage> msgList=new ArrayList<InboundMessage>();
               //Connects to the Modem before you can read
               ConnectToAModem();
               if(Isconnected)
             {
		
		try
		{
                    /*
                     * This can give you the Modem personal info.
                        System.out.println();
			System.out.println("Modem Information:");
			System.out.println("  Manufacturer: " + gateway.getManufacturer());
			System.out.println("  Model: " + gateway.getModel());
			System.out.println("  Serial No: " + gateway.getSerialNo());
			System.out.println("  SIM IMSI: " + gateway.getImsi());
			System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
			System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
			System.out.println();
                        */
			// In case you work with encrypted messages, its a good time to declare your keys.
			// Create a new AES Key with a known key value. 
			// Register it in KeyManager in order to keep it active. SMSLib will then automatically
			// encrypt / decrypt all messages send to / received from this number.
			//ServiceObj.getKeyManager().registerKey("+306948494037", new AESKey(new SecretKeySpec("0011223344556677".getBytes(), "AES")));
			// Read Messages. The reading is done via the Service object and
			// affects all Gateway objects defined. This can also be more directed to a specific
			// Gateway - look the JavaDocs for information on the Service method calls.
                         // int i=0;
                         //String []value=new String[4];
                        
			ServiceObj.readMessages(msgList, MessageClasses.ALL);
                       
                        mcount=Service.getInstance().getInboundMessageCount();
                       
                         
                        //loop through the InboundMessage
                        for(InboundMessage msg:msgList)
                        {
                            if(msgList.isEmpty())
                            {
                                javax.swing.JOptionPane.showMessageDialog(null, "No Messages availabel yet\n,Service Provider Problem");
                            }
                        }
                        //set the message to a vector object that will be used later in 
                        //a table class/object for display in GUI form.
                        this.SetMessage(message);
                       //create a report table object for GUI table display of the sms alerts
                       qtbl=new ReportTableModel(message);
                       IsMessageRed=true;
                       System.out.println("\n THE OVERALL VECTOR SIZE IS :"+msgList.size());
              
			// Sleep now. Emulate real world situation and give a chance to the notifications
			// methods to be called in the event of message or voice call reception.
			System.out.println("Now Sleeping - Hit <enter> to stop service.");
			System.in.read();
			System.in.read();
		}
		catch (Exception e)
		{
		}
		
        }
               return prop;   
                
                 
	}

    public static Boolean getIsMessageRed()
    {
        return IsMessageRed;
    }
    public static Boolean getIsNewInbound()
    {
        return IsNewInbound;
    }
    /*
    * This method actually SENDS SMS MESSAGES-using this MODEM as a gateway- the from the app.
    * @return boolean
     * @throws GatewayException,SMSLibException,TimeoutException,IOException,InterruptedException
     * @param port
     * @see this is seeing
    */
     public  Boolean SendMessages(String recipient,String text,String address) 
        {  
            Boolean IsMessageSent=false;
            Vector  message=new Vector();
           Enumeration<String> enu1;
           
            //GetSet gs=new GetSet();
              List<InboundMessage> msgList=new ArrayList<InboundMessage>();
               
               
               if(Isconnected)
             {
		
		try
		{
                    // In case you work with encrypted messages, its a good time to declare your keys.
			// Create a new AES Key with a known key value. 
			// Register it in KeyManager in order to keep it active. SMSLib will then automatically
			// encrypt / decrypt all messages send to / received from this number.
			//ServiceObj.getKeyManager().registerKey("+306948494037", new AESKey(new SecretKeySpec("0011223344556677".getBytes(), "AES")));
			// Read Messages. The reading is done via the Service object and
			// affects all Gateway objects defined. This can also be more directed to a specific
			// Gateway - look the JavaDocs for information on the Service method calls.
                         
                    // compose the message to send-ensure you have enough credit in your subsriber SIM card
                        String textTosend="";
                        textTosend="From: SNOS Team\n"
                                  + "Title:SECURITY BRIDGE ALERT\n"
                                  + "Message/Sensor:"+text+"\n"
                                  + "Location:"+address+"\n"
                                  + "Website:www.snosfortress.com";
                        messageSent=textTosend;
			OutboundMessage msg, msg1;
                        OutboundWapSIMessage wapMsg;//for possible or future wap messages
                        wapMsg = new OutboundWapSIMessage("2348064500095",new java.net.URL("http://www.snos.com/"), "Visit snos.com!"
                                 + ".\nFrom SNOS Team\n"
                                 + "Website:snos.com\n"
                                 + "Message/Sensor:Urgent Assitance");
                        wapMsg.setUrl(new java.net.URL("http://www.snosfortress.com/"));
                        msg1 = new OutboundMessage("2348064500095", "Messgage Sent to "+ recipient);
                        msg = new OutboundMessage(recipient,textTosend);
                        msg.setFrom("http://www.snosfortress.com.com");
                        msg.setDispatchDate(new java.util.Date());
                        msg.setFlashSms(true);
                        msg.setGatewayId(gateway.getGatewayId());
                        msg.setRecipient(recipient);
                        boolean sendUSSDRequest;
                        IsMessageSent = ServiceObj.sendMessage(msg);
                        if( IsMessageSent)
                {
                     IsMessageSent =true;
                }
                else
                {
                     IsMessageSent =false;
                }
                
		System.out.println(msg);
                //System.out.println("The URL IS:"+wapMsg.getUrl());
                System.out.println("The USSD response IS: "+new USSDResponse().getRawResponse());
                //System.out.println("The Text IS: "+wapMsg.getDataBytes().toString());
                      
		}
		catch (TimeoutException | GatewayException | IOException | InterruptedException e)
		{
                    IsMessageSent=false;
		}
		
             }
                
              
             return IsMessageSent;   
                
                 
	}
    public void SetMessage(Vector v){
        this.message=v;
    }
    public int getCountNew()
    {
        return CountNew;
    }
     public static ReportTableModel getObject(){
        return qtbl;
    }
    public Vector GetRow(){
        
       
       
       return message;
    }
     
     public Properties getProp()
     {
         return prop;
     }
    /**
 ** This is a background thread class that constantly waits and receives new 
 * sms on arrival to the modem. 
 * It notifies this app immediately new sms arrives to the Modem
 * @author AFAM;
 * @see ReadMessages1
 * @version 1.0
 * 
 */
     public class InboundNotification implements IInboundMessageNotification
     {
         int count=0;
         String values[];
         /*
          * This searches for item in a List of InboundMessage object
          */
         public Boolean SearchItem(String text,String dat,List<InboundMessage> items)
           {
                boolean answer=false; 
               for(int j=0;j<items.size();j++)
               {
                   if((!items.get(j).getDate().toString().equals(dat)) && (!items.get(j).getText().equals(text)))
                   {
                       System.out.println("Search Info:="+dat+" IS NOT EQUALS "+items.get(j).getDate().toString());
                       answer=true;
                       break;
                   }
                   else
                   {
                       answer=false;
                       System.out.println("Search Info:="+dat+"  EQUAL "+items.get(j).getDate().toString());
                   }
               }
               return answer;
           }
         /*
          * This gets the current snos position from the property object
          * containing the List of SNOS numbers.
          */
          public final int getSnosPosition(String sender,Properties prop)
           {
               int position=0;
               Collection coll=prop.values();
               Enumeration enu=prop.keys();
               Object[] keys=new Object[prop.size()]; 
               int p=0;
               while(enu.hasMoreElements())
               {
                   keys[p++]=enu.nextElement();
               }
               Object[] values=coll.toArray();
               Arrays.sort(values);
               for(int l=0;l<=keys.length;l++)
               {
                   String val=(String)keys[l];
                   //System.out.println("WHILE THE KEY IS "+keys[l]+" The VALUE is "+prop.getProperty(val)+" at position "+l);
                   if(keys[l].equals(sender) ||values[l].equals(sender))
                   {
                       position=l;
                       //System.out.println("tHE PoSITION OF  IS "+" "+l);
                       //System.out.println("WHILE THE KEY IS "+keys[l]+" The VALUE is "+prop.getProperty(val)+" at position "+l);
                       break;
                   }
               }
               return position;
           }
          /*
           * This method performs the heart of the method of this class-It recieves Inbound 
           * sms alert, processes and forwards it to email, remote hosts, etc using different 
           * threads for each of this.
           */
     public void process(AGateway gateway1, MessageTypes msgType, InboundMessage msg)
     {
         if (msgType == MessageTypes.INBOUND)
         {
             try {
                 //This makes a sound to notify that an alert just arrived
                 Toolkit.getDefaultToolkit().beep();
                 //System.out.println(">>> The IsActionTakeCounter= " + SnosFrame.IsActionTakeCounter+" while the size of ActionTakenListCount is:"+SnosFrame.IsActionTakenCountList.size()+" and the size of the IsActionTakenList boolean ="+SnosFrame.IsActionTakenList.size());
                 System.out.println(">>> New Inbound message detected from Gateway: " + gateway1.getGatewayId());
                 System.out.println(msg);
                 //get the details(date, text, sender's gsm number) of an alert that just arrived.
                 sender=msg.getOriginator();
                 // dat=msg.getDate().toLocaleString();
                 dat=new java.util.Date().toString();//Use the default system time zone-which is currently set at WAT(West Central African Time=UTC+1)
                 //NB: You MUST set the current system where this app resides to use WAT for consistence and proper arrangment/ordering at snosfortress.com
                 text=msg.getText();
                 memIndex=msg.getMemIndex();
                 memloc=msg.getMemLocation();
                 smsc=msg.getSmscNumber();
                values=new String[5];
                messageStatus="Untreated";
                RegisterdUserprop1=bis.getALLREGISTEREDSNOS();
                String num=sender;
                //get the SNOS number of the sender using his gsm number
                sender=RegisterdUserprop1.getProperty(sender);
                //Is the stranger unknown to this app-I mean is he/she not registered?
                if(sender==null || sender.equals(""))
                {
                    //then he must be a stranger
                    sender="STRANGER";
                    SnosPosition=-1;//The sender GSM number is not registered
                    //Display  the appropriate message
                    //javax.swing.JOptionPane.showMessageDialog(null, "Just received an alert from stranger with number: "+num+"\nPlease contact the administrator or visit  www.snosfortress.com  to register so as to \nbe able to use this application since you are a stranger to us.\nThank you ");
                }
                else
                {
                    //then the gsm number is registered and known by this app.get the in a list of gsm numbers position
                    SnosPosition=this.getSnosPosition(sender, RegisterdUserprop1);
                    //put this sms data into an array that will be added to a vector object.
                    values[0]=sender;values[1]=text;values[2]=dat;
                 /*******************************************************
                 * The push message section of code begins below
                 * 
                 *********************************************************/

                //check the existing device information
                /*
                if(bis.CheckExistingMobile_info(sender))
                {
                    String [] fone_info=bis.queryListPhone_Info(sender);
                    //String push_id=fone_info[0];
                    String device_id=fone_info[0];
                    new DeviceMessagePusher(device_id,text);
                }
                */
                //then add the array of value to the vector object
                message.addElement(values);
                //set a boolean value indicating that ypu just received an new alert.
                IsNewInbound=true;
                try{
                    
                }
                catch(Exception e){

                }
                //get a the GUI home class object
                SmsMain sm=SNOSSoftwareTest.getObj();
                //refresh it by exiting and reloading the Report Form Table to display the newwest alert.
                sm.ExitGene();
                sm.RefreshGeneReportTable();
                //Does the Gsm number sender exists in the database-I mean is it registered?
                if(SnosPosition!=-1)
                {
                    //then start all the thread needed to forward the alert to local host, email and etc.
                      StartAllMessageThread();
                      //read the new inbound text and delete it
                InboundMessage im=ServiceObj.readMessage(gateway.getGatewayId(), memloc, memIndex);
                
                    if(gateway.deleteMessage(im))
                    {
                        System.out.println("THE MESSAGE OF "+sender+" HAS BEEN DELETED");
                        //JOptionPane.showMessageDialog(sm, "THE MESSAGE OF "+sender+" HAS BEEN DELETED");
                     }
                      else
                               {

                                 System.out.println("THE MESSAGE OF "+sender+" could NOT be DELETED");
                                 //JOptionPane.showMessageDialog(sm, "THE MESSAGE OF "+sender+" HAS NOT BEEN DELETED");
                               }
                }
                else
                {
                }
              }
              
             } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                 Logger.getLogger(ReadMessages1.class.getName()).log(Level.SEVERE, null, ex);
             }



         }
         //this is for status report-it was not implemented in this version, but this could be done in higher version.
         else if (msgType == MessageTypes.STATUSREPORT) System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
         }
     }
     public class OutboundNotification implements IOutboundMessageNotification
     {
		public void process(AGateway gateway, OutboundMessage msg)
		{
			System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
			System.out.println(msg);
                        
		}
	}
        /*
         * this is for call notification-it was not implemented in this version, but this could be done in higher version.
         */
	public class CallNotification implements ICallNotification
	{
		public void process(AGateway gateway, String callerId)
		{       
			System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
		}
	}
        /*
         * this notifies one when there is a GateWayStatusNotification.
         */
	public class GatewayStatusNotification implements IGatewayStatusNotification
	{
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)
		{
			System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
		}       
	}
        /*
         * this is for OrphanedMessageNotification-it was not implemented in this version, but this could be done in higher version.
         */
	public class OrphanedMessageNotification implements IOrphanedMessageNotification
	{
            public boolean process(AGateway gateway, InboundMessage msg)
            {
                System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());
		System.out.println(msg);
		// Since we are just testing, return FALSE and keep the orphaned message part.
                return false;
            }
        }
}

