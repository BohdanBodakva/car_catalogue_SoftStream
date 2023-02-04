package eu.softstream.car_catalogue.repositories;

import eu.softstream.car_catalogue.DAO.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    List<CarEntity> findByBrandId(int brandId);

    @Query(value = "select * from car inner join car_price on car.id = car_price.car_id where car_price.date=:date and car_price.price_in_dollars between :startPrice and :endPrice",
            nativeQuery = true)
    List<CarEntity> findCarsByPriceAndDateBetween(@Param("startPrice") int startPrice,
                                                 @Param("date") Date date,
                                                 @Param("endPrice") int endPrice);

    @Query(value = "select * from car inner join car_price on car.id = car_price.car_id where car_price.date=:date and car_price.price_in_dollars >= :startPrice",
            nativeQuery = true)
    List<CarEntity> findCarsByPriceAndDateBetween(@Param("startPrice") int startPrice,
                                                 @Param("date") Date date);

    @Query(value = "select * from car inner join car_price on car.id = car_price.car_id where car_price.date=:date and car_price.price_in_dollars <= :endPrice",
            nativeQuery = true)
    List<CarEntity> findCarsByPriceAndDateBetween(@Param("date") Date date,
                                                 @Param("endPrice") int endPrice);

    @Query(value = "select * from car inner join car_price on car.id = car_price.car_id where car_price.date=:date",
            nativeQuery = true)
    List<CarEntity> findCarsByPriceAndDateBetween(@Param("date") Date date);

}
