package com.pl.services;

import org.springframework.data.domain.Page;
import com.pl.model.ProductoModel;

public interface ProductoService {

	ProductoModel save(ProductoModel producto);
	ProductoModel deleteById(ProductoModel producto);
	ProductoModel findById(Integer id);
	Page<ProductoModel> findAll(ProductoModel producto);

}
