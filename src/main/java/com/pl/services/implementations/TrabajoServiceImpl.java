package com.pl.services.implementations;

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

import com.pl.services.TrabajoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.pl.model.OrdenTrabajoModel;
import com.pl.model.metamodel.OrdenTrabajoModel_;
import com.pl.repository.TrabajoRepository;

@Component("trabajoService")
public class TrabajoServiceImpl implements TrabajoService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final TrabajoRepository trabajoRepository;
	
	public TrabajoServiceImpl(TrabajoRepository trabajoRepository){
		
		this.trabajoRepository = trabajoRepository;
		
	}

	@Override
	public OrdenTrabajoModel save(OrdenTrabajoModel trabajo) {
		
		return this.trabajoRepository.save(trabajo);
		
	}

	@Override
	public OrdenTrabajoModel deleteById(OrdenTrabajoModel trabajo) {
		
		return this.trabajoRepository.deleteById(trabajo.getId());
		
	}

	@Override
	public OrdenTrabajoModel findById(OrdenTrabajoModel trabajo) {
		
		return this.trabajoRepository.findById(trabajo.getId());
		
	}

	@Override
	public Page<OrdenTrabajoModel> findAll(OrdenTrabajoModel trabajo) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrdenTrabajoModel> query = builder.createQuery(OrdenTrabajoModel.class);
		Root<OrdenTrabajoModel> root = query.from(OrdenTrabajoModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(trabajo.getId() != 0){
			System.out.println(trabajo.getId());
			predicates.add(builder.equal(root.get(OrdenTrabajoModel_.id), trabajo.getId()));
		}
		
		if(trabajo.getComentarios() != ""){
			System.out.println(trabajo.getComentarios());
			predicates.add(builder.like(root.get(OrdenTrabajoModel_.comentarios), "%" + trabajo.getComentarios() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + trabajo.getRows());
			
			if(trabajo.getRows() > 0){
				return (Page<OrdenTrabajoModel>) this.trabajoRepository.findAll(new PageRequest(0, trabajo.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<OrdenTrabajoModel>) this.trabajoRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<OrdenTrabajoModel> trabajos = new PageImpl<OrdenTrabajoModel>(entityManager.createQuery(query.select(root)).getResultList());
			return trabajos;
			
		}
		
	}

	@Override
	public OrdenTrabajoModel findByPlanId(Integer id) {
		
		Iterable<OrdenTrabajoModel> trabajoResult = this.trabajoRepository.findByPlanId(new PageRequest(0, Integer.MAX_VALUE), id);
		
		OrdenTrabajoModel trabajo = null;
		
		for (OrdenTrabajoModel ordenTrabajoModel : trabajoResult) {
			trabajo = ordenTrabajoModel;
		}
		
		return trabajo;
		
	}

	@Override
	public Page<OrdenTrabajoModel> findAllBetweenPlanFecha(Date start, Date end) {
		
		return this.trabajoRepository.findAllBetweenPlanFecha(new PageRequest(0, Integer.MAX_VALUE), start, end);
		
	}

	@Override
	public Page<OrdenTrabajoModel> findMermaAllBetweenPlanFecha(Date start, Date end) {
		
		return this.trabajoRepository.findMermaAllBetweenPlanFecha(new PageRequest(0, Integer.MAX_VALUE), start, end);
		
	}

	@Override
	public Page<OrdenTrabajoModel> getAll(PageRequest page) {
		return this.trabajoRepository.findAll(page);
	}

	@Override
	public OrdenTrabajoModel getById(Integer id) {
		return this.trabajoRepository.findById(id);
	}

	@Override
	public Page<OrdenTrabajoModel> getAllInvalid(PageRequest page) {
		return this.trabajoRepository.findByCantidadOrHoras(page, new BigDecimal(0), 0);
	}

}
