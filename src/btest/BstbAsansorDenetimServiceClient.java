/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package btest;

import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Lenovo
 */
public class BstbAsansorDenetimServiceClient {

    public BstbAsansorDenetimServiceClient() {
    }

    public BstbAsansorDenetimServiceClient(String arg0) {
          String sslStoreProp = "javax.net.ssl.trustStore";
          System.setProperty(sslStoreProp, arg0);
          System.setProperty("javax.net.ssl.keyStorePassword","changeit");
    }
     public static BstbAsansorDenetimServiceClient INSTANCE =new BstbAsansorDenetimServiceClient();
    public BstbAsansorDenetimServis service=null;
 public  IBstbAsansorDenetimServis iService;
 
   public IBstbAsansorDenetimServis port() throws Exception{
           try {
               
            service = new BstbAsansorDenetimServis();
             service.setHandlerResolver(new HeaderHandlerResolver());
            iService = service.getBasicHttpBindingIBstbAsansorDenetimServis();
   
		
    return iService;
               
        } catch (Exception ex) {
            Logger.getLogger(BstbAsansorDenetimServiceClient.class.getName()).log(Level.SEVERE, null, ex);
             throw  new Exception(ex.getMessage());
        }
    }
}
