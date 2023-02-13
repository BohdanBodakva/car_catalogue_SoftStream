package eu.softstream.car_catalogue.services;

import eu.softstream.car_catalogue.DAO.CarEntity;
import eu.softstream.car_catalogue.exceptions.BrandNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarColorNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface CarService {
    Set<CarEntity> getAllCars();
    CarEntity getCarById(int carId) throws CarNotFoundException;
    Set<CarEntity> getCarsByColorName(String colorName) throws CarColorNotFoundException;
    Set<CarEntity> getCarsByEngineCapacity(int engineCapacity);
    Set<CarEntity> getCarsByBrandId(int brandId) throws BrandNotFoundException;
    CarEntity createCar(CarEntity car) throws BrandNotFoundException;
    CarEntity updateCarById(int carId, CarEntity car) throws CarNotFoundException, BrandNotFoundException;
    void deleteCarById(int carId) throws CarNotFoundException;
    List<String> getAllColors();





    Set<CarEntity> getCarsByPriceAndDateBetween(int startPrice, String date, int endPrice) throws ParseException;
    Set<CarEntity> getCarsByPriceAndDateBetween(int startPrice, String date) throws ParseException;
    Set<CarEntity> getCarsByPriceAndDateBetween(String date, int endPrice) throws ParseException;
    Set<CarEntity> getCarsByPriceAndDateBetween(String date) throws ParseException;
}
