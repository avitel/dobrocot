package ru.inno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.inno.dao.PersonDAO;
import ru.inno.entity.Person;

public class Security {

    private SecurityContext securityContext;
    private PersonDAO dao;

    public Security(SecurityContext securityContext, PersonDAO dao) {
        this.securityContext = securityContext;
        this.dao = dao;
    }

    public Person getCurrentUser(){

        String login ="";
        Authentication auth = securityContext.getAuthentication();
        if (auth != null){
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                login = ((UserDetails)principal).getUsername();
            } else {
                login = principal.toString();
            }
        }

        return dao.getPerson(login);
    }
}
