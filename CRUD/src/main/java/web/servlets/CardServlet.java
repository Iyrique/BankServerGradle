package web.servlets;

import connection.ConnectDB;
import crud.CRUD;
import entity.Card;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/card/*")
public class CardServlet extends HttpServlet {

    private CRUD crud;

    public CardServlet() throws SQLException {
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
                        sendAllCards(resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getById".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendCardById(id, resp);
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

    private void sendAllCards(HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        writer.write("List of cards: \n");
        for (Card card: crud.getAllCards()) {
            writer.write(card.toString() + "\n");
        }
    }

    private void sendCardById(int id, HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        Card card = crud.readCard(id);
        if (card == null) {
            writer.write("Credit not found");
        } else writer.write(card.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "create".equals(pathParts[1])) {
                try {
                    createCard(req, resp);
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

    private void createCard(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String clientId = req.getParameter("clientId");
        if (clientId != null && !clientId.isEmpty()) {
            crud.createCard(Integer.parseInt(clientId));
            resp.getWriter().write("Card created successfully.");
        } else {
            resp.getWriter().write("Invalid parameters for creating a card.");
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
                    deleteCardById(id, resp);
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

    private void deleteCardById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        boolean deleted = crud.deleteCard(id);
        if (deleted) {
            writer.write("Card deleted successfully.");
        } else {
            writer.write("Card not found or could not be deleted.");
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
