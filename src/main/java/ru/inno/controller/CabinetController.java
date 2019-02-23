package ru.inno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.entity.Order;
import ru.inno.entity.Person;
import ru.inno.services.CabinetService;
import ru.inno.services.SearchService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private CabinetService cabinetService;

    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cabinetGET(@RequestParam Integer id, ModelAndView modelAndView) {

        System.out.println("======= id ======== " + id);

        Person currentPerson = new Person(id,"","");
//        Person currentPerson = cabinetService.getCurrentPerson();
//        modelAndView.addObject("carList", cabinetService.getCarList(currentPerson));
        modelAndView.addObject("personid", id);
        List<Order> list =  cabinetService.getCustomerOrders(currentPerson);

        for (Order o: list){
            System.out.println(o.getOwner().getName());
            System.out.println(o.getCar().getMark());
            System.out.println(o.getDateOrder().toString());
        }

        modelAndView.addObject("customerOrders", list);
//        modelAndView.addObject("sellerOrders", cabinetService.getSellerOrders(currentPerson));
        modelAndView.setViewName("cabinet");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView cabinetPOST(ModelAndView modelAndView) {
        return modelAndView;
    }
}
