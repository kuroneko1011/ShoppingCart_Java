/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author Nhan
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection()
            throws /*ClassNotFoundException, SQLException*/ SQLException, NamingException {

        Context context = new InitialContext();
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("DSSE160120");
        Connection con = ds.getConnection();
        System.out.println("Come DBHelper");
        System.out.println("Con" +con);
        //1. Load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//        //2. Create connection string
//        String url = "jdbc:sqlserver:"
//                + "//localhost:1433"
//                + ";databaseName=PRJ";
//
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url, "sa", "nhanhero1234");
//
//        return con;
        return con;
    }
    
    public static void getSiteMaps(ServletContext context) throws IOException {
        //get site Maps file
        String siteMapFilePath = context.getInitParameter("SITES_MAPS_FILE_PATH");
        Properties props = new Properties();
        InputStream is = null;
        
        is = context.getResourceAsStream(siteMapFilePath);
        props.load(is);
        
        context.setAttribute("SITEMAP", props);
    }
}
