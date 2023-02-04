package eu.softstream.car_catalogue.DTO;

import eu.softstream.car_catalogue.DAO.BrandEntity;
import eu.softstream.car_catalogue.DAO.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BrandDTO {
    private int id;
    private String name;
    private Set<CarDTO> cars;

    public static BrandDTO fromBrandEntity(BrandEntity brandEntity){
        return new BrandDTO(
                brandEntity.getId(),
                brandEntity.getName(),
                brandEntity.getCars().stream()
                        .map(CarDTO::fromCarEntity)
                        .collect(Collectors.toSet())
        );
    }

    public static Set<BrandDTO> fromBrandEntitySet(Set<BrandEntity> brandEntities){
        return brandEntities.stream()
                .map(BrandDTO::fromBrandEntity)
                .collect(Collectors.toSet());
    }
}
