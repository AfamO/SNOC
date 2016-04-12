/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;


/**
 *
 * @author Charles
 */

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.NewsAddress;
import javax.swing.JOptionPane;

/**
 * This manages and defines the methods needed 
 * to setup and send e-mails 
 * in any part of the application. 
 * @author AFAM;
 * @see MyRunnable.java, ReadMessages1.java
 * @version 1.0
 *
 */

public class SnosSendEmail  extends Object{
    private String host;
    private String port;
    private String ssl_smtp_port;
    private static String sender,sender_username;
    private static String pass;
    //private Properties prop=AppConfig.LoadFromConfigFiles();
   
     public SnosSendEmail(){
        //load the configuration parameters from local configuration/property file
        Properties prop=AppConfig.LoadFromConfigFiles();
         host=prop.getProperty("smtphost");
         port=prop.getProperty("smtpport");
        sender=prop.getProperty("sender_address");
        sender_username=prop.getProperty("sender_username");
         pass=prop.getProperty("sender_password");
         ssl_smtp_port=prop.getProperty("ssl_smtp_port");
        //display("The config dbuser="+dbuser,"");
        if(host.equals("") || port.equals("") || sender.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(null, " One of the parameters for sending email has been tampered with","WRONG EMAIL PARAMETERS",2); 
            System.exit(0);
        }
        
    }
// Sends this email to the host and port
    public boolean SNOSemailSendToOne( final String sendereEmail, final String password,String title,String Mess,String Sendto)
    {
        boolean Isent=false;
      try{
        

        Properties props = new Properties();
        props.put("mail.smtp.host", host); // for gmail for yahoo use smtp.mail.yahoo.com mail.brinkster.net
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        //props.put("mail.smtp.ssl.trust", host);
        //props.put("mail.smtp.socketFactory.port", ssl_smtp_port);
        //props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.ssl.checkserveridentity", "true");
        props.put("mail.smtp.ssl.trust", "*");
        //props.put("mail.smtp.socketFactory.fallback", "false");
        
        
       

        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender_username, password);
            }
        });

        mailSession.setDebug(true); // Enable the debug mode
        Message msg = new MimeMessage( mailSession );
        msg.setHeader("content-type", "text/plain");
        //msg.setContent(new Multi-part()  para)
        MimeBodyPart part = new MimeBodyPart();

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        msg.setFrom( new InternetAddress( sendereEmail ) );

            InternetAddress addressTo ;
            //for (int i = 0; i < to.length; i++)
            //{
                addressTo = new InternetAddress(Sendto);
            //}
            msg.setRecipient(Message.RecipientType.TO, addressTo);
            //set receiver information
        msg.setSentDate( new Date());
        msg.setSubject( title );

        //--[ Create the body of the mail
        msg.setText( Mess );

        //--[ Ask the Transport class to send our mail message
        Transport trnsport;
        trnsport = mailSession.getTransport("smtp");
        trnsport.connect();
        msg.saveChanges(); 
        trnsport.sendMessage(msg, msg.getAllRecipients());
        trnsport.close();
         Isent=true;
       System.out.println( "Message Sent!");
       System.out.println( "CONTENT TYPE: "+msg.getContentType());
       System.out.println( "HEADER TYPE: "+msg.getHeader("content-type"));
    }catch(Exception E){
         Isent=false;
        System.out.println( "Oops something has gone pearshaped!");
        javax.swing.JOptionPane.showMessageDialog(null, "THIS SOFTWARE COULD NOT SEND EMAIL!\nThis application failed to send email to remote email address\nPlease do contact your email service provider or your web host\nAlso do ensure that all the email parameters are in tact.\nThank you.","FAILURE TO SEND EMAIL ",0);
        System.out.println( E );
        E.printStackTrace();
        
    }
      

      return Isent;
      }
    
    public static void main(String [] args)
{
     new SnosSendEmail().SNOSemailSendToOne(sender, pass,"SNOS REGISTRATION CONFIRMATION","Welcome to this email!!!\n\n\nSNOS Team" ,"olafajusamuel@live.com");
     /*
     //new SnosSendEmail(sender).SNOSemailSend("snos@teledominternational.net", "snostele01",sender,text +"  "+"\n PLEASE DO NOT REPLY THIS EMAIL!");
  String ma="SNOS5";admin@snosfortress.com     afam@teledominternational.net
String sms="Testing SNOS SOFTWARE the 3G or GSM remote monitoring and remote control system";
String subject="SNOS9";
String num="2347033949203";
//2347033949203
Reconsoft mail4 = new Reconsoft();

if(mail4.CheckExistingFoneNumber(num))
{
if(mail4.querySNOSRegisteredNumbers(num) ==null)
{
  JOptionPane.showMessageDialog(null, "No Registered number  for this"+mail4.querySNOSRegisteredNumbers(num));  
}
else{
    //new SnosSendEmail(ma).SNOSemailSend("snos@teledominternational.net", "snostele01", subject, sms);
JOptionPane.showMessageDialog(null, "This is the Registered for this  "+mail4.querySNOSRegisteredNumbers(num));
}
}else{
   JOptionPane.showMessageDialog(null, " UNRegistered  Number Information");  
 
}
*/
}

}