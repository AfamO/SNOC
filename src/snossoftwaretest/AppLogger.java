/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author fupre1
 */
import org.apache.log4j.Logger;
/**
 * This deals  with the application’s logging logic.
 * @author Afam;
 * @see Connector1.java,SnosSendEmail.java,ReadMessages1.java,
 * @version 1.0 
 */

public class AppLogger {
 
//initializing the logger
    //PropertyConfigurator.configure(args[0]);
static Logger log = Logger.getLogger(AppLogger.class.getName());
 
/**
 * @param args the command line arguments
 */
 public static void main(String[] args) {
 //logging in different levels
 log.trace("This is a Trace");
 log.debug("This is a Debug");
 log.info("This is an Info");
 log.warn("This is a Warn");
 log.error("This is an Error");
 log.fatal("This is a Fatal");
 System.out.println("The logged info is "+log.getParent().getAllAppenders().nextElement());
 }
}
