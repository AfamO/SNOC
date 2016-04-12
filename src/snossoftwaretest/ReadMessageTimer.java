/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Admin
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
//import java.text
import java.util.Date;
/**
 * This defines the SWING GUI and the logic needed for setting up a proper timer and splash screen when the modem is being scanned and searched-for available ports- by the application at startup.
 * @author AFAM;
 * @see SmsMain.java,SnosSoftwareTest.java,
 * @version 1.0 
 */
 
public class ReadMessageTimer extends JFrame  {
    //new JProgressBar(0,395);
    private static JProgressBar progressBar = new JProgressBar(0,1163);
    //private  JWindow splashScreen1=new JWindow();
    private static int count = 0, TIMER_PAUSE = 50,PROGBAR_MAX=1163;
    private static Timer progressBarTimer;
    private Date date;
    private static ReadMessageTimer jpan;
    private static ReadMessageTimer jpan1;
    private long before;
    private long after;
    Container container = getContentPane();
    Container container1 =getContentPane();
    ActionListener al = new ActionListener() {
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count++);
            //progressBar.setIndeterminate(true);
            //splashScreen1=
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            //System.out.println(count);
            if (ReadMessages1.getIsMessageRed()) {
                
                 progressBarTimer.stop();
                 //splashScreen1.dispose();
                // this.setVisible(false);
                 //this.setVisible(false);
                SNOSSoftwareTest.getTimerObj().setVisible(false);
                 //jpan.setVisible(false);
                 container1.remove(container);
                 progressBar.setValue(progressBar.getMinimum());
                 progressBar.setString("");
                 Toolkit.getDefaultToolkit().beep();
                   MyRunnable t=new MyRunnable("scanmodem");
             t.start();
             //javax.swing.JOptionPane.showMessageDialog(null, "Modem Scanning has just begun");
                //dispose of splashscreen
                //javax.swing.JOptionPane.showMessageDialog(null, "thank you,completed");
                //stop the timer
                SNOSSoftwareTest.getObj().setVisible(true);
            }
            if(ReadMessages1.getIsNotModemPortsAvailable()==true ||SmsMain.getIsTimerStopped())
            {
                progressBarTimer.stop();
                Toolkit.getDefaultToolkit().beep();
            }
            count++;//increase counter

        }
    };
  public ReadMessageTimer()
  {
     //date=new Date(); 
     //before=date.getTime();
     createInit();
     //SNOSSoftwareTest.Mymain();
     
  }
 
  public void CalculateTime()
  {
      after=date.getTime();
      System.out.println("Before is "+before);
      System.out.println("Date-Time is "+date.toLocaleString());
      System.out.println("Seconds is "+date.getSeconds());
      System.out.println("Year is "+date.getYear());
      System.out.println("Hour is "+date.getHours());
      System.out.println("Minute is "+date.getMinutes());
      System.out.println("After is "+after);
      
  }
  public static void closeFrame()
  {
      jpan.setVisible(false);
  }
   private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
       // System.out.println("The DELAY IS "+ progressBarTimer.getDelay());
        progressBarTimer.setActionCommand("WHO ARE YOU");
        
    }
   public void setSplashScreen(ReadMessageTimer t)
   {
       jpan1=t;
   }
    public  static ReadMessageTimer getSplashScreen()
   {
       return jpan1;
   }
   private void createInit() {
   JPanel panel = new JPanel();
   //  final ImageIcon icon =  new ImageIcon(new File("").getAbsolutePath()+"\\src\\snossoftwaretest\\probar.jpg");
   final ImageIcon icon2 = new ImageIcon(getClass().getResource("/snossoftwaretest/probar.jpg"));
          
       // panel.setBorder(new javax.swing.border.EtchedBorder());
        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 0, 153), new java.awt.Color(255, 255, 0)));
        panel.setBackground(new java.awt.Color(240, 169, 98));
        
           JLabel iconL = new JLabel();
           iconL.setBounds(0, 0, 200, 120);
           iconL .setIcon(icon2);
          
         // iconL .setIcon(new ImageIcon(getClass().getResource("/snossoftwaretest/probar.jpg")));
        
       // final ImageIcon icon = new ImageIcon(getClass().getResource("/snossoftwaretest/probar.jpg"));
        JLabel lab1 = new JLabel("AUTHENTICATING MODEM...!");
        lab1.setBounds(0,125, 250,50);
        lab1.setFont(new Font("Verdana", Font.BOLD, 15));
        lab1.setForeground(Color.BLUE);
        
        
         JLabel la = new JLabel("Please wait!!!...");
        la.setBounds(0,125,150,30);
        la.setFont(new Font("Verdana", Font.ITALIC, 14));
        la.setForeground(new java.awt.Color(204, 0, 204));
        
        panel.add(iconL);
        panel.add(lab1);
        panel.add(la);
        
        container.add(panel, BorderLayout.CENTER);
        
        //panel.setBackground(Color.yellow);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setMaximum(PROGBAR_MAX);
        progressBar.setBorderPainted(true);
        //progressBar.setBackground(Color.BLUE);
        container.add(progressBar, BorderLayout.SOUTH);
        //jpan=panel;
        setSize(400,230);
       // pack();
        setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(JFrame.ICONIFIED);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setTitle("TELEDOM SNOC SOFTWARE");
        setVisible(true);
        startProgressBar();
    }
/*
  public static void main(String args[])
  {
       ReadMessageTimer t;
      //splashScreen=t;
        jpan = new  ReadMessageTimer();
       jpan.setSplashScreen(jpan);
      //jpan=t;
       
       
  }
  */

    
}

