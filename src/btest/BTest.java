/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package btest;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;







/**
 *
 * @author Lenovo
 */
public class BTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {
             File currentDirFile = new File("");
String helper = currentDirFile.getAbsolutePath();
System.out.println("HELPER :"+helper);

String currentDir;
       
                currentDir = helper.substring(0, helper.length() - currentDirFile.getCanonicalPath().length());
           
System.out.println("CURRENT DIR:"+currentDir);
           StringBuilder path=new StringBuilder();
           System.setProperty("file.encoding","iso-8859-9");
           path.append(Path.instance.getPath()).append("/keystore.jks");
             String sslStoreProp = "javax.net.ssl.trustStore";
          //String userDir = System.getProperty("user.dir");
          //  System.out.println(url.getPath()+"/keystore.jks");
          System.setProperty(sslStoreProp, helper+"/src/keystore/keystore.jks");
            System.out.println("KEYSTORE FILE PATH :"+helper+"/src/keystore/keystore.jks");
          System.out.println("KEYSTORE EX FILE PATH :"+path.toString());
          System.setProperty("javax.net.ssl.keyStorePassword","changeit");
        
            BstbAsansorDenetimServis service=new BstbAsansorDenetimServis();
        //service.setHandlerResolver(new HeaderHandlerResolver());
            IBstbAsansorDenetimServis
             port=service.getBasicHttpBindingIBstbAsansorDenetimServis();
          

            List<Handler> handlerChain = new ArrayList<Handler>();
            //Add a handler to the handler chain
           handlerChain.add(  new HeaderHandler());
 
            //Acquire javax.xml.ws.Binding
            Binding binding = ( ( BindingProvider )port ).getBinding();
 
// Set the handler chain to a port by using javax.xml.ws.Binding
 
           binding.setHandlerChain(handlerChain);
           /*
           DenetimKayit denetimKayit = new DenetimKayit();
           ArrayOfDenetimKayitDetayFirmaBilgi a=new ArrayOfDenetimKayitDetayFirmaBilgi();
          
          
           DenetimKayitDetayFirmaBilgi denetimKayitDetayFirmaBilgi = new DenetimKayitDetayFirmaBilgi();
		
            ObjectFactory o=new ObjectFactory();
		denetimKayitDetayFirmaBilgi.setBakimFirmaAd(o.createString("TEST"));
		denetimKayitDetayFirmaBilgi.setBakimFirmaAdresi(o.createString("TEST"));
                 a.getDenetimKayitDetayFirmaBilgi().add(denetimKayitDetayFirmaBilgi);
             
                 denetimKayit.setDenetimKayitDetayFirmaBilgi(o.createArrayOfDenetimKayitDetayFirmaBilgi(a));
           port.asansorDenetimKayit(denetimKayit);
           */
          ServisSonucOfArrayOfReferansKontrolSorular8Zb117HL servisSonuc= port.referansDenetimKayitKontrolSorularListesiGetir();
          if (!servisSonuc.isHata()){
             ArrayOfReferansKontrolSorular a1= servisSonuc.getSonuc().getValue();
             List<ReferansKontrolSorular> r= a1.getReferansKontrolSorular();
             for (int i=0;i<r.size();i++){
                 System.out.println(r.get(i).getId());
             }
          }
           
         /*  ServisSonucOfArrayOfIl8Zb117HL servisSonuc=   port.tumIlleriSorgulama();
           ArrayOfIl il=servisSonuc.getSonuc().getValue();
        for (Il i:il.getIl()){
            System.out.println(i.getAd());
        }
                 */
        } /*catch (IBstbAsansorDenetimServisTumIlleriSorgulamaServiceExceptionFaultFaultMessage ex) {
            Logger.getLogger(BTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/catch (IOException ex) {
                Logger.getLogger(BTest.class.getName()).log(Level.SEVERE, null, ex);
            }  catch (IBstbAsansorDenetimServisReferansDenetimKayitKontrolSorularListesiGetirServiceExceptionFaultFaultMessage ex) {
            Logger.getLogger(BTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}
