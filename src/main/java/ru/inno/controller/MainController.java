package ru.inno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.service.MainService;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    public ModelAndView mainPageGET(ModelAndView modelAndView) {
        modelAndView.addObject("user", null == mainService.getCurrentPerson() ? null : mainService.getCurrentPerson().getName());
        modelAndView.addObject("filterOption", mainService.getAllFilterOption());
        modelAndView.addObject("result", mainService.getFilteredCars(null, null, null, null));
        modelAndView.setViewName("mainpage");
        return modelAndView;

    }

    @PostMapping
    public ModelAndView mainPagePOST(@RequestParam(name = "mark", required = false) String makr,
                                     @RequestParam(name = "model", required = false) String model,
                                     @RequestParam(name = "engine", required = false) String engine,
                                     @RequestParam(name = "color", required = false) String color,
                                     ModelAndView modelAndView) {

        modelAndView.addObject("user", null == mainService.getCurrentPerson() ? null : mainService.getCurrentPerson().getName());
        modelAndView.addObject("filterOption", mainService.getAllFilterOption());
        modelAndView.addObject("result", mainService.getFilteredCars(
                null == makr ? null : Integer.valueOf(makr),
                null == model ? null : Integer.valueOf(model),
                null == engine ? null : Integer.valueOf(engine),
                null == color ? null : Integer.valueOf(color)
        ));
        modelAndView.setViewName("mainpage");
        return modelAndView;
    }

}
