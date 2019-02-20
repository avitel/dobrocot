package ru.inno.servlets;

import ru.inno.ConnectionManager;
import ru.inno.dao.*;
import ru.inno.entity.*;
import ru.inno.services.SearchService;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {
    private SearchService searchService = new SearchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("filterOption", searchService.getFilterOption());
        // TODO: 18.02.2019 переписать для всех null
        req.setAttribute("result", searchService.getFilteredCars(null, null, null, null));
        RequestDispatcher dispatcher = req.getRequestDispatcher("indexmain.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("filterOption", searchService.getFilterOption());
        req.setAttribute("result", searchService.getFilteredCars(

                null == req.getParameter("mark") ? null : Integer.valueOf(req.getParameter("mark")),
                null == req.getParameter("model") ? null : Integer.valueOf(req.getParameter("model")),
                null == req.getParameter("engine") ? null : Integer.valueOf(req.getParameter("engine")),
                null == req.getParameter("color") ? null : Integer.valueOf(req.getParameter("color"))
        ));
        req.getRequestDispatcher("indexmain.jsp").forward(req, resp);
    }
}
