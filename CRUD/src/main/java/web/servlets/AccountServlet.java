package web.servlets;

import connection.ConnectDB;
import crud.CRUD;
import entity.Account;
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

@WebServlet("/account/*")
public class AccountServlet extends HttpServlet {

    private CRUD crud;

    public AccountServlet() throws SQLException {
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
                        sendAllAccount(resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getById".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendAccountById(id, resp);
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

    private void sendAllAccount(HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        writer.write("List of accounts: \n");
        for (Account account: crud.getAllAccounts()) {
            writer.write(account.toString() + "\n");
        }
    }

    private void sendAccountById(int id, HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        Account account = crud.readAccount(id);
        if (account == null) {
            writer.write("Account not found");
        } else writer.write(account.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "create".equals(pathParts[1])) {
                try {
                    createAccount(req, resp);
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

    private void createAccount(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String clientId = req.getParameter("id");

        if (clientId != null && !clientId.isEmpty()) {
            crud.createAccount(Integer.parseInt(clientId));
            resp.getWriter().write("Account created successfully.");
        } else {
            resp.getWriter().write("Invalid parameters for creating a account.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "delete".equals(pathParts[1])) {
                int id = Integer.parseInt(pathParts[2]);
                try {
                    deleteAccountById(id, resp);
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

    private void deleteAccountById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        boolean deleted = crud.deleteAccount(id);
        if (deleted) {
            writer.write("Account deleted successfully.");
        } else {
            writer.write("Account not found or could not be deleted.");
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
