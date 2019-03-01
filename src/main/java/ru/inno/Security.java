package ru.inno;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.inno.dao.PersonDAO;
import ru.inno.entity.Person;


@Component
public class Security {

    private PersonDAO dao;

    public Security(PersonDAO dao) {
        this.dao = dao;
    }

    public Person getCurrentUser(){

        String login ="";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                login = ((UserDetails)principal).getUsername();
            } else {
                login = principal.toString();
            }
        }

        if ("anonymousUser".equals(login)) {
            return null;
        }

        return dao.getPerson(login);
    }
}
