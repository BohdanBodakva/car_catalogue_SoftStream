package eu.softstream.car_catalogue.repositories;

import eu.softstream.car_catalogue.DAO.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
}
