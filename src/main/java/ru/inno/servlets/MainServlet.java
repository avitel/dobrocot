package ru.inno.servlets;

import ru.inno.ConnectionController;
import ru.inno.dao.*;
import ru.inno.entity.Color;
import ru.inno.entity.Engine;
import ru.inno.entity.Mark;
import ru.inno.entity.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionController connectionController = ConnectionController.createController();
        connectionController.setConnection();

        MarkDAO markDAO = new MarkImpl(connectionController.getConnection());
        ModelDAO modelDAO = new ModelImpl(connectionController.getConnection());
        ColorDAO colorDAO = new ColorImpl(connectionController.getConnection());
        EngineDAO engineDAO = new EngineImpl(connectionController.getConnection());

        Collection<Mark> marks = markDAO.getMarks();
        Collection<Model> models = modelDAO.getModels();
        Collection<Color> colors = colorDAO.getColors();
        Collection<Engine> engines = engineDAO.getEngines();

        req.setAttribute("marks", marks);
        req.setAttribute("models", models);
        req.setAttribute("colors", colors);
        req.setAttribute("engines", engines);

        RequestDispatcher dispatcher = req.getRequestDispatcher("indexmain.jsp");
        dispatcher.forward(req, resp);
    }
}
