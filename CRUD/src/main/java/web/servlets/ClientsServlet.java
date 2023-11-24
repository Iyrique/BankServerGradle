package web.servlets;

import connection.ConnectDB;
import crud.dao.ClientDAO;
import entity.Client;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/client/*")
public class ClientsServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try {
            sendAllPersons(resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendAllPersons(HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        List<Client> clients = new ArrayList<>();
        ClientDAO clientDAO = new ClientDAO(ConnectDB.connector());
        clients = clientDAO.getAll();
        writer.write("List of Persons:\n");
        for (Client person : clients) {
            writer.write(person.toString() + "\n");
        }
    }
}
