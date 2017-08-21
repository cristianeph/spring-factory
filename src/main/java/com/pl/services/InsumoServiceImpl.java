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

import com.pl.model.InsumoModel;
import com.pl.model.InsumoModel_;
import com.pl.repository.InsumoRepository;

@Component("insumoService")
public class InsumoServiceImpl implements InsumoService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final InsumoRepository insumoRepository;
	
	public InsumoServiceImpl(InsumoRepository insumoRepository){
		
		this.insumoRepository = insumoRepository;
		
	}

	@Override
	public Page<InsumoModel> findByRelacion(Integer relacion) {
		
		return this.insumoRepository.findByRelacion(new PageRequest(0, Integer.MAX_VALUE), relacion);
		
	}

	@Override
	public InsumoModel findById(Integer id) {
		
		return this.insumoRepository.findById(id);
		
	}

	@Override
	public Page<InsumoModel> findAll(InsumoModel insumo) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<InsumoModel> query = builder.createQuery(InsumoModel.class);
		Root<InsumoModel> root = query.from(InsumoModel.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(insumo.getId() != 0){
			System.out.println(insumo.getId());
			predicates.add(builder.equal(root.get(InsumoModel_.id), insumo.getId()));
		}
		if(insumo.getDescripcion() != ""){
			System.out.println(insumo.getDescripcion());
			predicates.add(builder.like(root.get(InsumoModel_.descripcion), "%" + insumo.getDescripcion() + "%"));
		}
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		if(predicates.size() == 0){
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + insumo.getRows());
			if(insumo.getRows() > 0){
				return (Page<InsumoModel>) this.insumoRepository.findAll(new PageRequest(0, insumo.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<InsumoModel>) this.insumoRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
		}else{
			System.out.println("SI se mandaron parametros para la busqueda");
			Page<InsumoModel> insumos = new PageImpl<InsumoModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return insumos;
		}
	}

	@Override
	public Page<InsumoModel> getAll(PageRequest page) {
		return this.insumoRepository.findAll(page);
	}

}
