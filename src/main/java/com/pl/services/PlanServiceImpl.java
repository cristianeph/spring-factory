package com.pl.services;

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

import com.pl.model.PlanModel;
import com.pl.model.PlanModel_;
import com.pl.repository.PlanRepository;

@Component("planService")
public class PlanServiceImpl implements PlanService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final PlanRepository planRepository;
	
	public PlanServiceImpl(PlanRepository planRepository){
		
		this.planRepository = planRepository;
		
	}

	@Override
	public PlanModel save(PlanModel plan) {
		
		return this.planRepository.save(plan);
		
	}

	@Override
	public PlanModel deleteById(PlanModel plan) {
		
		return this.planRepository.deleteById(plan.getId());
		
	}

	@Override
	public PlanModel findById(PlanModel plan) {
		
		return this.planRepository.findById(plan.getId());
		
	}

	@Override
	public Page<PlanModel> findAll(PlanModel plan) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanModel> query = builder.createQuery(PlanModel.class);
		Root<PlanModel> root = query.from(PlanModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(plan.getId() != 0){
			System.out.println(plan.getId());
			predicates.add(builder.equal(root.get(PlanModel_.id), plan.getId()));
		}
		
		if(plan.getCodigo() != ""){
			System.out.println(plan.getCodigo());
			predicates.add(builder.like(root.get(PlanModel_.codigo), "%" + plan.getCodigo() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + plan.getRows());
			
			if(plan.getRows() > 0){
				return (Page<PlanModel>) this.planRepository.findAll(new PageRequest(0, plan.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<PlanModel>) this.planRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<PlanModel> ingresos = new PageImpl<PlanModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return ingresos;
			
		}
		
	}

	@Override
	public Page<PlanModel> findAllBetweenFecha(Date start, Date end) {
		
		return this.planRepository.findAllBetweenFecha(new PageRequest(0, Integer.MAX_VALUE), start, end);
		
	}

	@Override
	public PlanModel findByPedidoId(Integer id) {
		
		Iterable<PlanModel> trabajoResult = this.planRepository.findByPedidoId(new PageRequest(0, Integer.MAX_VALUE), id);
		
		PlanModel plan = null;
		
		for (PlanModel planModel : trabajoResult) {
			plan = planModel;
		}
		
		return plan;
		
	}

	@Override
	public Page<PlanModel> findEstadoAllBetweenFecha(String estado, Date start, Date end) {
		
		return this.planRepository.findEstadoAllBetweenFecha(new PageRequest(0, Integer.MAX_VALUE), estado, start, end);
		
	}

}
