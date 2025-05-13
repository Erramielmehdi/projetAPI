package projetAPI.servlet;

import com.google.gson.Gson;
import projetAPI.client.dao.ClientDAO;
import projetAPI.client.model.Client;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet("/api/clients")
public class ClientServlet extends HttpServlet {
    private final ClientDAO clientDAO = new ClientDAO();
    private final Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Client> clients = clientDAO.list();
        String json = gson.toJson(clients);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = gson.fromJson(req.getReader(), Client.class);
        clientDAO.save(client);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"Client enregistré\"}");
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = gson.fromJson(req.getReader(), Client.class);
        clientDAO.save(client);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"Client mis à jour\"}");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        clientDAO.delete(id);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"Client supprimé\"}");
    }
}

