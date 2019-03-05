package ru.inno.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("messageText", "Нет такого пользователя");
        model.addAttribute("modalStyle", "display: block");
        return "login";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
