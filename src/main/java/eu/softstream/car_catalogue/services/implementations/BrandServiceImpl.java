package eu.softstream.car_catalogue.services.implementations;

import eu.softstream.car_catalogue.DAO.BrandEntity;
import eu.softstream.car_catalogue.DAO.CarEntity;
import eu.softstream.car_catalogue.exceptions.BrandNotFoundException;
import eu.softstream.car_catalogue.repositories.BrandRepository;
import eu.softstream.car_catalogue.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Set<BrandEntity> getAllBrands() {
        return new HashSet<>(brandRepository.findAll());
    }

    @Override
    public BrandEntity getBrandById(int brandId) throws BrandNotFoundException {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand with id = " + brandId + " doesn't exist"));
    }

    @Override
    public BrandEntity createBrand(BrandEntity brand) {
        return brandRepository.save(brand);
    }

    @Override
    public BrandEntity updateBrandById(int brandId, BrandEntity brand) throws BrandNotFoundException {
        BrandEntity brandToUpdate = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand with id = " + brandId + " doesn't exist"));

        brandToUpdate.setName(brand.getName());

        return brandRepository.save(brandToUpdate);
    }

    @Override
    public void deleteBrandById(int brandId) throws BrandNotFoundException {
        brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand with id = " + brandId + " doesn't exist"));

        brandRepository.deleteById(brandId);
    }
}
