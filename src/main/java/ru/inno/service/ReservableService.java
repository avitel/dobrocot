package ru.inno.service;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

@Service
public interface ReservableService {
    Map getValues(String ... requestParams);
    Map getReservedDates(String car_id);
    int getDays(Date d1, Date d2) throws Exception;
    boolean checkAvailableDate(Date d1, Date d2, String car_id);
    boolean checkAvailableCustomer(int id_seller);
    void addReservationOrder(int id_car, int id_owner, /*int id_customer,*/ Date date_begin, Date date_end, int price);
}
