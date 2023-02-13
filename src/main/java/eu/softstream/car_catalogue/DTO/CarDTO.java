package eu.softstream.car_catalogue.DTO;

import eu.softstream.car_catalogue.DAO.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
public class CarDTO {
    private int id;
    private String model;
    private String color;
    private int engineCapacity;
    private int currentPrice;
    private SimpleBrandDto brand;
    private String description;
    private Set<Map<LocalDate, Integer>> prices;

    public static CarDTO fromCarEntity(CarEntity carEntity){
        return new CarDTO(
                carEntity.getId(),
                carEntity.getModel(),
                carEntity.getColor().name(),
                carEntity.getEngineCapacityInLiters(),
                carEntity.getCurrentPrice(),
                SimpleBrandDto.fromSimpleBrandEntity(carEntity.getBrand()),
                carEntity.getDescription(),
                carEntity.getPrices().stream().map(
                        price -> Map.of(price.getDate(), price.getPrice())
                        )
                        .collect(Collectors.toSet())
        );
    }

    public static Set<CarDTO> fromCarEntitySet(Set<CarEntity> carEntities){
        return carEntities.stream()
                .map(CarDTO::fromCarEntity)
                .collect(Collectors.toSet());
    }
}
