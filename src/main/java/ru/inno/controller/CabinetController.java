package ru.inno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.dao.ColorImpl;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;
import ru.inno.services.CabinetService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private static final Logger log = LoggerFactory.getLogger(ColorImpl.class);

    private CabinetService cabinetService;

    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cabinetGET(ModelAndView modelAndView) {
        Person currentPerson = cabinetService.getCurrentPerson();
        List<Car> listCar = cabinetService.getCarList(currentPerson);
        List<Order> listOrder = cabinetService.getCustomerOrders(currentPerson);

        log.info("Person {} - cars list {} ", currentPerson.getName(), listCar);
        log.info("Person {} - orders list {} ", currentPerson.getName(), listOrder);

        modelAndView.addObject("carList", listCar);
        modelAndView.addObject("customerOrders", listOrder);
        modelAndView.addObject("currentPerson", currentPerson);
        modelAndView.setViewName("cabinet");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView cabinetPOST(ModelAndView modelAndView) {
        return modelAndView;
    }
}
