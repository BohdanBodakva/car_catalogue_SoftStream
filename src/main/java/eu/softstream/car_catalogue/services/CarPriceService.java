package eu.softstream.car_catalogue.services;

import eu.softstream.car_catalogue.DAO.CarPriceEntity;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarPriceNotFoundException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface CarPriceService {
    Set<CarPriceEntity> getAllCarPricesByCarId(int carId) throws CarNotFoundException;
    CarPriceEntity getCarPriceByCarIdAndDate(int carId, String date) throws CarPriceNotFoundException, CarNotFoundException, ParseException;
    CarPriceEntity createCarPrice(CarPriceEntity carPrice) throws CarNotFoundException;
}
