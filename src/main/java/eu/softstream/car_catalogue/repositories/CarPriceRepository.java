package eu.softstream.car_catalogue.repositories;

import eu.softstream.car_catalogue.DAO.CarPriceEntity;
import org.cef.misc.IntRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarPriceRepository extends JpaRepository<CarPriceEntity, Integer> {
    List<CarPriceEntity> findAllByCarId(int carId);
    Optional<CarPriceEntity> findByCarIdAndDate(int carId, Date date);

    @Query(value = "select * from car_price order by car_price.date",
            nativeQuery = true)
    List<CarPriceEntity> findAllByCarIdSortedByDateASC(int carId);

    @Query(value = "select * from car_price order by car_price.date desc",
            nativeQuery = true)
    List<CarPriceEntity> findAllByCarIdSortedByDateDESC(int carId);
}
