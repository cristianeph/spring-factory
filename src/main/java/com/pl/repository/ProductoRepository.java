package com.pl.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.ProductoModel;

public interface ProductoRepository extends Repository<ProductoModel, Long>{
	
	ProductoModel save(ProductoModel producto);
	ProductoModel deleteById(Integer id);
	ProductoModel findById(Integer id);
	Iterable<ProductoModel> findAll(Pageable page);

}
