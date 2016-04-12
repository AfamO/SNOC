/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import java.sql.*;
import java.util.Properties;
import javax.swing.*;
/**
 * This class encapsulates the whole database connection logic needed throughout the application..
 * @author Afam;
 * @see Reconsoft.java,AppConfig.java
 * @version 1.0 
 */
public class Connector1 {
    public Connection dbconn;
    public Connection rdbconn;
    private String  msgout;
     private Properties prop;
    public static Boolean IsRemoteConnSuccessful=false;
    /** Creates a new instance of Connector */
    public Connector1() {
    }
    
    public void databaseConnect() {
        
        try {
            prop=AppConfig.LoadFromConfigFiles();
        String host=prop.getProperty("dbhost");
        String port=prop.getProperty("dbport");
        String dbname=prop.getProperty("dbname");
        String dbuser=prop.getProperty("dbuser");
        String dbpass=prop.getProperty("dbpassword");
       // display("The config dbpass="+dbpass,"");
        if(host.equals("") || port.equals("") || dbname.equals("") || dbuser.equals(""))
        {
              SmsMain.setIsTimerStopped(true);
              display("Make Sure that your database configuration file has not been tampered with","DATABASE CONNECTION ERROR");
              dbconn=null;
              System.exit(1);
        }
        else
        {
            Class.forName("com.mysql.jdbc.Driver");
            dbconn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname, dbuser, dbpass);
            
        }
            //rdbconn = DriverManager.getConnection("jdbc:mysql://MySQL5.brinkster.com/teledom2013", "teledom2013", "Onime04");
            //dbconn.getNetworkTimeout();
           // msgout = ( "Connection successful\n" );
           //display("The network time out is: "+DriverManager.getLoginTimeout()+"","LOCAL CONNECTION UNSUCCESSFUL");
        }
        catch ( ClassNotFoundException cnfex ) {
            // process ClassNotFoundExceptions here
            SmsMain.setIsTimerStopped(true);
            cnfex.printStackTrace();
            msgout =  "Connection unsuccessful\n" +
            cnfex.toString() ;
            //display(msgout,"LOCAL CONNECTION UNSUCCESSFUL");
        }
        catch ( SQLException sqlex ) {
            // process SQLExceptions here
            SmsMain.setIsTimerStopped(true);
            sqlex.printStackTrace();
            msgout =  "This Application can't connect to database.\nThank you.";
            //sqlex.toString() ;
            display(msgout,"LOCAL CONNECTION UNSUCCESSFUL");
            
        }
        
    }
    public Connection getRemoteConn()
    {
        return rdbconn;
    }
   
   
     public static Boolean getIsRemoteConnSuccessful()
    {
       return IsRemoteConnSuccessful;
    }
     public void setIsRemoteConnSuccessful()
    {
       //return IsRemoteConnSuccessful;
    }
    
    public Statement create (){
        Statement stmt = null;
         try {
            stmt = dbconn.createStatement();
        }catch (SQLException ex) {
            display(msgout,"REMOTE CONNECTION UNSUCCESSFUL");
        }
        return stmt;
    }
     public Connection RemotedatabaseConnect() {
        
        try {
            prop=AppConfig.LoadFromConfigFiles();
        String host=prop.getProperty("rdbhost");
        String port=prop.getProperty("rdbport");
        String dbname=prop.getProperty("rdbname");
        String dbuser=prop.getProperty("rdbuser");
        String dbpass=prop.getProperty("rdbpassword");
        //display("The config dbuser="+dbuser,"");
        if((!host.equals("")) && (!port.equals("")) && (!dbname.equals("")) && (!dbuser.equals("")) && (!dbpass.equals("")))
        {
            Class.forName("com.mysql.jdbc.Driver");
            rdbconn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname, dbuser, dbpass);
        }
        else
        {
            display("Make Sure that your database configuration file has not been tampered with","DATABASE CONNECTION ERROR");
             rdbconn=null;
             //System.exit(1);
        }
                    //DriverManager.getConnection("jdbc:mysql://MySQL5.brinkster.com/teledom2013", "teledom2013", "Onime04");
            //dbconn = DriverManager.getConnection("jdbc:mysql://mysql1001.mochahost.com:3306/teledom_snossmsdb", "teledom_snos", "snos1");
            if(rdbconn!=null)
            {
                IsRemoteConnSuccessful=true;
                
                //display("Congratulations!\nConnection is now successful :Your SMS has been moved to online Database.");
            }
            else
            {
                IsRemoteConnSuccessful=false;
            }
            //dbconn = DriverManager.getConnection("jdbc:mysql://MySQL5.brinkster.com/teledom2013", "teledom2013", "Onime04");
            //System.out.println("MAKING A REMOTE CONNECTION");
           // msgout = ( "Connection successful\n" );
           // display (msgout);http://www.snoslive.6te.net/
            //DriverManager
        }
        catch ( ClassNotFoundException cnfex ) {
            // process ClassNotFoundExceptions here
            cnfex.printStackTrace();
            msgout =  "Connection unsuccessful\n" +
            cnfex.toString() ;
            //display(msgout);
             this.display("We are very sorry!that we cannot push your SMS to online database,\nMake sure that your INTERNET connection is  STRONG  OR that it is still established.\nThank You.","ABORTED REMOTE CONNECTION");
        }
        catch ( SQLException sqlex ) {
            // process SQLExceptions here
            sqlex.printStackTrace();
            msgout =  "Connection unsuccessful\n" +
            sqlex.toString() ;
           // display(msgout);
            this.display("We are very sorry!that we cannot push your SMS to online database,\nMake sure that your INTERNET connection is  STRONG  OR that it is still established.\nThank You.","ABORTED REMOTE CONNECTION");
           // display("We are very sorry!\nConnection is not successful,increase your bandwith :\nYour SMS has not been moved to online Database.\nThank You.");
        }
        catch ( Exception excp ) {
            // process remaining Exceptions here
            excp.printStackTrace();
            msgout =  excp.toString() ;
           // display(msgout);
             this.display("We are very sorry!that we cannot push your SMS to online database,\nMake sure that your INTERNET connection is  STRONG  OR that it is still established.\nThank You.","ABORTED REMOTE CONNECTION");
             //display("We are very sorry!\nConnection is not successful,increase your bandwith :\nYour SMS has not been moved to online Database.\nThank You.");
        }
        return rdbconn;
    }
    public void display( String msg,String label ) {
        //JOptionPane.showMessageDialog(null, msg,);
         JOptionPane.showMessageDialog(null,msg,label,2);
    }
    
    
}
