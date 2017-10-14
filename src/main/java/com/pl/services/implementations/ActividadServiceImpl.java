package com.pl.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pl.services.ActividadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.pl.model.ActividadModel;
import com.pl.model.metamodel.ActividadModel_;
import com.pl.repository.ActividadRepository;

@Component("actividadService")
public class ActividadServiceImpl implements ActividadService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final ActividadRepository actividadRepository;
	
	public ActividadServiceImpl(ActividadRepository actividadRepository){
		
		this.actividadRepository = actividadRepository;
		
	}

	@Override
	public ActividadModel save(ActividadModel actividad) {
		
		return this.actividadRepository.save(actividad);
		
	}

	@Override
	public ActividadModel deleteById(ActividadModel actividad) {
		
		return this.actividadRepository.deleteById(actividad.getId());
		
	}

	@Override
	public ActividadModel findById(ActividadModel actividad) {
		
		return this.actividadRepository.findById(actividad.getId());
		
	}

	@Override
	public Page<ActividadModel> findAll(ActividadModel actividad) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ActividadModel> query = builder.createQuery(ActividadModel.class);
		Root<ActividadModel> root = query.from(ActividadModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(actividad.getId() != 0){
			System.out.println(actividad.getId());
			predicates.add(builder.equal(root.get(ActividadModel_.id), actividad.getId()));
		}
		
		if(actividad.getDescripcion() != ""){
			System.out.println(actividad.getDescripcion());
			predicates.add(builder.like(root.get(ActividadModel_.descripcion), "%" + actividad.getDescripcion() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + actividad.getRows());
			
			if(actividad.getRows() > 0){
				return (Page<ActividadModel>) this.actividadRepository.findAll(new PageRequest(0, actividad.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<ActividadModel>) this.actividadRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<ActividadModel> actividades = new PageImpl<ActividadModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return actividades;
			
		}
		
	}

	@Override
	public Page<ActividadModel> getAll(PageRequest page) {
		return this.actividadRepository.findAll(page);
	}

	@Override
	public ActividadModel getById(Integer id) {
		return this.actividadRepository.findById(id);
	}

}
