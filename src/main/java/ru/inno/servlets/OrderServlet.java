package ru.inno.servlets;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.inno.ConnectionManager;
import ru.inno.Security;
import ru.inno.dao.PersonImpl;
import ru.inno.entity.Person;
import ru.inno.services.SearchService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private SearchService searchService = new SearchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo вынести в сервис

        Security security = new Security(SecurityContextHolder.getContext(), new PersonImpl(ConnectionManager.getConnection()));
        Person user = security.getCurrentUser();

        req.setAttribute("username", user.getName());

        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/order.jsp");
        dispatcher.forward(req, resp);
    }
}
