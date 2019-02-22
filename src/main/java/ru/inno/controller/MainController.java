package ru.inno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.ConnectionManager;
import ru.inno.Security;
import ru.inno.dao.PersonImpl;
import ru.inno.services.SearchService;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private SearchService searchService;

    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mainPageGET(ModelAndView modelAndView) {
        if (!addUser(modelAndView)) return modelAndView;
        modelAndView.addObject("filterOption", searchService.getFilterOption());
            modelAndView.addObject("result", searchService.getFilteredCars(null, null, null, null));
            modelAndView.setViewName("mainpage");
            return modelAndView;

    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView mainPagePOST(@RequestParam(name = "mark", required = false) String makr,
                                     @RequestParam(name = "model", required = false) String model,
                                     @RequestParam(name = "engine", required = false) String engine,
                                     @RequestParam(name = "color", required = false) String color,
                                     ModelAndView modelAndView) {

        if (!addUser(modelAndView)) return modelAndView;
        modelAndView.addObject("filterOption", searchService.getFilterOption());
        modelAndView.addObject("result", searchService.getFilteredCars(
                null == makr ? null : Integer.valueOf(makr),
                null == model ? null : Integer.valueOf(model),
                null == engine ? null : Integer.valueOf(engine),
                null == color ? null : Integer.valueOf(color)
        ));
        modelAndView.setViewName("mainpage");
        return modelAndView;
    }

    private boolean addUser(ModelAndView modelAndView) {
        try (Connection connection = ConnectionManager.getConnection()) {
            Security security = new Security(SecurityContextHolder.getContext(), new PersonImpl(connection));
            modelAndView.addObject("user", null == security.getCurrentUser() ? null : security.getCurrentUser().getName());
        } catch (SQLException e) {
            LOGGER.error("MainController.mainPage();", e);
            modelAndView.addObject("error", "Произошла непредвиденная ошибка :( ");
            modelAndView.setViewName("error");
            return false;
        }
        return true;
    }


}
