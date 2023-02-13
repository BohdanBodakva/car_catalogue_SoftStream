package eu.softstream.car_catalogue.services.implementations;

import eu.softstream.car_catalogue.DAO.BrandEntity;
import eu.softstream.car_catalogue.DAO.CarEntity;
import eu.softstream.car_catalogue.DAO.CarPriceEntity;
import eu.softstream.car_catalogue.DAO.enums.CarColor;
import eu.softstream.car_catalogue.exceptions.BrandNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarColorNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;
import eu.softstream.car_catalogue.repositories.BrandRepository;
import eu.softstream.car_catalogue.repositories.CarPriceRepository;
import eu.softstream.car_catalogue.repositories.CarRepository;
import eu.softstream.car_catalogue.services.CarPriceService;
import eu.softstream.car_catalogue.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarPriceService carPriceService;
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CarPriceRepository carPriceRepository;

    @Override
    public Set<CarEntity> getAllCars() {
        return new HashSet<>(carRepository.findAll());
    }

    @Override
    public CarEntity getCarById(int carId) throws CarNotFoundException {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with id = " + carId + " doesn't exist"));
    }

    private boolean checkIfCarColorIsExisting(String colorName){
        for(CarColor color : CarColor.values()){
            if(color.name().toLowerCase().equals(colorName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<CarEntity> getCarsByColorName(String colorName) throws CarColorNotFoundException {
        if(checkIfCarColorIsExisting(colorName)){
            return carRepository.findAll().stream()
                    .filter(car -> car.getColor().name().toLowerCase().equals(colorName))
                    .collect(Collectors.toSet());
        }

        throw new CarColorNotFoundException("Car colour '" + colorName + "' doesn't exist");
    }

    @Override
    public Set<CarEntity> getCarsByEngineCapacity(int engineCapacity) {
        return carRepository.findAll().stream()
                .filter(car -> car.getEngineCapacityInLiters() == engineCapacity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CarEntity> getCarsByBrandId(int brandId) throws BrandNotFoundException {
        brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand with id = " + brandId + " doesn't exist"));

        return new HashSet<>(carRepository.findByBrandId(brandId));
    }

    @Override
    public CarEntity createCar(CarEntity car) throws BrandNotFoundException {

        int brandId = car.getBrand().getId();
        BrandEntity brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand with id = " + brandId + " doesn't exist"));
        brand.addCar(car);

        CarEntity savedCar = carRepository.save(car);

        brandRepository.save(brand);
        carPriceRepository.save(new CarPriceEntity(savedCar, LocalDate.now(), car.getCurrentPrice()));
        return savedCar;
    }

    @Override
    public CarEntity updateCarById(int carId, CarEntity car) throws CarNotFoundException, BrandNotFoundException {
        CarEntity carToUpdate = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with id = " + carId + " doesn't exist"));

        carToUpdate.setModel(car.getModel());
        carToUpdate.setColor(car.getColor());
        carToUpdate.setEngineCapacityInLiters(car.getEngineCapacityInLiters());
        carToUpdate.setDescription(car.getDescription());

        if(car.getBrand().getId() != carToUpdate.getBrand().getId()){
            carToUpdate.getBrand().getCars().remove(carToUpdate);

            int brandId = car.getBrand().getId();
            BrandEntity brand = brandRepository.findById(brandId)
                    .orElseThrow(() -> new BrandNotFoundException("Brand with id = " + brandId + " doesn't exist"));
            carToUpdate.setBrand(brand);

            brand.addCar(carToUpdate);
            brandRepository.save(brand);
        }

        if(car.getCurrentPrice() != carToUpdate.getCurrentPrice()) {
            carToUpdate.setCurrentPrice(car.getCurrentPrice());

            Set<CarPriceEntity> carPricesToUpdate = carPriceService.getAllCarPricesByCarId((carToUpdate.getId()));
            for(CarPriceEntity carPrice: carPricesToUpdate){
                carPrice.setDate(carPrice.getDate().minusDays(1));
                carPriceRepository.save(carPrice);
            }

            carPriceRepository.save(new CarPriceEntity(carToUpdate, LocalDate.now(), car.getCurrentPrice()));
        }

        return carRepository.save(carToUpdate);
    }

    @Override
    public void deleteCarById(int carId) throws CarNotFoundException {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with id = " + carId + " doesn't exist"));

        car.getBrand().removeCar(car);

        carRepository.deleteById(carId);
    }

    @Override
    public List<String> getAllColors() {
        return Arrays.stream(CarColor.values()).map(Enum::name).collect(Collectors.toList());
    }

    // ==================================================================

    private Date getDateFromString(String date) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }

    @Override
    public Set<CarEntity> getCarsByPriceAndDateBetween(int startPrice, String date, int endPrice) throws ParseException {
        Date date1 = getDateFromString(date);

        return new HashSet<>(
                carRepository.findCarsByPriceAndDateBetween(startPrice, date1, endPrice)
        );
    }

    @Override
    public Set<CarEntity> getCarsByPriceAndDateBetween(int startPrice, String date) throws ParseException {
        Date date1 = getDateFromString(date);

        return new HashSet<>(
                carRepository.findCarsByPriceAndDateBetween(startPrice, date1)
        );
    }

    @Override
    public Set<CarEntity> getCarsByPriceAndDateBetween(String date, int endPrice) throws ParseException {
        Date date1 = getDateFromString(date);

        return new HashSet<>(
                carRepository.findCarsByPriceAndDateBetween(date1, endPrice)
        );
    }

    @Override
    public Set<CarEntity> getCarsByPriceAndDateBetween(String date) throws ParseException {
        Date date1 = getDateFromString(date);

        return new HashSet<>(
                carRepository.findCarsByPriceAndDateBetween(date1)
        );
    }





}
