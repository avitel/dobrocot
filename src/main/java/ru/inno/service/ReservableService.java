package ru.inno.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ReservableService {
    public Map getValues(String ... requestParams);
}
