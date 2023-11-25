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
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                String command = pathParts[1];
                if ("getAll".equals(command)) {
                    try {
                        sendAllPersons(resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getById".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendPersonById(id, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getByName".equals(command)) {
                    String name = req.getParameter("name");
                    try {
                        sendPersonByName(name, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    resp.getWriter().write("Invalid command.");
                }
            } else {
                resp.getWriter().write("Invalid path.");
            }
        } else {
            resp.getWriter().write("Path not specified.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "create".equals(pathParts[1])) {
                try {
                    createPerson(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                resp.getWriter().write("Invalid path for POST request.");
            }
        } else {
            resp.getWriter().write("Path not specified for POST request.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "update".equals(pathParts[1])) {
                int id = Integer.parseInt(pathParts[2]);
                try {
                    updatePersonById(id, req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                resp.getWriter().write("Invalid path for PUT request.");
            }
        } else {
            resp.getWriter().write("Path not specified for PUT request.");
        }
    }

    private void updatePersonById(int id, HttpServletRequest req, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        Client client = crud.readClientById(id);
        if (client != null) {
            // Достаем параметры из запроса и обновляем клиента
            String newName = req.getParameter("name");
            if (newName != null && !newName.isEmpty()) {
                client.setName(newName);
            }
            crud.updateClient(id, newName);
            writer.write("Person updated successfully.");
        } else {
            writer.write("Person not found or could not be updated.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "delete".equals(pathParts[1])) {
                int id = Integer.parseInt(pathParts[2]);
                try {
                    deletePersonById(id, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                resp.getWriter().write("Invalid path for DELETE request.");
            }
        } else {
            resp.getWriter().write("Path not specified for DELETE request.");
        }
    }

    private void createPerson(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");

        if (name != null && !name.isEmpty() && birthday != null && !birthday.isEmpty()) {
            crud.createClient(name, birthday);
            resp.getWriter().write("Person created successfully.");
        } else {
            resp.getWriter().write("Invalid parameters for creating a person.");
        }
    }

    private void deletePersonById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        boolean deleted = crud.deleteClient(id);
        if (deleted) {
            writer.write("Person deleted successfully.");
        } else {
            writer.write("Person not found or could not be deleted.");
        }
    }

    private void sendAllPersons(HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        List<Client> clients = new ArrayList<>();
        clients = crud.getAllClients();
        writer.write("List of Clients:\n");
        for (Client person : clients) {
            writer.write(person.toString() + "\n");
        }
    }

    private void sendPersonById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        Client client = crud.readClientById(id);
        if (client == null) {
            writer.write("Client not found");
        } else writer.write(client.toString());
    }

    private void sendPersonByName(String name, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        Client client = crud.readClientByName(name);
        if (client != null) {
            writer.write("Client found:\n" + client.toString());
        } else {
            writer.write("Client not found.");
        }
    }
}
