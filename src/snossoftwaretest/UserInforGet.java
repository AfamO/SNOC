/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

import java.awt.image.BufferedImage;

/**
 * This encapsulates and defines ALL the "gets" and "sets"
 * AND the properties needed by the application, especially client properties 
 * @author Afam;
 * @see AddSensor.java,AssignSensor.java,AddClients, AddContacts,AddDeviceInfo
 * @version 1.0 
 */
public class UserInforGet {
    private String name;
     private String oname;
    private String fone;
    private String contact;
    private String loc;
    private String area;
    private String bulid;
    private String lga;
    private String state;
    private String identity;
    private String ClientPassword;
    private String Client_email;
    private String Contact_email;
    private String sensor;
    //declaration for adding users
    private String OperaName;
    private String userid;
    private String sname;
    private String fname;
    
    private String pass;
    private String status;
     //declaration for adding text Messages
    private String message;
    private String dat;
    private String descrpt;
    private String relation_position;
    
    private BufferedImage log3;
    private String noc_name,city;
    
    
    UserInforGet(){
        
    }
    UserInforGet(String sname,String fname,String oname,String fone,String loc,String lga,String state,String id,String pa,String email){
      SetSname(sname);
      SetContact(loc);
    SetFname(fname);SetOname(oname);SetFone(fone);
     SetLoc(loc);SetLga(lga);SetState(state);SetIdentity(id);SetClientPassword(pa);SetClientEmail(email);
    
    }
    UserInforGet(String sensor,String nam,String descrpt,String build,String loc,String add,String lga,String state){
     SetSensor(sensor);SetClient(nam);
     SetDescrpt(descrpt);SetBuild(build);SetLoc(loc);
     SetContact(add);SetLga(lga);SetState(state);

    }
    UserInforGet(String sensor,String nam,String relat_pos,String add,String email1,String fone){
     SetSensor(sensor);SetClient(nam);SetRelation_Position(relat_pos);SetContact(add);
     SetClientEmail(email1);SetFone(fone);
     

    }
    
    UserInforGet(String senName,String fon){
     SetSensor(senName);SetFone(fon);
    }
    UserInforGet(String senName){
     SetSensor(senName);
    }
    
    UserInforGet(String name4,String city,BufferedImage log4)
    {
        SetNoc_name(name4);SetNoc_City(city);SetLogo(log4);
    }
    
    UserInforGet(String nam,String id3,String pv3,String st2){
    SetOperaName(nam);SetUserid(id3);SetPass(pv3);SetStatus(st2);
    }
    
    //set methods for the Information
    public void SetClient(String m){name=m;}
    public void SetOname(String on){oname=on;}
    public void SetDescrpt(String decr){descrpt=decr;}
    public void SetFone(String fo){fone=fo;}
    public void SetSname(String sn){sname=sn;}
    public void SetFname(String fn){fname=fn;}
    public void SetContact(String con){contact=con;}
    public void SetLoc(String lo){loc=lo;}
    public void SetArea(String a){area=a;}
    public void SetBuild(String bu){bulid=bu;}
    public void SetLga(String lg){lga=lg;}
    public void SetState(String sta){state=sta;}
     public void SetIdentity(String ident){identity=ident;}
    public void SetClientPassword(String pass){ClientPassword=pass;}
    public void SetClientEmail(String mail){Client_email=mail;}
    public void SetContactEmail(String mail2){Contact_email=mail2;}
    
     public void SetSensor(String sense){ sensor=sense; }
     public void SetOperaName(String opera){ OperaName=opera; }
     public void SetUserid(String id){ userid=id; }
     public void SetPass(String vas){ pass=vas; }
     public void SetStatus(String stat2){ status=stat2; }
     public void SetMessage(String mes2){ message=mes2; }
     public void SetDate1(String d){dat=d;}
     public void SetRelation_Position(String relatio_pos){this.relation_position=relatio_pos;}
     
     public void SetNoc_name(String nam2){ noc_name=nam2; }
     public void SetNoc_City(String loco){city=loco;}
     public void SetLogo(BufferedImage pix){log3=pix;}
    
    
    //get already setted Information
    public String getClient(){return name;}
    public String getOname(){return oname;}
    public String getFone(){return fone;}
    public String getContact(){return contact;}
    public String getLoc(){return loc;}
    public String getArea(){return area;}
    public String getBuild(){return bulid;}
    public String getLga(){return lga;}
    public String getState(){return state;}
    public String getIdentity(){return identity;}
    public String getClientPassword(){return ClientPassword;}
   public String getClientEmail(){return Client_email;}
   public String getContactEmail(){return Contact_email;}
    
    public String getSensor(){return sensor;}
    public String getOperaName(){return OperaName;}
    public String getUserid(){return userid;}
    public String getPass(){return pass;}
    public String getStatus(){return status;}
    public String getMess(){return message;}
    public String getDate1(){return dat;}
    public String getRelation_Position(){return this.relation_position;}
    public String getDecrpt(){return descrpt;}
    
    public String getNoc_name(){return noc_name;}
    public String getNoc_city(){return city;}
    public BufferedImage getLogo(){return log3;}

    String getsurname() {
       return sname;
    }

    String getFname() {
        return fname;
    }
}
