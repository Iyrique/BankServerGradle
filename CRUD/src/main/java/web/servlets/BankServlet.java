package web.servlets;

import connection.ConnectDB;
import crud.CRUD;
import entity.Bank;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bank")
public class BankServlet extends HttpServlet {

    private CRUD crud;

    public BankServlet() throws SQLException {
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
        try {
            sendAllBanks(resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void sendAllBanks(HttpServletResponse response) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();
        List<Bank> banks = new ArrayList<>();
        banks = crud.getAllBanks();
        writer.write("List of banks:\n");
        for (Bank bank : banks) {
            writer.write(bank.toString() + "\n");
        }
    }
}
