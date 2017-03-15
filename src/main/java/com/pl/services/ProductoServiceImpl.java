package com.pl.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.pl.model.ProductoModel;
import com.pl.model.ProductoModel_;
import com.pl.repository.ProductoRepository;

@Component("productoService")
public class ProductoServiceImpl implements ProductoService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final ProductoRepository productoRepository;
	
	public ProductoServiceImpl(ProductoRepository productoRepository){
		
		this.productoRepository = productoRepository;
		
	}

	@Override
	public ProductoModel save(ProductoModel producto) {
		
		return this.productoRepository.save(producto);
		
	}

	@Override
	public ProductoModel deleteById(ProductoModel producto) {
		
		return this.productoRepository.deleteById(producto.getId());
		
	}

	@Override
	public ProductoModel findById(ProductoModel producto) {
		
		return this.productoRepository.findById(producto.getId());
		
	}

	@Override
	public Page<ProductoModel> findAll(ProductoModel producto) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductoModel> query = builder.createQuery(ProductoModel.class);
		Root<ProductoModel> root = query.from(ProductoModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(producto.getId() != 0){
			System.out.println(producto.getId());
			predicates.add(builder.equal(root.get(ProductoModel_.id), producto.getId()));
		}
		
		if(producto.getDescripcion() != ""){
			System.out.println(producto.getDescripcion());
			predicates.add(builder.like(root.get(ProductoModel_.descripcion), "%" + producto.getDescripcion() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + producto.getRows());
			
			if(producto.getRows() > 0){
				return (Page<ProductoModel>) this.productoRepository.findAll(new PageRequest(0, producto.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<ProductoModel>) this.productoRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<ProductoModel> productos = new PageImpl<ProductoModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return productos;
			
		}
		
	}

}
