/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Admin
 */
import javax.swing.*;
import java.util.regex.*;
import java.text.*;
/**
 * This defines  all the application's necessary regular expressions and 
 * takes care of the total validation,comparison,checking and filtering of inputs 
 * entered in a field based on some particular pre-defined and proper patterns
 * @author Afam
 * @see AdminLogin.java, Change_user.java
 * @version 1.0 
 */
public class PatternCheck {
    private static final String GSMNO = "[234]{3}[7-8]{1}[0-9]{9}|[0-9]{11}";
    //private static final String USERNAME="([A-Z]{3,25}[ ]{1,5}[A-Z]{3,25}[ ]{0,5}[A-Z,.]{0,25})";
      private static final String USERNAME="([ ]{0,5}[A-Z]{2,50}[ ]{0,5}[A-Z]{0,25}[ ]{0,5}[A-Z,.]{0,25})";
    private static final String LOCATION="[A-Z,0-9,-_,;,.,[ ]{0,5}]{3,60}";
    private static final String SNOSLABEL = "[SNOS]{4}[ ]{0,3}[1-9]{1}[0-9]{0,4}";
    private static final String ARBITRARYFIELD = "[[ ]{0,3}[A-Z]{4,25}[ ]{0,4},0-9,.,;,-]{4,45}";
    private static final String sensorFIELD = "[A-Z,0-9,.,;,-,[ ]{0,5}]{7,50}";
    private static final String CODE = "[A-Z]{3}[ ][1-5]{1}[0-9]{2}";
    private static final String EMAIL="[a-z]{2}[a-z,0-9,_,.]{1,15}[@]{1}[a-z,0-9,_]{3,25}[.]{1}[a-z]{3}";
    private static final String CO_NAME_CITY="([A-Z,a-z,.;,-,[ ]{0,4}]{3,700})[.]{0,1}";
    private Matcher matcher;
    private Pattern pattern;
    /** Creates a new instance of PatternCheck */
    public PatternCheck() {
    }
    public boolean checkCode(String code)
    {
        pattern=Pattern.compile(CODE);
        matcher=pattern.matcher(code);
        if(matcher.matches())
        {
            display("Valid Coursecode");
            return true;
        }
        display("Invalid Coursecode");
        return false;
    }
    public boolean checkSnosLabel(String username,String label,String format)
    {
        pattern=Pattern.compile(SNOSLABEL,Pattern.CASE_INSENSITIVE);
        matcher=pattern.matcher(username);
        if(matcher.matches())
        {
            //display("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
     public boolean checkArbitraryField(String username,String label,String format)
    {
        pattern=Pattern.compile(ARBITRARYFIELD,Pattern.CASE_INSENSITIVE);
        matcher=pattern.matcher(username);
        if(matcher.matches())
        {
            //display("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    public boolean checkSensorField(String username,String label,String format)
    {
        pattern=Pattern.compile(sensorFIELD,Pattern.CASE_INSENSITIVE);
        matcher=pattern.matcher(username);
        if(matcher.matches())
        {
            //display("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    public boolean checkUserName(String username,String label,String format)
    {
        pattern=Pattern.compile(USERNAME,Pattern.CASE_INSENSITIVE);
        matcher=pattern.matcher(username);
        if(matcher.matches())
        {
            //display("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    
    
    
     public boolean checksetup_infor(String co_name2)
    {
        pattern=Pattern.compile(CO_NAME_CITY,Pattern.CASE_INSENSITIVE);
        matcher=pattern.matcher(co_name2);
        if(matcher.matches())
        {
           // System.out.println("surname is  good");
            return true;
        }
        //System.out.println("surname is  bad");
         return false;
    }
    
    
    
    public boolean checkLocation(String loc,String label,String format)
    {
        pattern=Pattern.compile(LOCATION,Pattern.CASE_INSENSITIVE);
        matcher=pattern.matcher(loc);
        if(matcher.matches())
        {
           // display("Valid "+label+". Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        display("Invalid "+label+". Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    public boolean checkEmail(String email,String label,String format)
    {
        pattern=Pattern.compile(EMAIL);
        matcher=pattern.matcher(email);
        if(matcher.matches())
        {
             //display("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
             return true;
        }
         display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
         return false;
    }
     public boolean checkGsm(String gsm,String label,String format)
    {
        pattern=Pattern.compile(GSMNO);
        matcher=pattern.matcher(gsm);
        if(matcher.matches())
        {
             //display("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
             return true;
        }
         display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
         return false;
    }
    
     public boolean checkYear(String input) {
        //pattern = Pattern.compile(YEAR);
        matcher = pattern.matcher(input);
        if (matcher.matches()){
            display ("Valid Year Number: Congratulations!!!.");
           return true;
        }else{
            display ("Invalid Year Number: Enter Valid Year Number.");
            return false; 
            
        }
    }
     
     public boolean checkNext(String input) {
       // pattern = Pattern.compile(YEAR);
        matcher = pattern.matcher(input);
        if (matcher.matches()){
           return true;
        }else{
            return false;  
        }
    }
     
   
   private void display (String msg){
       JOptionPane.showMessageDialog(null,msg,"INVALID INPUT",2);
   }
   
}


