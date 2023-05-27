package com.algo.pattern.proxy.model;

import java.util.ArrayList;
import java.util.List;

public class ProxyInternet implements Internet {
	
	// 代理会持有真正对象的引用
    private Internet internet = new RealInternet();
    
    private static List<String> bannedSites = new ArrayList<>(); ;
      
    static {
        bannedSites.add("xvideos.com");
        bannedSites.add("jizzonline.com");
        bannedSites.add("pornhub.com");
        bannedSites.add("91porn.com");
    }
      
    @Override
    public void connectTo(String serverhost) throws Exception {
        if(bannedSites.contains(serverhost.toLowerCase()))
        {
            throw new Exception("Access Denied");
        }
          
        internet.connectTo(serverhost);
    }
  
}
