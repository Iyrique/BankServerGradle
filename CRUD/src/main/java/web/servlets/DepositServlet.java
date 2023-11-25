package web.servlets;

import connection.ConnectDB;
import crud.CRUD;
import entity.Credit;
import entity.Deposit;

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

@WebServlet("/deposit/*")
public class DepositServlet extends HttpServlet {

    private CRUD crud;

    public DepositServlet() throws SQLException {
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
                        sendAllDeposits(resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getById".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendDepositById(id, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("getAllByClientId".equals(command)) {
                    int id = Integer.parseInt(pathParts[2]);
                    try {
                        sendDepositsByClientId(id, resp);
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

    private void sendAllDeposits(HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        writer.write("List of deposits: \n");
        for (Deposit deposit: crud.getAllDeposits()) {
            writer.write(deposit.toString() + "\n");
        }
    }

    private void sendDepositById(int id, HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        Deposit deposit = crud.readDepositById(id);
        if (deposit == null) {
            writer.write("Deposit not found");
        } else writer.write(deposit.toString());
    }

    private void sendDepositsByClientId(int id, HttpServletResponse resp) throws IOException, SQLException{
        PrintWriter writer = resp.getWriter();
        writer.write("List of deposits: \n");
        List<Deposit> deposits = crud.readDepositsById(id);
        if (!deposits.isEmpty()) {
            for (Deposit deposit: deposits) {
                writer.write(deposit.toString() + "\n");
            }
        } else writer.write("Deposits not found!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1 && "create".equals(pathParts[1])) {
                try {
                    createDeposit(req, resp);
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

    private void createDeposit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String clientId = req.getParameter("clientId");
        String sum = req.getParameter("sum");
        String percent = req.getParameter("percent");
        String period = req.getParameter("period");
        String topUp = req.getParameter("topUp");
        String withdraw = req.getParameter("withdraw");
        if (clientId != null && !clientId.isEmpty() && sum != null && !sum.isEmpty() &&
                percent != null && !percent.isEmpty() && topUp != null && !topUp.isEmpty() &&
                period != null && !period.isEmpty() && withdraw != null && !withdraw.isEmpty()) {
            crud.createDeposit(Integer.parseInt(clientId), Double.parseDouble(sum),
                    Double.parseDouble(percent), period, Boolean.parseBoolean(topUp), Boolean.parseBoolean(withdraw));
            resp.getWriter().write("Deposit created successfully.");
        } else {
            resp.getWriter().write("Invalid parameters for creating a deposit.");
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
                    updateDepositById(id, req, resp);
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

    private void updateDepositById(int id, HttpServletRequest req, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        Deposit deposit = crud.readDepositById(id);
        if (deposit != null) {
            // Достаем параметры из запроса и обновляем клиента
            String newSum = req.getParameter("sum");
            String newPercent = req.getParameter("percent");
            crud.updateDeposit(id, Double.parseDouble(newSum), Double.parseDouble(newPercent));
            writer.write("Deposit updated successfully.");
        } else {
            writer.write("Deposit not found or could not be updated.");
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
                    deleteDepositById(id, resp);
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

    private void deleteDepositById(int id, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        boolean deleted = crud.deleteDeposit(id);
        if (deleted) {
            writer.write("Deposit deleted successfully.");
        } else {
            writer.write("Deposit not found or could not be deleted.");
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
