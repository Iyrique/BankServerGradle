package web.servlets;

import connection.ConnectDB;
import crud.CRUD;
import entity.Client;
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
import java.util.List;

@WebServlet("/credit/*")
public class CreditServlet extends HttpServlet {

    private CRUD crud;

    public CreditServlet() throws SQLException {
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
                        sendAllCredits(resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getById".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendCreditById(id, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getAllByClientId".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendCreditsByClientId(id, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "create".equals(pathParts[1])) {
                try {
                    createCredit(req, resp);
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

    private void createCredit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String clientId = req.getParameter("clientId");
        String sum = req.getParameter("sum");
        String percent = req.getParameter("percent");
        String payment = req.getParameter("payment");
        String period = req.getParameter("period");
        if (clientId != null && !clientId.isEmpty() && sum != null && !sum.isEmpty() &&
                percent != null && !percent.isEmpty() && payment != null && !payment.isEmpty() &&
                period != null && !period.isEmpty() ) {
            crud.createCredit(Integer.parseInt(clientId), Double.parseDouble(sum),
                    Double.parseDouble(percent), Double.parseDouble(payment), period);
            resp.getWriter().write("Credit created successfully.");
        } else {
            resp.getWriter().write("Invalid parameters for creating a credit.");
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
                    updateCreditById(id, req, resp);
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

    private void updateCreditById(int id, HttpServletRequest req, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        Credit credit = crud.readCreditById(id);
        if (credit != null) {
            // Достаем параметры из запроса и обновляем клиента
            String newSum = req.getParameter("sum");
            String newPercent = req.getParameter("percent");
            String newPayment = req.getParameter("payment");
            crud.updateCredit(id, Double.parseDouble(newSum), Double.parseDouble(newPercent),
                    Double.parseDouble(newPayment));
            writer.write("Credit updated successfully.");
        } else {
            writer.write("Credit not found or could not be updated.");
        }
    }

    private void sendAllCredits(HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        writer.write("List of credits: \n");
        for (Credit credit: crud.getAllCredits()) {
            writer.write(credit.toString() + "\n");
        }
    }

    private void sendCreditById(int id, HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        Credit credit = crud.readCreditById(id);
        if (credit == null) {
            writer.write("Credit not found");
        } else writer.write(credit.toString());
    }

    private void sendCreditsByClientId(int id, HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        writer.write("List of credits: \n");
        List<Credit> credits = crud.readCreditsById(id);
        if (!credits.isEmpty()) {
            for (Credit credit: credits) {
                writer.write(credit.toString() + "\n");
            }
        } else writer.write("Credits not found!");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "delete".equals(pathParts[1])) {
                int id = Integer.parseInt(pathParts[2]);
                try {
                    deleteCreditById(id, resp);
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

    private void deleteCreditById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        boolean deleted = crud.deleteCredit(id);
        if (deleted) {
            writer.write("Person deleted successfully.");
        } else {
            writer.write("Person not found or could not be deleted.");
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
