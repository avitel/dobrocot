package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.service.ReservableService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CarReservationController {
    private ReservableService reservableService;

    @Autowired
    CarReservationController(ReservableService reservableService) {
        this.reservableService = reservableService;
    }

    @RequestMapping(value = "/carreserve", method = RequestMethod.POST)
    public String doReserve(Model model, @RequestParam(name = "car_id") String car_id) {
        Map<String, String> carParams = reservableService.getValues(car_id);
        for (String s : carParams.keySet()) {
            model.addAttribute(s, carParams.get(s));
        }
        model.addAttribute("car_id", car_id);
        HashMap<String, String> mapDates = (HashMap<String, String>) reservableService.getReservedDates(car_id);
        model.addAttribute("dateReserved", mapDates);
        return "carreserve";
    }

    @RequestMapping(value = "/carreserve3", method = RequestMethod.POST)
    public String doReserve2(Model model, @RequestParam(name = "date_begin") String date_begin,
                             @RequestParam(name = "date_end") String date_end,
                             @RequestParam(name = "car_id") String car_id) {
        int days = 0;

        try {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            model.addAttribute("date_begin", date_begin);
            model.addAttribute("date_end", date_end);
            days = reservableService.getDays(formatter.parse(date_begin), formatter.parse(date_end));
        } catch (Exception e) {
            model.addAttribute("msgStyle", "w3-red");
            model.addAttribute("modalStyle", "display: block");
            model.addAttribute("submitMessage", "Неверно указаны даты");
            return doReserve(model, car_id);
        }

        model.addAttribute("jsScript","enableBtn(\"idSubmit\");");
        model.addAttribute("days", days);
        Map<String, String> carParams = reservableService.getValues(car_id);
        model.addAttribute("cost", Integer.parseInt(carParams.get("dayprice")) * days);
        model.addAttribute("car_id", car_id);
        return doReserve(model, car_id);
    }

    @RequestMapping(value = "/carreserve2", method = RequestMethod.POST)
    public String doReserve3(Model model,
                             @RequestParam(name = "date_begin") String date_begin,
                             @RequestParam(name = "date_end") String date_end,
                             @RequestParam(name = "car_id") String car_id,
                             @RequestParam(name = "id_owner") String id_owner,
                             @RequestParam(name = "price") String price) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("modalStyle","display: block");
        try {
            model.addAttribute("msgStyle", "w3-red");
            if (!reservableService.checkAvailableDate(formatter.parse(date_begin), formatter.parse(date_end), car_id)) {
                model.addAttribute("submitMessage", "Нельзя взять машину на эту дату!");
                return doReserve(model, car_id);
            }
            if (!reservableService.checkAvailableCustomer(Integer.parseInt(id_owner))) {
                model.addAttribute("submitMessage", "Невозможно арендовать свою же машину!");
                return doReserve(model, car_id);
            }
            reservableService.addReservationOrder(Integer.parseInt(car_id), Integer.parseInt(id_owner), formatter.parse(date_begin), formatter.parse(date_end), Integer.parseInt(price));
            model.addAttribute("submitMessage", "Машина зарезервирована!");
            model.addAttribute("msgStyle", "w3-green");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return doReserve(model, car_id);
    }
}
