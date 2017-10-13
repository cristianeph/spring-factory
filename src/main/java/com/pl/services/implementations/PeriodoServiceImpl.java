package com.pl.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pl.services.PeriodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.pl.model.PeriodoModel;
import com.pl.model.metamodel.PeriodoModel_;
import com.pl.repository.PeriodoRepository;

@Component("periodoService")
public class PeriodoServiceImpl implements PeriodoService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final PeriodoRepository periodoRepository;
	
	public PeriodoServiceImpl(PeriodoRepository periodoRepository){
		
		this.periodoRepository = periodoRepository;
		
	}

	@Override
	public PeriodoModel save(PeriodoModel periodo) {
		
		return this.periodoRepository.save(periodo);
		
	}

	@Override
	public PeriodoModel deleteById(PeriodoModel periodo) {
		
		return this.periodoRepository.deleteById(periodo.getId());
		
	}

	@Override
	public PeriodoModel findById(PeriodoModel periodo) {
		
		return this.periodoRepository.findById(periodo.getId());
		
	}

	@Override
	public Page<PeriodoModel> findAll(PeriodoModel periodo) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PeriodoModel> query = builder.createQuery(PeriodoModel.class);
		Root<PeriodoModel> root = query.from(PeriodoModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(periodo.getId() != 0){
			System.out.println(periodo.getId());
			predicates.add(builder.equal(root.get(PeriodoModel_.id), periodo.getId()));
		}
		
		if(periodo.getAno() != ""){
			System.out.println(periodo.getAno());
			predicates.add(builder.equal(root.get(PeriodoModel_.ano), periodo.getAno()));
		}
		
		if(periodo.getMes() != ""){
			System.out.println(periodo.getMes());
			predicates.add(builder.equal(root.get(PeriodoModel_.mes), periodo.getMes()));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + periodo.getRows());
			
			if(periodo.getRows() > 0){
				return (Page<PeriodoModel>) this.periodoRepository.findAll(new PageRequest(0, periodo.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<PeriodoModel>) this.periodoRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<PeriodoModel> periodos = new PageImpl<PeriodoModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return periodos;
			
		}
		
	}

}
