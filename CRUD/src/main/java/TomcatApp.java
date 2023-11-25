import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import web.servlets.BankServlet;
import web.servlets.ClientsServlet;

import java.sql.SQLException;

public class TomcatApp {

    public static void main(String[] args) throws LifecycleException, SQLException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper clientServlet = Tomcat.addServlet(ctx, "clientServlet", new ClientsServlet());
        clientServlet.setLoadOnStartup(1);
        clientServlet.addMapping("/client/*");

        Wrapper bankServlet = Tomcat.addServlet(ctx, "bankServlet", new BankServlet());
        bankServlet.setLoadOnStartup(1);
        bankServlet.addMapping("/bank");

        tomcat.start();
    }
}
