package eu.softstream.car_catalogue.DTO;

import eu.softstream.car_catalogue.DAO.BrandEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class SimpleBrandDto {
    private int id;
    private String name;

    public static SimpleBrandDto fromSimpleBrandEntity(BrandEntity brandEntity){
        return new SimpleBrandDto(
                brandEntity.getId(),
                brandEntity.getName()
        );
    }

    public static Set<SimpleBrandDto> fromSimpleBrandEntitySet(Set<BrandEntity> brandEntities){
        return brandEntities.stream()
                .map(SimpleBrandDto::fromSimpleBrandEntity)
                .collect(Collectors.toSet());
    }
}
