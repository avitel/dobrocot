package ru.inno.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.ConnectionController;
import ru.inno.services.RegistrationService;
import ru.inno.services.SearchService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlete_in");


        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/registration.jsp");
        dispatcher.forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlete_out");

       RegistrationService regService = new RegistrationService();

        regService.addUser(req.getParameter("name"), req.getParameter("login"), req.getParameter("pass"));

        req.getRequestDispatcher("").forward(req, resp);
    }
}
