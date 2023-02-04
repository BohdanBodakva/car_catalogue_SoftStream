package eu.softstream.car_catalogue.services.implementations;

import eu.softstream.car_catalogue.DAO.CarEntity;
import eu.softstream.car_catalogue.DAO.CarPriceEntity;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarPriceNotFoundException;
import eu.softstream.car_catalogue.repositories.CarPriceRepository;
import eu.softstream.car_catalogue.repositories.CarRepository;
import eu.softstream.car_catalogue.services.CarPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarPriceServiceImpl implements CarPriceService {
    private final CarPriceRepository carPriceRepository;
    private final CarRepository carRepository;

    @Override
    public Set<CarPriceEntity> getAllCarPricesByCarId(int carId) throws CarNotFoundException {
        carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with id = " + carId + " doesn't exist"));

        return new HashSet<>(carPriceRepository.findAllByCarId(carId));
    }

    private Date getDateFromString(String date) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }

    @Override
    public CarPriceEntity getCarPriceByCarIdAndDate(int carId, String date) throws CarPriceNotFoundException, CarNotFoundException, ParseException {
        carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with id = " + carId + " doesn't exist"));

        Date date1 = getDateFromString(date);

        return carPriceRepository.findByCarIdAndDate(carId, date1)
                .orElseThrow(() -> new CarPriceNotFoundException("Car price with carId = " + carId +
                        " and date = " + date1.toString() + " doesn't exist"));
    }

    private void updateCurrentCarPriceByCarId(CarPriceEntity carPrice) throws CarNotFoundException {
        CarEntity car = carRepository.findById(carPrice.getCar().getId())
                .orElseThrow(() -> new CarNotFoundException(
                        "Car with id = " + carPrice.getCar().getId() + " doesn't exist"
                        )
                );

        car.setCurrentPrice(carPrice.getPrice());

        carRepository.save(car);
    }

    @Override
    public CarPriceEntity createCarPrice(CarPriceEntity carPrice) throws CarNotFoundException {
        updateCurrentCarPriceByCarId(carPrice);

        int carId = carPrice.getCar().getId();
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with id = " + carId +  " doesn't exist"));
        car.getPrices().add(carPrice);

        carRepository.save(car);
        return carPriceRepository.save(carPrice);
    }
}
