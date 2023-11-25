package web.servlets;

import connection.ConnectDB;
import crud.CRUD;
import entity.CardAccount;
import entity.Credit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/cardAccount/*")
public class CardAccountServlet extends HttpServlet {

    private CRUD crud;

    public CardAccountServlet() throws SQLException {
        crud = new CRUD(ConnectDB.connector());
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
                        sendAllCardAccount(resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getById".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendCardAccountById(id, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    resp.getWriter().write("Invalid command.");
                }
            } else {
                resp.getWriter().write("Invalid path.");
            }
        } else {
            resp.getWriter().write("Path not specified.");
        }
    }

    private void sendAllCardAccount(HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        writer.write("List of cardAccounts: \n");
        for (CardAccount cardAccount: crud.getAllCardAccounts()) {
            writer.write(cardAccount.toString() + "\n");
        }
    }

    private void sendCardAccountById(int id, HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        CardAccount cardAccount = crud.readCardAccount(id);
        if (cardAccount == null) {
            writer.write("CardAccount not found");
        } else writer.write(cardAccount.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "create".equals(pathParts[1])) {
                try {
                    createCardAccount(req, resp);
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

    private void createCardAccount(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String clientId = req.getParameter("clientId");
        if (clientId != null && !clientId.isEmpty()) {
            crud.createCardAccount(Integer.parseInt(clientId));
            resp.getWriter().write("CardAccount created successfully.");
        } else {
            resp.getWriter().write("Invalid parameters for creating a CardAccount.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "delete".equals(pathParts[1])) {
                int id = Integer.parseInt(pathParts[2]);
                try {
                    deleteCardAccountById(id, resp);
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

    private void deleteCardAccountById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        boolean deleted = crud.deleteCardAccount(id);
        if (deleted) {
            writer.write("CardAccount deleted successfully.");
        } else {
            writer.write("CardAccount not found or could not be deleted.");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
