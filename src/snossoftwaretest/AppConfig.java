/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author fupre1
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

 /**
 * This class encapsulates and defines the methods needed to load application configuration parameters (like database name, port name, host name, log limits, etc.) needed to properly initialize the software on startup.
 * @author Afam;
 * @see Connector1.java,SnosSendEmail.java,ReadMessages1.java,
 * @version 1.0 
 */
public class AppConfig 
{
    static File fileName=new File("Config");
    static  String userHome = System.getProperty("user.home",".");
     static String FILE_SEPARATOR = System.getProperty("file.separator","/"); 
     //static String configFileName = fileName+FILE_SEPARATOR+"config.properties";
     static File configFileName=new File("config.properties");
     static String logFileName = userHome+FILE_SEPARATOR+"applicationLog.log";
    public static void LoadIntoConfigFiles()
    {
        //(String dbhost,String port,String dbname,String dbuser,String dbpass )
    	Properties prop = new Properties();
 ///*
    	try {
    		//set the properties value
            /*
                prop.setProperty("dbhost", "localhost");
                prop.setProperty("dbport", "3306");
    		prop.setProperty("dbname", "biometric_database");
    		prop.setProperty("dbuser", "root");
    		prop.setProperty("dbpassword", "donsimon");
                prop.setProperty("logLimit", "5000000");
                */
                
                
 
    		//save properties to project root folder
    		prop.store(new FileOutputStream(configFileName), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
       // */
        
        
        
    }
     public static Properties LoadFromConfigFiles( )
    {
    	Properties prop = new Properties();
 
        
        try {
               //load a properties file
    		prop.load(new FileInputStream(configFileName));
  
               //get the property value and print it out
                /*
                System.out.println(prop.getProperty("dbhost"));
                System.out.println(prop.getProperty("dbport"));
                System.out.println(prop.getProperty("dbname"));
    		System.out.println(prop.getProperty("dbuser"));
    		System.out.println(prop.getProperty("dbpassword"));
                System.out.println(prop.getProperty("logLimit"));
                */
                
                
                
                
 
    	} catch (IOException ex) {
        }
        return prop;
        
    }
     
}
