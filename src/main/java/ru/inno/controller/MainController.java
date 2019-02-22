package ru.inno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.services.SearchService;

@Controller
@RequestMapping("/")
public class MainController {

    private SearchService searchService;


    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mainPageGET(ModelAndView modelAndView) {
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
}
