package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.inno.services.AddNewCarService;
import java.sql.Timestamp;

@Controller
@RequestMapping("/addnewcar")
public class AddNewCarController {
    @Autowired
    AddNewCarService addNewCarService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(Model model) {
        model.addAttribute("param", addNewCarService.getParam());
        return "addnewcar";
    }

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView addNewCar(@RequestParam(name = "mark", required = false) String mark,
                            @RequestParam(name = "model", required = false) String model,
                            @RequestParam(name = "assembleDate", required = false) Timestamp assembledate,
                            @RequestParam(name = "engine", required = false) String engine,
                            @RequestParam(name = "numbeerofseats ", required = false) String numbeerofseats,
                            @RequestParam(name = "color", required = false) String color,
                            Model carModel) {
        addNewCarService.addCar(mark, model, assembledate, engine, numbeerofseats, color);


        carModel.addAttribute("message","car added" );
//        carModel.addAttribute("addcar", addNewCarService.addNewCar(mark,
//                model, assembledate, engine, numbeerofseats, color));


        return new RedirectView("/");
    }


}
