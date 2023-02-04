package eu.softstream.car_catalogue.DTO;

import eu.softstream.car_catalogue.DAO.CarPriceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CarPriceDTO {
    private int id;
    private Integer car;
    private LocalDate date;
    private int price;

    private static String convertDateIntoString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    public static CarPriceDTO fromCarPriceEntity(CarPriceEntity carPriceEntity){
        return new CarPriceDTO(
                carPriceEntity.getId(),
                carPriceEntity.getCar().getId(),
                carPriceEntity.getDate(),
                carPriceEntity.getPrice()
        );
    }

    public static Set<CarPriceDTO> fromCarPriceEntitySet(Set<CarPriceEntity> carPriceEntities){
        return carPriceEntities.stream()
                .map(CarPriceDTO::fromCarPriceEntity)
                .collect(Collectors.toSet());
    }
}
