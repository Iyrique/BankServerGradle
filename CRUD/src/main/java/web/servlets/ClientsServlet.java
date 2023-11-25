package web.servlets;

import connection.ConnectDB;
import crud.CRUD;
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

    private CRUD crud;

    public ClientsServlet() throws SQLException {
        crud = new CRUD(ConnectDB.connector());
    }

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
        if (pathInfo == null || pathInfo.equals("/")) {
            try {
                sendAllPersons(resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            int id = Integer.parseInt(pathInfo.substring(1));
            try {
                sendPersonById(id, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendAllPersons(HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        List<Client> clients = new ArrayList<>();
        clients = crud.getAllClients();
        writer.write("List of Persons:\n");
        for (Client person : clients) {
            writer.write(person.toString() + "\n");
        }
    }

    private void sendPersonById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        Client client = crud.readClientById(id);
        if (client == null) {
            writer.write("Клиента не существует");
        } else writer.write(client.toString());
    }
}
