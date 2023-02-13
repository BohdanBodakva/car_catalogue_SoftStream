package eu.softstream.car_catalogue.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    public BrandEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "brand")
    private Set<CarEntity> cars = new HashSet<>();

    public void addCar(CarEntity car){
        this.cars.add(car);
    }

    public void removeCar(CarEntity car){
        this.cars.remove(car);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrandEntity brand)) return false;
        return id == brand.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}
