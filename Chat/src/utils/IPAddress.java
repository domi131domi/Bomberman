package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class IPAddress {
	
	public static String getPublicIP() {
		String systemipaddress = ""; 
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = 
            new BufferedReader(new InputStreamReader(url_name.openStream())); 
  
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (Exception e) { 
         e.printStackTrace();
        }
		return systemipaddress;  
	}
}
