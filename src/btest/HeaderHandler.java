package btest;



import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;

import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
 
public class HeaderHandler implements SOAPHandler<SOAPMessageContext>{
 
   @Override
   public boolean handleMessage(SOAPMessageContext context) {
 
	
 
	Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
 
	//if this is a request, true for outbound messages, false for inbound
	if(isRequest){
 
	try{
          
	    SOAPMessage soapMsg = context.getMessage();
            SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
            SOAPHeader soapHeader = soapEnv.getHeader();
 
            //if no header, add one
            if (soapHeader == null){
            	soapHeader = soapEnv.addHeader();
            }
 
          
            SOAPElement security =
            		soapHeader.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");



            SOAPElement usernameToken =
                    security.addChildElement("UsernameToken", "wsse");
            usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

            SOAPElement username =
                    usernameToken.addChildElement("Username", "wsse");
            username.addTextNode("BSTB-234953");

            SOAPElement password =
                    usernameToken.addChildElement("Password", "wsse");
            password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
            password.addTextNode("bs*tb202klm869");
            soapMsg.saveChanges();
         
 
            //tracking
          
          System.out.println(  System.getProperty("file.encoding"));
         
          
            soapMsg.writeTo(System.out);
            System.out.println("");
           
            
	   }catch(SOAPException e){
		System.err.println(e);
	   }catch(IOException e){
		System.err.println(e);
	   }
 
         }
 
	   //continue other handler chain
	   return true;
   }
 
	@Override
	public boolean handleFault(SOAPMessageContext context) {
//		System.out.println("Client : handleFault()......");
		return true;
	}
 
	@Override
	public void close(MessageContext context) {
//		System.out.println("Client : close()......");
	}
        /*
	@Override
	public Set<QName> getHeaders() {
//		System.out.println("Client : getHeaders()......");
		return null;
	}*/
 @Override
 public Set<QName> getHeaders() {
        // throw new UnsupportedOperationException("Not supported yet.");
        final QName securityHeader = new QName(
                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
                "Security", "wsse");
 
        final HashSet headers = new HashSet();
        headers.add(securityHeader);
 
        return headers;
    }
}
