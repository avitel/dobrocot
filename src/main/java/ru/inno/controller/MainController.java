package ru.inno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.service.MainService;

@Controller
@RequestMapping("/")
public class MainController {

    public static final String USER_ATTRIBUTE = "user";
    public static final String FILTER_OPTION_ATTRIBUTE = "filterOption";
    public static final String RESULT_ATTRIBUTE = "result";

    public static final String MARK_REQUEST = "mark";
    public static final String MODEL_REQUEST = "model";
    public static final String ENGINE_REQUEST = "engine";
    public static final String COLOR_REQUEST = "color";

    public static final String MAINPAGE = "mainpage";

    private MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    public ModelAndView mainPageGET(ModelAndView modelAndView) {
        modelAndView.addObject(USER_ATTRIBUTE, null == mainService.getCurrentPerson() ? null : mainService.getCurrentPerson().getName());
        modelAndView.addObject(FILTER_OPTION_ATTRIBUTE, mainService.getAllFilterOption());
        modelAndView.addObject(RESULT_ATTRIBUTE, mainService.getFilteredCars(null, null, null, null));
        modelAndView.setViewName(MAINPAGE);
        return modelAndView;

    }

    @PostMapping
    public ModelAndView mainPagePOST(@RequestParam(name = MARK_REQUEST, required = false) String makr,
                                     @RequestParam(name = MODEL_REQUEST, required = false) String model,
                                     @RequestParam(name = ENGINE_REQUEST, required = false) String engine,
                                     @RequestParam(name = COLOR_REQUEST, required = false) String color,
                                     ModelAndView modelAndView) {

        modelAndView.addObject(USER_ATTRIBUTE, null == mainService.getCurrentPerson() ? null : mainService.getCurrentPerson().getName());
        modelAndView.addObject(FILTER_OPTION_ATTRIBUTE, mainService.getAllFilterOption());
        modelAndView.addObject(RESULT_ATTRIBUTE, mainService.getFilteredCars(
                null == makr ? null : Integer.valueOf(makr),
                null == model ? null : Integer.valueOf(model),
                null == engine ? null : Integer.valueOf(engine),
                null == color ? null : Integer.valueOf(color)
        ));
        modelAndView.setViewName(MAINPAGE);
        return modelAndView;
    }

}
