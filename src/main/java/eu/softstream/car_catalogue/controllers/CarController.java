package eu.softstream.car_catalogue.controllers;

import eu.softstream.car_catalogue.DAO.CarEntity;
import eu.softstream.car_catalogue.DTO.CarDTO;
import eu.softstream.car_catalogue.exceptions.BrandNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarColorNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;
import eu.softstream.car_catalogue.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public Set<CarDTO> getAllCars(){
        return CarDTO.fromCarEntitySet(carService.getAllCars());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable("carId") int carId){
        try {
            return new ResponseEntity<>(
                    CarDTO.fromCarEntity(carService.getCarById(carId)),
                    HttpStatus.OK
            );
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/colour/{colorName}")
    public ResponseEntity<?> getCarsByColorName(@PathVariable("colorName") String colorName){
        try {
            return new ResponseEntity<>(
                    CarDTO.fromCarEntitySet(carService.getCarsByColorName(colorName)),
                    HttpStatus.OK
            );
        } catch (CarColorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/engine-capacity/{engine-capacity}")
    public Set<CarDTO> getCarsByEngineCapacity(@PathVariable("engine-capacity") int engineCapacity){
        return CarDTO.fromCarEntitySet(carService.getCarsByEngineCapacity(engineCapacity));
    }

    @GetMapping("/date/{date}/from/{start-price}/to/{end-price}")
    public ResponseEntity<?> getCarsByPriceAndDateBetween(@PathVariable("date") String date,
                                                    @PathVariable(value = "start-price", required = false) Optional<Integer> startPrice,
                                                    @PathVariable(value = "end-price",required = false) Optional<Integer> endPrice){
        try {
            if(startPrice.isEmpty() && endPrice.isEmpty()){
                return new ResponseEntity<>(
                        CarDTO.fromCarEntitySet(carService.getCarsByPriceAndDateBetween(date)),
                        HttpStatus.OK
                );
            }
            if(startPrice.isEmpty()){
                return new ResponseEntity<>(
                        CarDTO.fromCarEntitySet(carService.getCarsByPriceAndDateBetween(date, endPrice.get())),
                        HttpStatus.OK
                );
            }
            if(endPrice.isEmpty()){
                return new ResponseEntity<>(
                        CarDTO.fromCarEntitySet(carService.getCarsByPriceAndDateBetween(startPrice.get(), date)),
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    CarDTO.fromCarEntitySet(
                            carService.getCarsByPriceAndDateBetween(startPrice.get(), date, endPrice.get())
                    ),
                    HttpStatus.OK
            );

        } catch (ParseException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CarEntity car){
        try {
            return new ResponseEntity<>(
                    CarDTO.fromCarEntity(carService.createCar(car)),
                    HttpStatus.OK
            );
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{carId}")
    public ResponseEntity<?> updateCarById(@PathVariable("carId") int carId,
                                           @RequestBody CarEntity car){
        try {
            return new ResponseEntity<>(
                    CarDTO.fromCarEntity(carService.updateCarById(carId, car)),
                    HttpStatus.OK
            );
        } catch (CarNotFoundException | BrandNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCarById(@PathVariable("carId") int carId){
        try {
            carService.deleteCarById(carId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
