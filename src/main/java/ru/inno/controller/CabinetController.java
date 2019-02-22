package ru.inno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.services.CabinetService;
import ru.inno.services.SearchService;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private CabinetService cabinetService;


    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cabinetGET(ModelAndView modelAndView) {
        modelAndView.addObject("carList", cabinetService.getCarList());
        modelAndView.addObject("orderList", cabinetService.getOrderList());
        modelAndView.setViewName("cabinet");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView cabinetPOST(
            ModelAndView modelAndView) {

        return modelAndView;
    }
}
