package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.ConnectionManager;
import ru.inno.dao.CarDAO;
import ru.inno.dao.CarImpl;
import ru.inno.dao.OrderDAO;
import ru.inno.dao.OrderImpl;
import ru.inno.service.CarReservationService;
import ru.inno.service.ReservableService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class CarReservationController {
    ReservableService reservableService;

    @Autowired
    CarReservationController(ReservableService reservableService){
        this.reservableService = reservableService;
    }

    @RequestMapping(value = "/carreserve",method = RequestMethod.POST)
//public String doReserve(Model model,@RequestParam(name = "car_id",required = true) String car_id) {
    public String doReserve(Model model,@RequestParam(name = "car_id") String car_id) {
        Map<String,String> carParams = reservableService.getValues(car_id);
        for (String s : carParams.keySet()) {
            model.addAttribute(s, carParams.get(s));
        }

        return "carreserve";

    }

    @RequestMapping(value = "/carreserve3",method = RequestMethod.POST)
//public String doReserve(Model model,@RequestParam(name = "car_id",required = true) String car_id) {
    public String doReserve2(Model model,@RequestParam(name = "date_begin") Date date_begin, @RequestParam(name = "date_end") Date date_end) {
        System.out.println(date_begin);
        System.out.println(date_end);
        System.out.println(date_end.getTime()-date_begin.getTime());
        int days = (int)((date_end.getTime()-date_begin.getTime())/86400000);
        model.addAttribute("days", days);
        model.addAttribute("cost", 100*days);

        return "carreserve";

    }

    @RequestMapping(value = "/carreserve2",method = RequestMethod.POST)
//public String doReserve(Model model,@RequestParam(name = "car_id",required = true) String car_id) {
    public String doReserve(Model model) {
        Connection c = ConnectionManager.getConnection();
        OrderDAO order = new OrderImpl(c);
//        order.addOrder(5,5,5, Timestamp.valueOf(LocalDateTime.now()));
        order.addOrder(5,5,5, new Timestamp(System.currentTimeMillis()));
        ConnectionManager.closeConnection(c);
        return "carreserved";

    }
}
