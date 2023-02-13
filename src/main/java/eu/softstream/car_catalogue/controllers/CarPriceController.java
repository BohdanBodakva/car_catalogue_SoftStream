package eu.softstream.car_catalogue.controllers;

import eu.softstream.car_catalogue.DAO.CarPriceEntity;
import eu.softstream.car_catalogue.DTO.CarPriceDTO;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarPriceNotFoundException;
import eu.softstream.car_catalogue.services.CarPriceService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/api/car-price")
@RequiredArgsConstructor
public class CarPriceController {
    private final CarPriceService carPriceService;

    @GetMapping("/{carId}")
    public ResponseEntity<?> getCarPricesByCarId(@PathVariable("carId") int carId){
        try {
            return new ResponseEntity<>(
                    CarPriceDTO.fromCarPriceEntitySet(carPriceService.getAllCarPricesByCarId(carId)),
                    HttpStatus.OK
            );
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{carId}/{date}")
    public ResponseEntity<?> getCarPriceByCarIdAndDate(@PathVariable("carId") int carId,
                                                       @PathVariable("date") String date){
        try {
            return new ResponseEntity<>(
                    CarPriceDTO.fromCarPriceEntity(carPriceService.getCarPriceByCarIdAndDate(carId, date)),
                    HttpStatus.OK
            );
        } catch (CarPriceNotFoundException | CarNotFoundException | ParseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCarPrice(@RequestBody CarPriceEntity carPrice){
        try {
            return new ResponseEntity<>(
                    CarPriceDTO.fromCarPriceEntity(carPriceService.createCarPrice(carPrice)),
                    HttpStatus.OK
            );
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
