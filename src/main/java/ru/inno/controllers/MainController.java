package ru.inno.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.services.SearchService;

@Controller
@RequestMapping("/")
public class MainController {

    private SearchService searchService;


    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String mainPageGET(Model springModel) {

        springModel.addAttribute("filterOption", searchService.getFilterOption());
        springModel.addAttribute("result", searchService.getFilteredCars(null, null, null, null));

        return "mainpage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String mainPagePOST(@RequestParam(name = "mark", required = false) String makr,
                               @RequestParam(name = "model", required = false) String model,
                               @RequestParam(name = "engine", required = false) String engine,
                               @RequestParam(name = "color", required = false) String color,
                               Model springModel) {
        springModel.addAttribute("filterOption", searchService.getFilterOption());
        springModel.addAttribute("result", searchService.getFilteredCars(
                null == makr ? null : Integer.valueOf(makr),
                null == model ? null : Integer.valueOf(model),
                null == engine ? null : Integer.valueOf(engine),
                null == color ? null : Integer.valueOf(color)
        ));
        return "mainpage";
    }
}
