import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import web.servlets.ClientsServlet;

public class TomcatApp {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "clientServlet", new ClientsServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/client/*");
        tomcat.start();
    }
}
