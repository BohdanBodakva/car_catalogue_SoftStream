package eu.softstream.car_catalogue.DAO;

import eu.softstream.car_catalogue.DAO.enums.CarColor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private CarColor color;

    @Column(name = "engine_capacity_in_liters")
    private int engineCapacityInLiters;

    @Column(name = "current_price")
    private int currentPrice;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "car")
    private Set<CarPriceEntity> prices = new HashSet<>();

    public void addPrice(CarPriceEntity carPriceEntity){
        this.prices.add(carPriceEntity);
    }

    public CarEntity(String model, CarColor color, int engineCapacityInLiters, int currentPrice, BrandEntity brand, String description) {
        this.model = model;
        this.color = color;
        this.engineCapacityInLiters = engineCapacityInLiters;
        this.currentPrice = currentPrice;
        this.brand = brand;
        this.description = description;
    }

    public CarEntity(String model, CarColor color, int engineCapacityInLiters, int currentPrice, BrandEntity brand) {
        this.model = model;
        this.color = color;
        this.engineCapacityInLiters = engineCapacityInLiters;
        this.currentPrice = currentPrice;
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarEntity carEntity)) return false;
        return id == carEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", engineCapacityInLiters=" + engineCapacityInLiters +
                ", currentPrice=" + currentPrice +
                ", brand=" + brand +
                ", description='" + description + '\'' +
                ", prices=" + prices +
                '}';
    }
}
