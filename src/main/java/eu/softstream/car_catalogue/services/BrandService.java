package eu.softstream.car_catalogue.services;

import eu.softstream.car_catalogue.DAO.BrandEntity;
import eu.softstream.car_catalogue.DAO.CarEntity;
import eu.softstream.car_catalogue.exceptions.BrandNotFoundException;
import eu.softstream.car_catalogue.exceptions.CarNotFoundException;

import java.util.Set;

public interface BrandService {
    Set<BrandEntity> getAllBrands();
    BrandEntity getBrandById(int brandId) throws BrandNotFoundException;
    BrandEntity createBrand(BrandEntity brand);
    BrandEntity updateBrandById(int brandId, BrandEntity brand) throws BrandNotFoundException;
    void deleteBrandById(int brandId) throws BrandNotFoundException;
}
