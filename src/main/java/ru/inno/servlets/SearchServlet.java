package ru.inno.servlets;

import ru.inno.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private SearchService searchService = new SearchService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = searchService.search(new String[]{
            req.getParameter("mark"),req.getParameter("model"),req.getParameter("color"),
            req.getParameter("engine"),req.getParameter("places")}
        );
        req.setAttribute("result", result);
        req.getRequestDispatcher("search.jsp").forward(req, resp);


    }
}
