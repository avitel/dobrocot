package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.inno.ConnectionManager;
import ru.inno.Security;
import ru.inno.dao.PersonImpl;
import ru.inno.services.AddNewCarService;
import java.sql.Connection;

@Controller
@RequestMapping("/addnewcar")
public class AddNewCarController {

    private final AddNewCarService addNewCarService;
    private Security security;

    @Autowired
    public AddNewCarController(AddNewCarService addNewCarService, Security security) {
        this.addNewCarService = addNewCarService;
        this.security = security;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(Model model) {
        model.addAttribute("param", addNewCarService.getParam());
        return "addnewcar";
    }

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView addNewCar(@RequestParam(name = "mark", required = false) String mark,
                                  @RequestParam(name = "model", required = false) String model,
                                  @RequestParam(name = "assembleDate", required = false) String assembledate,
                                  @RequestParam(name = "engine", required = false) String engine,
                                  @RequestParam(name = "numbeerofseats ", required = false) String numbeerofseats,
                                  @RequestParam(name = "color", required = false) String color,
                                  Model carModel) {

        int getId = security.getCurrentUser().getId();

        addNewCarService.addCar(getId, mark, model, assembledate, engine, numbeerofseats, color);

        return new RedirectView("/cabinet");
    }


}
