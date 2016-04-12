/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *This is a class that implements push message notification to various mobile devices.
 * It pushes received sms alerts to registered devices.
 * @author user
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import sun.rmi.runtime.Log;
/**
 This is a class that implements push message notification to various mobile devices.
 It pushes received sms alerts to registered devices.
* @param sEndpushMsgUrl String object which represents the remote url to push  message sms alert through.
* @param packageName :String object that represent the mobile app's package name as resides in CodeNameOne Server(codenameone.com)
* @param codename_one_email_account :String object that represent the authorized current user name's account as created and resides in CodeNameOne Server(codenameone.com)
* @param type String object which represents the type of push message: 1=visible text payload, 2:invisible/silent text payload and 3: combines both hidden payload with text visible to the user.
* @param gooleAuthKey_ProjectNumber :String object that represent the google payload as resides in Google Developer site(developers.google.com)
* @param prodEnvironment :String object that represent('true' or 'false') whether the mobile app is currently on production or development mode. At the moment is 'false' because the mobile app has not being taken to market.
* @param googleServerKey :Similar to google project key but in this case it is a server key attached to a project
* @param iOSCertURL_development String object which represents the text iOS development push certificate url-created at developer.apple.com-but which currently resides at the CodeNameOne Cloud server.
* @param iOSCertPassword :String object that represents the password of the above iOS certificate.
* @param bbPushURL :String object that represents the blackberry push url-not used at the moment since we don't have a full functional blackberry mobile version.
* @param bbAppId :String object that represents the blackberry Application ID(like that of google)-not used at the moment since we don't have a full functional blackberry mobile version.
* @param bbPassword :String object that represents the blackberry certificate password-not used at the moment since we don't have a full functional blackberry mobile version.
* @param bbPort :String object that represents the blackberry port for older versions of BB-not used at the moment since we don't have a full functional blackberry mobile version.
* @see ReadMessages1.java, App_Notification.java
*/
public class DeviceMessagePusher
{
    static String sEndpushMsgUrl="https://codename-one.appspot.com/sendPushMessage";
    //static String deviceId="5957281768275968";
    static String packageName="com.teledom.snosapp";
    static String codename_one_email_account="afamsimon@gmail.com";
    static String type="1";
    static String gooleAuthKey_ProjectNumber="920181369538";
    static String prodEnvironment = "false";
    static String googleServerKey = "AIzaSyBT837SlAr-XzPtHzufXNF0Hbfe6ggaFKU";
    static String iOSCertURL_development = "https://codename-one-push-certificates.s3.amazonaws.com/com.teledom.snosapp_DevelopmentPushea852471-10ae-421d-b886-3db47be86e7f.p12";
    static String iOSCertPassword = "m34N9G9d";
    static String bbPushURL = "http://www.bbpush.com/ios.cert";
    static String bbAppId = "1sandkdkdkd";
    static String bbPassword = "mybbpass.com";
    static String bbPort = "233";
    /**
    This is a constructor of the class that implements push message notification to various mobile devices.
    It pushes received sms alerts to registered devices.
    * @param deviceId String object which represents the deviceId-belonging to a particular device- to push  message sms alert to: null means push to all the devices, number digist means push to that particular device that have that id number.
    * @param pushMessage String object which represents the push  message sms alert to forward to the device(s)
    */
    public DeviceMessagePusher(String deviceId,String pushMessage)
    {
        //get all the parameters from file needed to send push notification messages to devices.
        Properties prop=AppConfig.LoadFromConfigFiles();
        sEndpushMsgUrl=prop.getProperty("sEndpushMsgUrl");
        packageName=prop.getProperty("packageName");
        codename_one_email_account=prop.getProperty("codename_one_email_account");
        type=prop.getProperty("type");
        prodEnvironment=prop.getProperty("prodEnvironment");
        googleServerKey=prop.getProperty("googleServerKey");
        iOSCertURL_development=prop.getProperty("iOSCertURL_development");
        iOSCertPassword=prop.getProperty("iOSCertPassword");
        bbPushURL=prop.getProperty("bbPushURL");
        bbAppId=prop.getProperty("bbAppId");
        bbPassword=prop.getProperty("bbPassword");
        bbPort=prop.getProperty("bbPort");
        if(sEndpushMsgUrl==null)
        {
            JOptionPane.showMessageDialog(null, "The applications's configuration file for sending push notifications to mobile devices \ncannot be found OR has been tampered with","WRONG PUSH NOTIFICATION PARAMETERS",2); 
            System.exit(0);
        }
        //authenticate the parameters to ensure they have not been tampered with.
        if(sEndpushMsgUrl.equals("") || packageName.equals("") || codename_one_email_account.equals("") || type.equals("")
        ||prodEnvironment.equals("") || googleServerKey.equals("") ||iOSCertURL_development.equals("") || iOSCertPassword.equals("")
        ||bbPushURL.equals("") ||bbAppId.equals("") ||bbPassword.equals("") ||bbPort.equals("")){
            JOptionPane.showMessageDialog(null, " One of the parameters for sending push notifications to mobile devices has been tampered with","WRONG PUSH NOTIFICATION PARAMETERS",2); 
            System.exit(0);
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if(deviceId==null||deviceId.equals(""))
        {
            System.out.println("log_tag::Push messages will be sent to many devices " );
        }
        else
        {
            //add the parameter then
            params.add(new BasicNameValuePair("device", deviceId));
            System.out.println("log_tag::Push message will be sent to ONLY one device " );
        }
        //construct other needed push message parameters
        params.add(new BasicNameValuePair("packageName", packageName));
        params.add(new BasicNameValuePair("email", codename_one_email_account));
        params.add(new BasicNameValuePair("type", type));
        params.add(new BasicNameValuePair("auth", googleServerKey));
        params.add(new BasicNameValuePair("certPassword", iOSCertPassword));
        params.add(new BasicNameValuePair("body", pushMessage));
        params.add(new BasicNameValuePair("cert", iOSCertURL_development));
        params.add(new BasicNameValuePair("production", prodEnvironment));
        makePushNotificationRequest(sEndpushMsgUrl,"POST",params);
    }
    /*
    * This method actually does the pushing in question using a specified url,
    * http method containing the parameters in question.
    * @throws UnsupportedEncodingException,ClientProtocolException,IOException
    */
    public static void makePushNotificationRequest(String url, String method,
    List<NameValuePair> params) 
    {
        InputStream is = null;
        // Making HTTP request
        try 
        {
            // check for request method
            if(method == "POST")
            {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                System.out.println("log_tag:POST's URL IS: " + url);
                 
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println("log_tag:Credential Provider= " +httpClient.getCredentialsProvider());
                System.out.println("log_tag:ResponseInterceptorCount= " +httpClient.getResponseInterceptorCount());
                System.out.println("Content lent="+httpEntity.getContentLength());
            }
            else if(method == "GET")
            {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                System.out.println("log_tag:GET's URL IS: " + url);
                HttpGet httpGet = new HttpGet(url);
                
                HttpResponse httpResponse = httpClient.execute(httpGet);
                System.out.println("log_tag:Credential Provider= " +httpClient.getCredentialsProvider());
                // System.out.println("log_tag:Credential Provider= " +httpClient.getCredentialsProvider());
                System.out.println("log_tag:ResponseInterceptorCount= " +httpClient.getResponseInterceptorCount());
                //System.out.println("Content lent="+httpEntity.getContentLength());
                
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
               // Log.e("log_tag", "content is: " +httpEntity.getContent().toString());
            }
        } 
        catch (UnsupportedEncodingException e) {
        	System.out.println("log_tag:Error in http connection " + e.toString());
            //e.printStackTrace();
        } catch (ClientProtocolException e) {
        	System.out.println("log_tag:Error in http connection " + e.toString());
        } catch (IOException e) {
        System.out.println("log_tag: Error in http connection " + e.toString());
        //e.printStackTrace();
        }
        try {
            String result="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            System.out.println("The RESULT FROM REMOTE SERVER for http method "+method+" is: "+result);
        } catch (Exception e) {
            System.out.println("BufferedERRORRRRR Error Error converting result " + e.getMessage());
            e.printStackTrace();
        }
    }
}