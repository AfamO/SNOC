package snossoftwaretest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Charles
 */
import java.util.*;
import java.util.List;
import java.sql.*;
import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import javacardx
//import org.apache.commons.lang.*;
//import org.apache.commons.lang.StringEscapeUtils;


//import org.apache.common
import javax.imageio.ImageIO;
import org.apache.commons.dbutils.DbUtils;
/**
 * This defines all the methods needed to perform 
 * all the database access of the application 
 * especially CRUD(create, retrieve, update and delete).
 * @author AFAM, Charles;
 * @see SmsMain.java,SnosSoftwareTest.java,
 * @version 1.0 
 */
public class Reconsoft {
    //private URL u=new URL("index.html");
    private Statement statement;
    private Connector1 dbconnector;
    private Connection rdbconn;
    private Vector row;
    private String msgout;
    private String[] record;
    private String n1,n2,n3;
    private UserInforGet getuser;
    private Properties snosprop=new Properties();
    private static Properties RegisterdUserprop=new Properties();
    private Properties usersensorprop=new Properties();
    private List sensorList=new ArrayList<String>();
    private List clients_contactsList=new ArrayList<String>();
    private List clients_contactsId=new ArrayList<String>();
    private String [] mail;
    private String convertTosnos;
    public static Boolean IsRemoteConnSuccessful=false;
     private String [] customers,mobile_info;

    
    /** Creates a new instance of ProjectDatabase */ 
      
    public Reconsoft()
    {
        dbconnector = new Connector1 ();
    }
    public boolean AddSmsInfor(String snos_type,String msg,String date,String status){ 
        boolean Isent=false;
      if(snos_type.equalsIgnoreCase("NULL")||msg.equalsIgnoreCase("NULL")||date.equalsIgnoreCase("NULL"))
      {
          JOptionPane.showMessageDialog(null,"You can't send a null sms message to database!\nThank you");
      }
      else
      {
           rdbconn=dbconnector.RemotedatabaseConnect();
            try {
            
            statement = rdbconn.createStatement();
                       
            String query = "INSERT INTO sms_in" +
            "(snos_type,msg,re_time,status)" +
             "VALUES('" + snos_type + "','" + msg+ "','" + date +"','" + status +"')";
             int result = statement.executeUpdate( query );
             //if(result)
             //{
                 
             //}
             Isent=true;
             statement.close();
        }catch ( SQLException sqlex ) {
                this.display("Cannot push your data to online database,\nmake sure that your INTERNET connection is  STRONG  OR that it is still established","ABORTED REMOTE CONNECTION");
                Isent=false;
                sqlex.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.rdbconn);
        }
           
      }
      
           
        return Isent;
        
       
    }
       public void AddSmsInforTOlOCALDB(String snos_type,String msg,String date,String status){ 
      
           
          dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();           
            String query = "INSERT INTO sms_in" +
            "(snos_type,msg,re_time,status)" +
             "VALUES('" + snos_type + "','" + msg+ "','" + date +"','" + status +"')";
             int result = statement.executeUpdate( query );
             
             
             //if(num == 1)
                //newForm.myOtherSave();
             //else
                 //redForm.myOtherSave();
             //statement.close();
        }catch ( SQLException sqlex ) {
                //this.display("Cannot push your data to online database,\nmake sure that your INTERNET connection is  STRONG  OR that it is still established","ABORTED REMOTE CONNECTION");
                sqlex.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
           
       
        
        
       
    }
      public void display( String msg,String label ) {
        //JOptionPane.showMessageDialog(null, msg,);
         JOptionPane.showMessageDialog(null,msg,label,2);
    }
    public void AddActionInfor(String snos_type,String msg,String action_type,String contacts_id,String msgSent,String date1,boolean local){ 
       
           rdbconn=dbconnector.RemotedatabaseConnect();
            try {
            
            statement = rdbconn.createStatement();
                       
            String query;
            query = "INSERT INTO action_tab" +
     "(snos_type,sensor,action_type,contacts_id,text_sent,date1)" +
      "VALUES('" + snos_type + "','" + msg + "','" + action_type+ "', '" + contacts_id+ "','" + msgSent+ "','" + date1 +"')";
             int result = statement.executeUpdate( query );
             //statement.close();
        }
        catch ( SQLException sqlex ) 
        {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
            
    }
       
      public int AddClientInfo(UserInforGet d){ 
        dbconnector.databaseConnect();
        int res=0;
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO snos_client" +
            "(Surname,firstname,Client_name,fone,loco,state,lga,snos_type,Client_email,password)" +
             "VALUES('" +d.getsurname() + "','" + d.getFname()+ "','" + d.getOname()+ "','" + d.getFone()+ "','" + d.getLoc() + "', '" + d.getState() + "','" + d.getLga()
                    + "','" + d.getIdentity() + "','" + d.getClientEmail()+ "','" + d.getClientPassword() + "')";
             int result = statement.executeUpdate( query );
             res=1;
             statement.close();
        }catch ( SQLException sqlex ) 
        {
                JOptionPane.showMessageDialog(null, sqlex.toString());
                
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        return res;
    }
    /**
     *
     * @param d
     */
      public UserInforGet getClientDetails1(String sensor,String table) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();


      try {
            statement = dbconnector.dbconn.createStatement();

            String query = "SELECT * FROM "+table+" WHERE snos_type = '" + sensor + "'" ;
            ResultSet rs = statement.executeQuery(query);

            rs.next();
            getuser.SetSensor(rs.getString("snos_type"));
            getuser.SetFone(rs.getString ("fone"));
            getuser.SetFname(rs.getString ("firstname"));
            getuser.SetContact(rs.getString ("loco"));
            getuser.SetLga(rs.getString("lga"));
            getuser.SetSname(rs.getString ("Surname"));
            getuser.SetOname(rs.getString("Client_name"));
            getuser.SetState(rs.getString ("state"));
            getuser.SetClientEmail(rs.getString ("Client_email"));
            getuser.SetPass(rs.getString ("password"));


           //getuser.SetLga(rs.getString (10));
           //getuser.SetIdentity(rs.getString (11));
            dbconnector.dbconn.close();
            return getuser;
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,exc.toString( ));
            return getuser;
         }
         //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }

   }
      public int UpdateClientDetails(String sensor,UserInforGet uig) {
       getuser = new UserInforGet();//table,field,item,colu;
       String id=RetrieveItem("snos_client", "snos_type", sensor, "cid");
       dbconnector.databaseConnect();
        
        int resu=0;
       int res=0;
      try {
          if(id.equals("")){
               
               throw new SQLException("The id info from DB is null, thus cannot continue.");
           }
          else{
            statement = dbconnector.dbconn.createStatement();

            String query = "UPDATE snos_client SET Surname='"+uig.getsurname()+"',firstname='"+uig.getFname()+"',Client_name='"+uig.getOname()+"',fone='"+uig.getFone()+"',loco='"+uig.getContact()+"',lga='"+uig.getLga()+"',state='"+uig.getState()+"',snos_type='"+uig.getIdentity()+"',Client_email='"+uig.getClientEmail()+"'  WHERE cid = '" + id + "'" ;
           res=statement.executeUpdate(query);
           resu=1;
          }
      }
      catch (SQLException exc) {
              
            JOptionPane.showMessageDialog(null,exc.toString( ));
           
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
     
      return resu;
   }
      public Vector getClientDeviceDetails(String sensor,String table) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
         row = new Vector ();

      try {
            statement = dbconnector.dbconn.createStatement();

            String query = "SELECT * FROM "+table+" WHERE snos_type = '" + sensor + "'" ;
            ResultSet rs = statement.executeQuery(query);

           while(rs.next())
           {
                getuser.SetClient(rs.getString (3));
                getuser.SetDescrpt(rs.getString (4));
                getuser.SetBuild(rs.getString (5));
                getuser.SetLoc(rs.getString (6));
                getuser.SetContact(rs.getString (7));
                getuser.SetLga(rs.getString (8));
                getuser.SetState(rs.getString (9));
                record=new String[] {getuser.getClient(),getuser.getDecrpt(),getuser.getBuild(),getuser.getLoc(),getuser.getContact(),getuser.getLga(),getuser.getState(),rs.getString(1)};

                row.add(record);
           }
           
            dbconnector.dbconn.close();
            return row;
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,exc.toString( ));
            return row;
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }

   }
      public int UpdateDeviceDetails(String sensor,UserInforGet uig,String id) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
        int resu=0;

      try {
            statement = dbconnector.dbconn.createStatement();

            String query = "UPDATE clients_device_tab SET name='"+uig.getClient()+"',descrpt='"+uig.getDecrpt()+"', housing_type='"+uig.getBuild()
                    +"', housing_location='"+uig.getLoc()+"',address='"+uig.getContact()+"',lga='"+uig.getLga()+"',state='"+uig.getState()
                    +"'  WHERE snos_type = '" + sensor + "' and id='" + id + "'" ;
           int res=statement.executeUpdate(query);
           resu=1;
           statement.close();
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,exc.toString( ));

         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
     return  resu;
   }
      public int UpdateClientsContactsDetails(String sensor,UserInforGet uig,String id,String sms_type, String mail_type) {
       getuser = new UserInforGet();
       int resu=0;
        dbconnector.databaseConnect();


      try {
            statement = dbconnector.dbconn.createStatement();

            String query = "UPDATE clients_contacts_tab SET name='"+uig.getClient()+"',relation_position='"+uig.getRelation_Position()+"', email1='"+uig.getClientEmail()
                    +"', gsm='"+uig.getFone()+"',address='"+uig.getContact()+"',sms_type='"+sms_type+"',mail_type='"+mail_type+"'  WHERE snos_type = '" + sensor + "' and contacts_id='" + id + "'" ;
           int res=statement.executeUpdate(query);
            resu=1;
            statement.close();
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,exc.toString( ));

         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
return resu;
   }
      public Vector getClientContactsDetails(String sensor,String table) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
        row = new Vector ();  

      try {
            statement = dbconnector.dbconn.createStatement();

            String query = "SELECT * FROM "+table+" WHERE snos_type = '" + sensor + "'" ;
            ResultSet rs = statement.executeQuery(query);

           while(rs.next())
           {
               
            getuser.SetClient(rs.getString (3));
            getuser.SetRelation_Position(rs.getString (4));
            getuser.SetContact(rs.getString (5));
            getuser.SetClientEmail(rs.getString (6));
            getuser.SetFone(rs.getString (7));
            if(table.equals("clients_contacts_temp_tab"))
            {
                  getuser.SetSensor(rs.getString(2));
                  record=new String[] {getuser.getClient(),getuser.getRelation_Position(),getuser.getContact(),getuser.getClientEmail(),getuser.getFone(),rs.getString("sms_type"),rs.getString("mail_type"),rs.getString("contacts_id")};
            }
            else
            {
               record=new String[] {getuser.getClient(),getuser.getRelation_Position(),getuser.getContact(),getuser.getClientEmail(),getuser.getFone(),rs.getString("sms_type"),rs.getString("mail_type"),rs.getString("contacts_id")};

            }
            
           row.add(record);
           }
           statement.close();
           dbconnector.dbconn.close();
            
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,exc.toString( ));
            return row;
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
         return row;
   }

    public void AddClient_Contacts(UserInforGet d){ 
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO snos_client" +
            "(contacts_id,snos_type,name,relation_position,address,email1,gsm)" +
             "VALUES('" +d.getSensor() + "','" + d.getClient()+ "','" + d.getRelation_Position()+
                    "','" + d.getLoc() + "','" + d.getClientEmail()+ "','" + d.getFone()+ "')";
             int result = statement.executeUpdate( query );
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }  
    
    
    /*****************************************************************************
   * Store Setup Image into database
   *****************************************************************************/
    
   public void Addsetup_Inform(String name2,String city,File log2)
   {
        FileInputStream logo;

        dbconnector.databaseConnect();
        try {
            statement = dbconnector.dbconn.createStatement();
            logo = new FileInputStream(log2);


            String sql = "INSERT INTO setup_tab (snoc_name,so_city,logo) VALUES (?,?,?)";
            PreparedStatement stmt = dbconnector.dbconn.prepareStatement(sql);

            stmt.setString(1, name2);
            stmt.setString(2, city);
            stmt.setBinaryStream(3, (InputStream) logo, (int) log2.length());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Customization Information Submitted succssfully\n Click Exit Button and Restart the Software From Desktop.");

            stmt.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }

    }
    
   
   
   public void Update_setup_Inform(String name2,String city,File log2)
   {
        FileInputStream logo;
      
        dbconnector.databaseConnect();
        try {
            statement = dbconnector.dbconn.createStatement();
            logo = new FileInputStream(log2);

           // String sql = "INSERT INTO setup_tab (,,) VALUES (?,?,?)";
            String query = "UPDATE setup_tab SET snoc_name=? ,so_city=? ,logo=? WHERE sid =1" ;
            PreparedStatement stmt = dbconnector.dbconn.prepareStatement(query);

            //stmt.setString(0, supid);
            stmt.setString(1, name2);
            stmt.setString(2, city);
            stmt.setBinaryStream(3, (InputStream) logo, (int) log2.length());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Customization Information updated succssfully\n Click Exit Button and Restart the Software From Desktop.");

            stmt.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }

    }
    
    
    
      public int AddOprator(UserInforGet d){ 
        dbconnector.databaseConnect();
        int res=0;
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO admin" +
            "(user_name,user_id,password,status)" +
             "VALUES('" +d.getOperaName() + "','" + d.getUserid()+ "','" + d.getPass()+ "','" + d.getStatus()  + "')";
             int result = statement.executeUpdate( query );
             JOptionPane.showMessageDialog(null, "Remeber that result="+result);
             res=1;
                statement.close();
               
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        return res;
    }
      public boolean CheckregisteredID(String id,String pass,String sta){
        
     dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM admin WHERE user_id = '" +
            id + "'" + "AND password = '" +pass + "' AND status = '" + sta + "'" ;
            ResultSet rs1 = statement.executeQuery( query );


            while (rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    } 
    
    
    public String myID(String userid,String pass,String sta){
       
        dbconnector.databaseConnect();
        try {
            
            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM admin WHERE user_id = '" +
            userid + "'" + "AND password = '" +pass + "' AND status = '" + sta + "'" ;
            ResultSet rs1 = statement.executeQuery( query );
            
           rs1.next();
           String id = rs1.getString (2);
           dbconnector.dbconn.close();
            return id;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return "";
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    } 
    
  
    
   public String Showmystatus(String userid){
       
        dbconnector.databaseConnect();
        try {
            
            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT status FROM admin WHERE user_id = '" +
            userid + "'" ;
            ResultSet rs1 = statement.executeQuery( query );
            
           rs1.next();
           String id = rs1.getString("status");
           statement.close();
           dbconnector.dbconn.close();
            return id;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return "";
        }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }  
    }   
    
    
    
    
    
 public void updateAdmin(String name,String userid,String pass,String status) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE admin SET password = '" + pass +"',status= '"+status+ "',user_name= '"+name+ "'" +
            "WHERE user_id = '" + userid + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }   
    
      
      public void AddSensor(UserInforGet d){ 
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO sensor_table" +
            "(sensor)" +
             "VALUES('" +d.getSensor() + "')";
             int result = statement.executeUpdate( query );
             
                statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    } 
      public void AddSensor(String snos_type,String mess,String sensor){ 
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO user_sensor_table" +
            "(snos_type,message,sensor)" +
             "VALUES('" +snos_type + "','" +mess+ "','" + sensor + "')";
             int result = statement.executeUpdate( query );
             
                statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    } 
      
      
public void AddClient_Contacts(String snos_type,String name,String rela,String add,String mail,String gsm,String smsformat,String mailformat){ 
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO clients_contacts_tab" +
            "(snos_type,name,relation_position,address,email1,gsm,sms_type,mail_type)" +
             "VALUES('" +snos_type + "','" + name+ "','" + rela+ "','" + add + "','" + mail+ "','" + gsm+ "','" +smsformat+ "','" +mailformat+ "')";
             int result = statement.executeUpdate( query );
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    } 
public int AddDeviceInfo(UserInforGet d,String table){
        dbconnector.databaseConnect();
int res=0;
        try {
            PreparedStatement ps=dbconnector.dbconn.prepareStatement("");
            

            statement = dbconnector.dbconn.createStatement();

            String query = "INSERT INTO "+table+
            "(snos_type,name,descrpt,housing_type,housing_location,address,lga,state)" +
             "VALUES('" +d.getSensor()+ "','" + d.getClient()+ "','" + d.getDecrpt()+ "','" + d.getBuild() + "','" + d.getLoc()
                    + "','" + d.getContact()+ "','" + d.getLga()+ "','" + d.getState()+ "')";
             int result = statement.executeUpdate( query );
           res=1;
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
                sqlex.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        return res;
    }

      
      public void AddSnos(UserInforGet d){ 
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO snos_table" +
            "(snos_type,fone)" +
             "VALUES('" +d.getSensor() + "','" +d.getFone() + "')";
             int result = statement.executeUpdate( query );
             
                statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    } 
     
      public int UpdateSnosId(String oldsnos_type,String newsnos_type,String fone){ 
          String tid= RetrieveItem("snos_table", "snos_type",oldsnos_type,"tid");
          String cid= RetrieveItem("snos_client", "snos_type",oldsnos_type,"cid");
          dbconnector.databaseConnect();
        int res,res1,resu=0;
        try {
            
            statement = dbconnector.dbconn.createStatement();
                   
         String query = "UPDATE snos_table SET snos_type='"+newsnos_type+"',fone='"+fone+"' WHERE tid = '" + tid + "'" ;
         String query1 = "UPDATE snos_client SET snos_type='"+newsnos_type+"',fone='"+fone+"' WHERE cid = '" + cid + "'" ;
         res  =statement.executeUpdate(query);
         res1 =statement.executeUpdate(query1);
        
         resu=1;
             
        statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        return resu;
    } 
      
      
      public UserInforGet SNOSUSER(String sensor) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM snos_client WHERE snos_type = '" + sensor + "'" ;
            ResultSet rs = statement.executeQuery(query);
            
            rs.next();
            // Name,fone,contact,loco,area,bu,state,lga,ident
            getuser.SetClient(rs.getString (2));
            getuser.SetFname(rs.getString("firstname"));
             getuser.SetSname(rs.getString("Surname"));
            getuser.SetFone(rs.getString (3));
            getuser.SetContact(rs.getString (4));
            getuser.SetLoc(rs.getString (5));
            dbconnector.dbconn.close();
            return getuser;
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            return getuser;
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
   }
      public Properties getALLSNOS() {
          String registered_snos=null;
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM snos_table" ;
            ResultSet rs = statement.executeQuery(query);
            RegisterdUserprop=this.getALLREGISTEREDSNOS();
           while(rs.next())
           {
               snosprop.put(rs.getString (3),rs.getString (2));
           }
           dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
      return snosprop;
        
   }
      public Properties getALLREGISTEREDSNOS() {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM snos_client" ;
            ResultSet rs = statement.executeQuery(query);
            
           while(rs.next())
           {
                RegisterdUserprop.put(rs.getString ("fone"),rs.getString ("snos_type"));
                System.out.println("The first REGISTERED column is "+rs.getString(3));
                System.out.println("The first REGISTERD column is "+rs.getString(10));
                
           }
           dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
      return RegisterdUserprop;
        
   }
      public Boolean CheckIfItemExist(String table,String field,String item)
      {
          boolean IsItemExist=false;
           dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = " select * FROM "+table+" where "+field+" ='"+item+"' " ;
              ResultSet rs = statement.executeQuery(query);
              if(rs.next())
              {
                  IsItemExist=true;
              }
              else
              {
                  IsItemExist=false;
              }
           
            dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
     return IsItemExist;
          
      }
      public String RetrieveItem(String table,String field,String item,String Col)
      {
          String Item="";
         
             dbconnector.databaseConnect();
            try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = " select * FROM "+table+" where "+field+" ='"+item+"' " ;
              ResultSet rs = statement.executeQuery(query);
              if(rs.next())
              {
                  Item=rs.getString(Col);
              }
              else
              {
                  //IsItemExist=false;
              }
          
            dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         } 
         //closes all possible connection to database
         finally
         {
             DbUtils.closeQuietly(statement);
             DbUtils.closeQuietly( dbconnector.dbconn);
         }
     return Item;
          
      }
      public List getClients_Contacts_Email (String snos_type){
          
        dbconnector.databaseConnect();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = " select * FROM  clients_contacts_tab where snos_type ='"+snos_type+"'" ;
            ResultSet rs = statement.executeQuery(query);
              while(rs.next())
              {
                 String email1=rs.getString("email1");
                  clients_contactsList.add(email1);
              }
             
           
            dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            exc.printStackTrace();
            
         }
         //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        return clients_contactsList;
   }  
      /**
     *
     * @param snos_type
     * @param email1
     * @return
     */
    public List RetrieveClient_Contacts_Id(String snos_type)
      {
          String Item="";
          //if(this.CheckIfItemExist(table, field, item) || this.CheckExistingSNOS(item))
         // {
             dbconnector.databaseConnect();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            clients_contactsList=this.getClients_Contacts_Email(snos_type);
            for(int k=0;k<clients_contactsList.size();k++)
            {
                Item=this.RetrieveItem("clients_contacts_tab", "email1",(String)clients_contactsList.get(k),"contacts_id");
                clients_contactsId.add(Item);
            }
            
           dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            exc.printStackTrace();
         } 
         //closes all possible connection to database
         finally
         {
             DbUtils.closeQuietly(statement);
             DbUtils.closeQuietly( dbconnector.dbconn);
         }
           
     return clients_contactsId;
          
      }
      public void  DeleteREGISTEREDSNOS(String snos_type) {
        dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = " delete FROM snos_table where snos_type='"+snos_type+"' " ;
              int executeUpdate = statement.executeUpdate(query);
           
            dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
      
        
   }
      public Properties getALLUSERSENSORS() {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM user_sensor_table" ;
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next())
            {
                usersensorprop.setProperty(rs.getString (2),rs.getString (3));
            }
            dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        return usersensorprop;
   }
      public List getALLSENSORS() {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM sensor_table" ;
            ResultSet rs = statement.executeQuery(query);
            
           while(rs.next())
           {
               sensorList.add(rs.getString (2));
           }
           dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
      return sensorList;
        
   }
      

      //get client Information from database
      
      public UserInforGet ClientDetails(String sensor) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
     
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM snos_client WHERE snos_type = '" + sensor + "'" ;
            ResultSet rs = statement.executeQuery(query);
            
            rs.next();
            
            getuser.SetClient(rs.getString (2));
            getuser.SetFone(rs.getString (3));
            getuser.SetContact(rs.getString (4));
            getuser.SetLoc(rs.getString (5));
           // getuser.SetArea(rs.getString (5));
            getuser.SetBuild(rs.getString (7));
            getuser.SetState(rs.getString (8));
           getuser.SetLga(rs.getString (9));
           getuser.SetIdentity(rs.getString (10));
            dbconnector.dbconn.close();
            return getuser;
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            return getuser;
         }
      //closes all possible connections to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
   }

     public UserInforGet MessageInfor(String snos_type) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
      
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM sms_in WHERE snos_type = '" + snos_type + "'" ;
            ResultSet rs = statement.executeQuery(query);
            
            rs.next();
          
            getuser.SetMessage(rs.getString(3));
            dbconnector.dbconn.close();
            return getuser;
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            return getuser;
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
   }
 
      
      
     //***************************************************************************************
      // Error Checking methods for different things are below
      //**************************************************************************************
       
       
       public boolean ChecksetupInform(){
        
     dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM setup_tab where sid='1'";
            
            ResultSet rs1 = statement.executeQuery( query );
            statement.close();

            while (rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    } 
       
      
     public boolean CheckExistingMobile_info(String sender){
       // JOptionPane.showMessageDialog (null,matric+" "+sem+" "+sec);
       rdbconn=dbconnector.RemotedatabaseConnect();
        try {

            statement =rdbconn.createStatement();
            String query = "SELECT * FROM  phone_info WHERE snos_type = '" +
            sender + "'" ;
            ResultSet rs1 = statement.executeQuery( query );
            statement.close();

            if(rs1.next()) {
                return true;
            }

          rdbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.rdbconn);
        }
        
    }
     public String[] queryListPhone_Info(String snos_type) {

        rdbconn=dbconnector.RemotedatabaseConnect();
        try {

            statement =rdbconn.createStatement();


            String query = "SELECT device_id FROM phone_info WHERE snos_type = '" + snos_type + "'";

            ResultSet rs = statement.executeQuery(query);
            int num = 0;
            while (rs.next()) {
               // Get the device's push id;
                String device_id = rs.getString("device_id");
               

                mobile_info= new String[]{device_id};
                statement.close();
            }
           rdbconn.close();


        } catch (SQLException exc) {

            JOptionPane.showMessageDialog(null, "NO Mobile Phone Information Found");
            return null;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.rdbconn);
        }
        return mobile_info;
    }
     
     
 public String[] queryListAllPhone_Info() {

        rdbconn=dbconnector.RemotedatabaseConnect();
        try {

            statement =rdbconn.createStatement();

            String query = "SELECT device_id FROM phone_info ";

            ResultSet rs = statement.executeQuery(query);
            int num = 0;
            while (rs.next()) {
               // String pushid = rs.getString("push_id");
                String device_id = rs.getString("device_id");
                mobile_info= new String[]{device_id};
                statement.close();
            }
            rdbconn.close();


        } catch (SQLException exc) {

            JOptionPane.showMessageDialog(null, "NO Mobile Phone Information Found");
            return null;

        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.rdbconn);
        }
        return mobile_info;
    }    
     

    public boolean SNOSCheckExistingRecord(String sender) {
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM sms_in WHERE Sender = '"
                    + sender + "'";
            ResultSet rs1 = statement.executeQuery(query);


            if (rs1.next()) {
                return true;
            }

            dbconnector.dbconn.close();

            return false;

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, exc.toString());
            return false;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }

    }

      public void querysmsList(){
   
          dbconnector.databaseConnect();
          row = new Vector ();   
          try {
          
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT *  FROM sms_in" ;
           ResultSet rs = statement.executeQuery(query);
            int num=0;
            while (rs.next()) {
                
                String Sender = rs.getString (2); 
                String Tex = rs.getString (3).toUpperCase();     
                String date1 =rs.getString (4);
                record=new String[] {Sender,Tex,date1,"unread"};
                //System.out.println(record);
               // System.out.println(record.length);
                row.addElement(record);
               //databaseinforList = new Object[][]{record};
               num ++;
                
               }
            dbconnector.dbconn.close();
          }catch (SQLException exc) {
           
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
         //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
      
  }
      public int CountRegisteredSNOS() {
        dbconnector.databaseConnect();
        try {
                statement = dbconnector.dbconn.createStatement();
                String query = "SELECT * FROM snos_table " ;
                ResultSet rs1 = statement.executeQuery(query);
                int num = 0;
                String ans = "yes";
                while (ans.equals("yes")){
                    num ++;
                    if(rs1.next()){
                        ans = "yes";
                    }else{
                        ans = "n";
                        return num - 1; 
                    }
                }
                
                return 0;
          }catch (SQLException exc) {
          JOptionPane.showMessageDialog(null,exc.toString( ));
            return 0;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
      public int CountSensors() {
        dbconnector.databaseConnect();
          try {
                statement = dbconnector.dbconn.createStatement();
                String query = "SELECT * FROM sensor_table " ;
                ResultSet rs1 = statement.executeQuery(query);
                int num = 0;
                String ans = "yes";
                while (ans.equals("yes")){
                    num ++;
                    if(rs1.next()){
                        ans = "yes";
                    }else{
                        ans = "n";
                        return num - 1; 
                    }
                }
                
                return 0;
          }catch (SQLException exc) {
          JOptionPane.showMessageDialog(null,exc.toString( ));
            return 0;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
     
     
     
    public String[] queryListofRegisteredSnos() {
        dbconnector.databaseConnect();
        try {
                statement = dbconnector.dbconn.createStatement();

                String query = "SELECT * FROM snos_table " ;
                ResultSet rs = statement.executeQuery(query);
                int num = 0;
                num = CountRegisteredSNOS();
                num = num + 1;
                String record[] = new String[num];
                record[0] = "Choose one";
                for (int i = 1; i < num; i++){
                    rs.next();
                    record[i] = (rs.getString(2));
                    if (i == num -1){
                        return record;
                    }
                }
                statement.close();
                return record;

          }catch (SQLException exc) {
           
           JOptionPane.showMessageDialog(null,exc.toString( ));
            String[] record = {"No Information"};
            return record;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }

     public String[] queryListofSensors() {
       dbconnector.databaseConnect();
       try {
           statement = dbconnector.dbconn.createStatement();

                String query = "SELECT * FROM sensor_table " ;
                ResultSet rs = statement.executeQuery(query);
                int num = 0;
                num = CountSensors();
                num = num + 1;
                String record[] = new String[num];
                record[0] = "Choose one";
                for (int i = 1; i < num; i++){
                    rs.next();
                    record[i] = (rs.getString(4));
                    if (i == num -1){
                        return record;
                    }
                }
                return record;

          }catch (SQLException exc) {
           
           JOptionPane.showMessageDialog(null,exc.toString( ));
            String[] record = {"No Information"};
            return record;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    } 
public void querySNOSType(String para){
   
          dbconnector.databaseConnect();
          row = new Vector ();   
          try {
          
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT a.snos_type,a.msg,a.re_time,b.Client_name,b.loco,b.fone  FROM sms_in a,snos_client b WHERE a.snos_type = '" 
                    + para +  "'" + "AND b.snos_type = '" + para + "'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
            while (rs.next()) {
                
                String Sender = rs.getString ("snos_type"); 
                String Tex = rs.getString ("msg").toUpperCase();
                String date1 =rs.getString ("re_time"); 
                String Client=rs.getString("Client_name");
                String address=rs.getString("loco");
                String fon=rs.getString("fone");
                record=new String[] {Sender,Tex,date1,Client,address,fon};
                
                row.addElement(record);
                 
               }
               statement.close();
            
            dbconnector.dbconn.close();
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
  } 
    public void queryActionTaken_List(){
   
          dbconnector.databaseConnect();
          row = new Vector ();   
          try {
          
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT *  FROM action_tab" ;
           ResultSet rs = statement.executeQuery(query);
            int num=1;
            while (rs.next()) {
                
                String customer = rs.getString ("snos_type"); 
                String sensor_mess = rs.getString ("sensor").toUpperCase();
                String act_type = rs.getString ("action_type"); 
                String text = rs.getString ("text_sent"); 
                String act_date = rs.getString ("action_date"); 
    
                String count=Integer.toString(num);
                        
                
                record=new String[] {count,customer,sensor_mess,act_type,text,act_date};
                
                row.addElement(record);
               
              num ++;
                
               }
            dbconnector.dbconn.close();
          }catch (SQLException exc) 
          {
            
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
         //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
  }
  public ArrayList<String []> queryCustomersLIST_ACTION_Taken(String snos_type){
   
          dbconnector.databaseConnect();
          ArrayList<String []> cous = new ArrayList<String[]>();
      
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
           String query = "SELECT * FROM action_tab WHERE snos_type = '"  + snos_type +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                   int num =0;  
               while (rs.next()) {
               
                String sensor_mess = rs.getString ("sensor").toUpperCase();
                String act_type = rs.getString ("action_type"); 
                String text = rs.getString ("text_sent"); 
                String act_date = rs.getString ("action_date"); 
              // String count=Integer.toString(num);
                     customers=new String[] {sensor_mess,act_type,text,act_date};
                     cous.add(customers);
                     num++;
               }
               dbconnector.dbconn.close();
                
          
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"NO ACTION TAKEN Found");
             return null;
           
         }
         //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
       return  cous;
  }
  public ArrayList<String []> queryCustomersDAILY_LIST_ACTION_Taken(String date1){
   
          dbconnector.databaseConnect();
          ArrayList<String []> cous = new ArrayList<String[]>();
      
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
     String query = "SELECT * FROM action_tab WHERE action_date like'%"+ date1+"%'";
    // like'%"+para+"%' 
            
           ResultSet rs = statement.executeQuery(query);
                   int num =0;  
               while (rs.next()) {
               
                String cos_id = rs.getString ("snos_type"); 
                String sensor_mess = rs.getString ("sensor").toUpperCase();
                String act_type = rs.getString ("action_type"); 
                String text = rs.getString ("text_sent"); 
                String act_date = rs.getString ("action_date"); 
                customers=new String[] {cos_id,sensor_mess,act_type,text,act_date};
                cous.add(customers);
                num++;
               }
               statement.close();
               dbconnector.dbconn.close();
                
          
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"NO ACTION TAKEN Found");
             return null;
          }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
       return  cous;
  }  
    
    
    //***********************************************
       //select customers email from the database
    ///*************************************************
      
    public String querySNOSRegisteredNumbers(String numb){
   
          dbconnector.databaseConnect();
     
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
            String query = "SELECT snos_name FROM snos_table WHERE fone = '"  + numb +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                     
              rs.next();
              
                String snosnumber = rs.getString ("snos_name"); 
                 //dbconnector.dbconn.close();
                
                  if(snosnumber.equals(""))
                   {
                       convertTosnos=null;
                       return convertTosnos;
                   }else{
                     convertTosnos=snosnumber;
                      return convertTosnos;  
                   }
                
                
                
          
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"SNOS Not Found");
             return convertTosnos;
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
  }  
      
      //***********************************************
       //select customers email from the database
    ///*************************************************
      
    public String [] querySNOSEmailContacts(String mail2){
   
          dbconnector.databaseConnect();
      
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
            String query = "SELECT client_email,contact_email FROM snos_client WHERE snos_type = '"  + mail2 +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                     
              rs.next();
                String Client_mail = rs.getString ("client_email"); 
                String Contact_mail= rs.getString ("contact_email");
                //String date1 =rs.getString ("re_time"); 
                   if(Client_mail.equals("")||Contact_mail.equals(""))
                   {
                       mail=null;
                       return mail;
                   }else{
                     mail=new String[] {Client_mail,Contact_mail};
                      return mail;  
                   }
                
                // dbconnector.dbconn.close();
                
          
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"Email Address Not Found");
             return mail;
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
  }  
      
      //****************************************************************************
       //store the selected customers email from the database in string array form 
    ///******************************************************************************
      
      public String [] getClientEmail()
      {
          return mail;
      }
    
      public UserInforGet QueryHome_Name_Logo()
      {
          UserInforGet home_id=new UserInforGet();
          dbconnector.databaseConnect();
          try 
          {
              statement = dbconnector.dbconn.createStatement();
            String query = "SELECT snoc_name,so_city,logo FROM setup_tab where sid='1'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           
           if (rs.next()) 
            {
                home_id.SetNoc_name(rs.getString("snoc_name"));
                home_id.SetNoc_City(rs.getString("so_city"));
                home_id.SetLogo(ImageIO.read(rs.getBinaryStream("logo")));
                 
                //System.out.println("The Name of the Staff is "+rs.getString("firstname"));
             }
             statement.close();

          }catch (SQLException  exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Setup Information Not Found");
         }
       catch (IOException exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Setup Information Not Found");
         }
           //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }

    return  home_id;
  }
      
      
      
      
      public UserInforGet dateMessageInfor(String dat) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
      
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT snos_type,re_time  FROM sms_in WHERE re_time= '" + dat + "'" ;
            ResultSet rs = statement.executeQuery(query);
            
            rs.next();
          
            //getuser.SetSensor(rs.getString("snos_type"));
            this.querySNOSType(rs.getString("snos_type"));
            
            statement.close();
            
              dbconnector.dbconn.close();
            return getuser;
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            return getuser;
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
   }
       public void dateMessageInfor1(String dat) {
       getuser = new UserInforGet();
        dbconnector.databaseConnect();
      
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT snos_type,re_time  FROM sms_in WHERE re_time= '" + dat + "'" ;
            ResultSet rs = statement.executeQuery(query);
            
            rs.next();
          
            //getuser.SetSensor(rs.getString("snos_type"));
            this.querySNOSType(rs.getString("snos_type"));
            dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
   }
      
     //***************************************************************************************
      // error Checking methods for different things are below
      //**************************************************************************************
      
       public boolean CheckExistingSNOS(String snos){
       // JOptionPane.showMessageDialog (null,matric+" "+sem+" "+sec);
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM snos_table WHERE snos_type = '" +
            snos + "'" ;
            ResultSet rs1 = statement.executeQuery( query );
            statement.close();
            if(rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    }
       public boolean CheckExistingFoneNumber(String snos){
       // JOptionPane.showMessageDialog (null,matric+" "+sem+" "+sec);
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT fone FROM snos_table WHERE fone = '" +
            snos + "'" ;
            ResultSet rs1 = statement.executeQuery( query );


            if(rs1.next()) {
                return true;
            }
            statement.close();

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    }
    public boolean CheckExistingEmail(String snos){
       // JOptionPane.showMessageDialog (null,matric+" "+sem+" "+sec);
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT client_email,contact_email FROM snos_client WHERE snos_type = '" +
            snos + "'" ;
            ResultSet rs1 = statement.executeQuery( query );


            if(rs1.next()) {
                return true;
            }
          statement.close();
          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    }
      public void getTextMessageAndDisplay(String sender,String text,String d){
          record=new String[] {sender,text,d};
                
                row.addElement(record);
      }
      public ArrayList<String []> queryRegisteredSNOSCustomers(){
   
          dbconnector.databaseConnect();
          ArrayList<String []> cous = new ArrayList<String[]>();
      
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
            String query = "SELECT Surname,firstname FROM snos_client ";
            
           ResultSet rs = statement.executeQuery(query);
                   int num =0;  
               while (rs.next()) {
                String surname = rs.getString ("Surname"); 
                String firstname= rs.getString ("firstname");
                customers=new String[] {surname,firstname};
                     cous.add(customers);
                     num++;
               }
               dbconnector.dbconn.close();
                
          
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"NO Registered Customer Found");
             return null;
           
         }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
       return  cous;
  }  
  public ArrayList<String []> queryRegisteredSENSORCustomers(String snos_type){
   
          dbconnector.databaseConnect();
          ArrayList<String []> cous = new ArrayList<String[]>();
      
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
     String query = "SELECT sensor FROM user_sensor_table WHERE snos_type = '"  + snos_type +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                   int num =0;  
               while (rs.next()) {
                String sen = rs.getString ("sensor"); 
               
                     customers=new String[] {sen};
                     cous.add(customers);
                       
                  
                     num++;
               }
               statement.close();
               dbconnector.dbconn.close();
                
          
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"NO Registered Sensor Found");
             return null;
           
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
       return  cous;
  }  
  public ArrayList<String []> queryListCustomersPhoneContacts(String snos_type){
   
          dbconnector.databaseConnect();
          ArrayList<String []> cous = new ArrayList<String[]>();
      
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
           String query = "SELECT name,gsm,address,sms_type FROM clients_contacts_tab WHERE snos_type = '"  + snos_type +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                   int num =0;  
               while (rs.next()) {
                String name1 = rs.getString ("name");
                String gsm=rs.getString("gsm");
                String add=rs.getString("address");
                 String mailtype=rs.getString("sms_type");
               
                     customers=new String[] {name1,gsm,add,mailtype};
                     cous.add(customers);
                       
                  
                     num++;
               }
               statement.close();
               dbconnector.dbconn.close();
                
          
          }catch (SQLException exc) {
              
            
            JOptionPane.showMessageDialog(null,"NO Customer's Mobile Contact Found.The cause is:"+exc.getMessage());
            exc.printStackTrace();
             return null;
          }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
       return  cous;
  }
  public ArrayList<String []> queryListCustomersE_Mail_Contacts(String snos_type){
   
          dbconnector.databaseConnect();
          ArrayList<String []> cous = new ArrayList<String[]>();
      
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
     String query = "SELECT name,email1,mail_type FROM clients_contacts_tab WHERE snos_type = '"  + snos_type +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                   int num =0;  
               while (rs.next()) {
                String name1 = rs.getString ("name"); 
                String mail = rs.getString ("email1"); 
                String text_type = rs.getString ("mail_type"); 
               
                     customers=new String[] {name1,mail,text_type};
                     cous.add(customers);
                       
                  
                     num++;
               }
               statement.close();
               dbconnector.dbconn.close();
                
          
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"NO Customer's E-mail Contact Found");
             return null;
           
         }
      //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
       return  cous;
  }  
    
    
    
  
  
  
 public String queryReceivedSMS_Getid(String date){
   
          dbconnector.databaseConnect();
     
      try {
          
           statement = dbconnector.dbconn.createStatement();
           
            String query = "SELECT id FROM sms_in WHERE re_time = '"  + date +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                     
              rs.next();
              
                String sms_id = rs.getString ("id"); 
                statement.close();
                dbconnector.dbconn.close();
                 
                  if(sms_id.equals(""))
                   {
                       convertTosnos=null;
                       return convertTosnos;
                   }else{
                     convertTosnos=sms_id;
                      return convertTosnos;  
                   }
                
                 }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"NO SMS ID FOUND");
             return convertTosnos;
         }
         //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
  }    
  public void AddAction_To_RecievedSMS(String snos_type,String sensor_msg,String action_type,String sms_id,String text_sent,String sms_date,String action_date){ 
      dbconnector.databaseConnect();
      try {
            
            statement = dbconnector.dbconn.createStatement();           
            String query = "INSERT INTO action_tab" +
            "(snos_type,sensor,action_type,sms_id,text_sent,sms_date,action_date)" +
             "VALUES('" + snos_type + "','" + sensor_msg+ "','" + action_type +"','" + sms_id+"','" +text_sent+"','" + sms_date+"','" +action_date+"')";
             int result = statement.executeUpdate( query );
             statement.close();
             
        }catch ( SQLException sqlex ) 
        {
          
                sqlex.printStackTrace();
                System.out.println("SQL Error");
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
  }
 
 public void UpdateActionTaken(String action_type,String text_sent,String action_date,String sms_id){ 
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                   
        String query = "UPDATE action_tab SET action_type='"+action_type+"',text_sent='"+text_sent+"',action_date='"+action_date+"' WHERE sms_id = '" + sms_id + "'" ;
           int res=statement.executeUpdate(query);
             
          
                statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    } 
    public boolean UpdateSMSStatus(String status,String sms_id){ 
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                   
        String query = "UPDATE sms_in SET status='"+status+"' WHERE id = '" + sms_id + "'" ;
           int res=statement.executeUpdate(query);
            
          
                statement.close();
                 return true;
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
                return false;
            
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    } 
     public int CountRegisteredClient() {
        dbconnector.databaseConnect();
      
          try {
                statement = dbconnector.dbconn.createStatement();
                String query = "SELECT * FROM snos_client" ;
                ResultSet rs1 = statement.executeQuery(query);
                int num = 0;
                String ans = "yes";
                while (ans.equals("yes")){
                    num ++;
                    if(rs1.next()){
                        ans = "yes";
                    }else{
                        ans = "n";
                        return num - 1; 
                    }
                }
                statement.close();
                return 0;
          }catch (SQLException exc) {
          JOptionPane.showMessageDialog(null,exc.toString( ));
            return 0;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    public int CountLga(String stat) {
         
        dbconnector.databaseConnect();
      
          try {
                statement = dbconnector.dbconn.createStatement();
                String query = "SELECT * FROM lga_tab WHERE state = '"  + stat +  "'";
            
                ResultSet rs1 = statement.executeQuery(query);
                int num = 0;
                String ans = "yes";
                while (ans.equals("yes")){
                    num ++;
                    if(rs1.next()){
                        ans = "yes";
                    }else{
                        ans = "n";
                        return num - 1; 
                    }
                }
                statement.close();
                return 0;
          }catch (SQLException exc) {
          JOptionPane.showMessageDialog(null,exc.toString( ));
            return 0;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
     
public String[] queryListofLga(String sta) 
    {
        dbconnector.databaseConnect();
       
          try {
                statement = dbconnector.dbconn.createStatement();

              String query = "SELECT * FROM lga_tab WHERE state = '"  + sta +  "'";
                ResultSet rs = statement.executeQuery(query);
                int num = 0;
                num = CountLga(sta);
                num = num + 1;
                String record[] = new String[num];
                record[0] = "Choose one";
                for (int i = 1; i < num; i++){
                    rs.next();
                    record[i] = (rs.getString(3));
                    if (i == num -1){
                        return record;
                    }
                }
                return record;

          }catch (SQLException exc) {
           
           JOptionPane.showMessageDialog(null,exc.toString( ));
            String[] record = {"No Information"};
            return record;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
      
      public String [] queryListCustomers_printable_personnal(String snos_type){
   
          dbconnector.databaseConnect();
          try {
          
           statement = dbconnector.dbconn.createStatement();
           String query = "SELECT Surname,firstname,Client_name,fone,loco,build_type,state,lga FROM snos_client WHERE snos_type = '"  + snos_type +  "'";
            
           ResultSet rs = statement.executeQuery(query);
                   int num =0;  
               while (rs.next()) {
                String sname = rs.getString ("Surname"); 
                String fname = rs.getString ("firstname");
                String oname = rs.getString ("Client_name");
                String number = rs.getString ("fone"); 
                String add = rs.getString ("loco"); 
                String prop = rs.getString ("build_type"); 
                String stat = rs.getString ("state"); 
                String lga2 = rs.getString ("lga"); 
               
              customers=new String[] {sname,fname,oname,number,add,prop,stat,lga2};
               }
               statement.close();
               dbconnector.dbconn.close();
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"NO Customer's Information Found");
             return null;
           
         }
         //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
       return  customers;
  }
  public void ChangeValue(int i,Object val){
   row = new Vector ();   
   row.set(i, val);
  }
      
      public Vector getRows (){
       return row;
   }  
      public Properties getSnosProp (){
       return snosprop;
   }  
     public Properties getUserSensorProp (){
       return usersensorprop;
   } 
     public List getSensorProp (){
       return sensorList;
   }  
     
    
    
/*
System.out.println("The path separator is:"+System.getProperty("path.separator"));
System.out.println("The file separator is:"+System.getProperty("file.separator"));
System.out.println("The os is: "+System.getProperty("os.name"));
System.out.println("The Class path is: "+System.getProperty("java.class.path"));
System.out.println("The  Java full Home is: "+System.getProperty("java.home"));
System.out.println("The  Java full Home is: "+System.getProperty("java.home")+System.getProperty("file.separator")+"javax.comm.properties");
System.out.println("The  OS version is: "+System.getProperty("os.version"));
System.out.println("The  java version is: "+System.getProperty("java.version"));
System.out.println("The  user name is: "+System.getProperty("user.name")+" and his home is:"+System.getProperty("user.home"));
* */

}