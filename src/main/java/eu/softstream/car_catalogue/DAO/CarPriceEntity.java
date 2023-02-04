package eu.softstream.car_catalogue.DAO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "car_price",
        uniqueConstraints=@UniqueConstraint(columnNames={"car_id", "date"})
)
@Data
@NoArgsConstructor
public class CarPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price_in_dollars")
    private int price;

    public CarPriceEntity(CarEntity car, LocalDate date, int price) {
        this.car = car;
        this.date = date;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarPriceEntity that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarPriceEntity{" +
                "id=" + id +
                ", car=" + car +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
