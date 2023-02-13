package eu.softstream.car_catalogue.controllers;

import eu.softstream.car_catalogue.DTO.CarDTO;
import eu.softstream.car_catalogue.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/colors")
@RequiredArgsConstructor
public class ColorController {
    private final CarService carService;

    @GetMapping
    public List<String> getAllColors(){
        return carService.getAllColors();
    }
}
