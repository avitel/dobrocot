package ru.inno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;
import ru.inno.service.CabinetService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private static final Logger log = LoggerFactory.getLogger(CabinetController.class);

    private CabinetService cabinetService;

    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cabinetGET(ModelAndView modelAndView) {
        Person currentPerson = cabinetService.getCurrentPerson();
        List<Car> listCar = cabinetService.getCarList(currentPerson);
        List<Order> customerOrders = cabinetService.getCustomerOrders(currentPerson);
        List<Order> sellerOrders = cabinetService.getSellerOrders(currentPerson);

        log.info("Person {} - cars list {} ", currentPerson.getName(), listCar);
        log.info("Seller orders list {} ", customerOrders);
        log.info("Customer orders list {} ", sellerOrders);

        modelAndView.addObject("carList", listCar);
        modelAndView.addObject("customerOrders", customerOrders);
        modelAndView.addObject("sellerOrders", sellerOrders);
        modelAndView.addObject("currentPerson", currentPerson);
        modelAndView.setViewName("cabinet");
        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView deleteCar(@RequestParam(name = "car_id") String car_id, ModelAndView modelAndView) {
        int carId = Integer.parseInt(car_id);
        log.info("Car id - {}, deleted - {}",carId,cabinetService.deleteCar(carId));
        return cabinetGET(modelAndView);
    }
}
