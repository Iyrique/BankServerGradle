import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import web.servlets.*;

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

        Wrapper creditServlet = Tomcat.addServlet(ctx, "creditServlet", new CreditServlet());
        creditServlet.setLoadOnStartup(1);
        creditServlet.addMapping("/credit/*");

        Wrapper depositServlet = Tomcat.addServlet(ctx, "depositServlet", new DepositServlet());
        depositServlet.setLoadOnStartup(1);
        depositServlet.addMapping("/deposit/*");

        Wrapper cardAccountServlet = Tomcat.addServlet(ctx, "cardAccountServlet", new CardAccountServlet());
        cardAccountServlet.setLoadOnStartup(1);
        cardAccountServlet.addMapping("/cardAccount/*");

        Wrapper cardServlet = Tomcat.addServlet(ctx, "cardServlet", new CardServlet());
        cardServlet.setLoadOnStartup(1);
        cardServlet.addMapping("/card/*");

        Wrapper accountServlet = Tomcat.addServlet(ctx, "accountServlet", new AccountServlet());
        accountServlet.setLoadOnStartup(1);
        accountServlet.addMapping("/account/*");

        tomcat.start();
    }
}
