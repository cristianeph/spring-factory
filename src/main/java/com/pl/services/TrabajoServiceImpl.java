package com.pl.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import com.pl.model.TrabajoModel;
import com.pl.model.TrabajoModel_;
import com.pl.repository.TrabajoRepository;

@Component("trabajoService")
public class TrabajoServiceImpl implements TrabajoService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final TrabajoRepository trabajoRepository;
	
	public TrabajoServiceImpl(TrabajoRepository trabajoRepository){
		
		this.trabajoRepository = trabajoRepository;
		
	}

	@Override
	public TrabajoModel save(TrabajoModel trabajo) {
		
		return this.trabajoRepository.save(trabajo);
		
	}

	@Override
	public TrabajoModel deleteById(TrabajoModel trabajo) {
		
		return this.trabajoRepository.deleteById(trabajo.getId());
		
	}

	@Override
	public TrabajoModel findById(TrabajoModel trabajo) {
		
		return this.trabajoRepository.findById(trabajo.getId());
		
	}

	@Override
	public Page<TrabajoModel> findAll(TrabajoModel trabajo) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TrabajoModel> query = builder.createQuery(TrabajoModel.class);
		Root<TrabajoModel> root = query.from(TrabajoModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(trabajo.getId() != 0){
			System.out.println(trabajo.getId());
			predicates.add(builder.equal(root.get(TrabajoModel_.id), trabajo.getId()));
		}
		
		if(trabajo.getComentarios() != ""){
			System.out.println(trabajo.getComentarios());
			predicates.add(builder.like(root.get(TrabajoModel_.comentarios), "%" + trabajo.getComentarios() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + trabajo.getRows());
			
			if(trabajo.getRows() > 0){
				return (Page<TrabajoModel>) this.trabajoRepository.findAll(new PageRequest(0, trabajo.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<TrabajoModel>) this.trabajoRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<TrabajoModel> trabajos = new PageImpl<TrabajoModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return trabajos;
			
		}
		
	}

	@Override
	public TrabajoModel findByPlanId(Integer id) {
		
		Iterable<TrabajoModel> trabajoResult = this.trabajoRepository.findByPlanId(new PageRequest(0, Integer.MAX_VALUE), id);
		
		TrabajoModel trabajo = null;
		
		for (TrabajoModel trabajoModel : trabajoResult) {
			trabajo = trabajoModel;
		}
		
		return trabajo;
		
	}

	@Override
	public Page<TrabajoModel> findAllBetweenPlanFecha(Date start, Date end) {
		
		return this.trabajoRepository.findAllBetweenPlanFecha(new PageRequest(0, Integer.MAX_VALUE), start, end);
		
	}

	@Override
	public Page<TrabajoModel> findMermaAllBetweenPlanFecha(Date start, Date end) {
		
		return this.trabajoRepository.findMermaAllBetweenPlanFecha(new PageRequest(0, Integer.MAX_VALUE), start, end);
		
	}

	@Override
	public Page<TrabajoModel> getAll(PageRequest page) {
		return this.trabajoRepository.findAll(page);
	}

	@Override
	public TrabajoModel getById(Integer id) {
		return this.trabajoRepository.findById(id);
	}

	@Override
	public Page<TrabajoModel> getAllInvalid(PageRequest page) {
		return this.trabajoRepository.findByCantidadOrHoras(page, new BigDecimal(0), 0);
	}

}
