package eu.softstream.car_catalogue.controllers;

import eu.softstream.car_catalogue.DAO.BrandEntity;
import eu.softstream.car_catalogue.DAO.CarEntity;
import eu.softstream.car_catalogue.DTO.BrandDTO;
import eu.softstream.car_catalogue.DTO.CarDTO;
import eu.softstream.car_catalogue.exceptions.BrandNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;
import eu.softstream.car_catalogue.services.BrandService;
import eu.softstream.car_catalogue.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    private final CarService carService;

    @GetMapping
    public Set<BrandDTO> getAllBrands(){
        return BrandDTO.fromBrandEntitySet(brandService.getAllBrands());
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<?> getBrandById(@PathVariable("brandId") int brandId){
        try {
            return new ResponseEntity<>(
                    BrandDTO.fromBrandEntity(brandService.getBrandById(brandId)),
                    HttpStatus.OK
            );
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{brandId}/cars")
    public ResponseEntity<?> getCarsByBrandById(@PathVariable("brandId") int brandId){
        try {
            return new ResponseEntity<>(
                    CarDTO.fromCarEntitySet(carService.getCarsByBrandId(brandId)),
                    HttpStatus.OK
            );
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public BrandDTO createBrand(@RequestBody BrandEntity brand){
        return BrandDTO.fromBrandEntity(brandService.createBrand(brand));
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<?> updateBrandById(@PathVariable("brandId") int brandId,
                                           @RequestBody BrandEntity brand){
        try {
            return new ResponseEntity<>(
                    BrandDTO.fromBrandEntity(brandService.updateBrandById(brandId, brand)),
                    HttpStatus.OK
            );
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<?> deleteCarById(@PathVariable("brandId") int brandId){
        try {
            brandService.deleteBrandById(brandId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
