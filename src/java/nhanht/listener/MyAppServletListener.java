/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.listener;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import nhanht.utils.DBHelper;

/**
 * Web application lifecycle listener.
 *
 * @author Nhan
 */
public class MyAppServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //get contextScope        
        ServletContext context = sce.getServletContext();
        try {
            //store site maps
            DBHelper.getSiteMaps(context);
        } catch (IOException ex) {
            context.log("MyAppServletListener _ IO " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //get contextScope        
        ServletContext context = sce.getServletContext();
        context.removeAttribute("SITEMAP");
    }
}
