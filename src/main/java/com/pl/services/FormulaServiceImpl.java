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
import org.springframework.transaction.annotation.Transactional;

import com.pl.model.FormulaModel;
import com.pl.model.FormulaModel_;
import com.pl.repository.FormulaRepository;

@Transactional
@Component("formulaService")
public class FormulaServiceImpl implements FormulaService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final FormulaRepository formulaRepository;
	
	public FormulaServiceImpl(FormulaRepository formulaRepository){
		
		this.formulaRepository = formulaRepository;
		
	}

	@Override
	public FormulaModel save(FormulaModel formula) {
		
		return this.formulaRepository.save(formula);
		
	}

	@Override
	public void deleteById(FormulaModel formula) {
		
		System.out.println("entro: " + formula.getId());
		
		this.formulaRepository.deleteById(formula.getId());
		
	}

	@Override
	public FormulaModel findById(FormulaModel formula) {
		
		return this.formulaRepository.findById(formula.getId());
		
	}

	@Override
	public Page<FormulaModel> findAll(FormulaModel formula) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FormulaModel> query = builder.createQuery(FormulaModel.class);
		Root<FormulaModel> root = query.from(FormulaModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(formula.getId() != 0){
			System.out.println(formula.getId());
			predicates.add(builder.equal(root.get(FormulaModel_.id), formula.getId()));
		}
		
		if(formula.getCodigo() != ""){
			System.out.println(formula.getCodigo());
			predicates.add(builder.like(root.get(FormulaModel_.codigo), "%" + formula.getCodigo() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + formula.getRows());
			
			if(formula.getRows() > 0){
				return (Page<FormulaModel>) this.formulaRepository.findAll(new PageRequest(0, formula.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<FormulaModel>) this.formulaRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<FormulaModel> formulas = new PageImpl<FormulaModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return formulas;
			
		}
		
	}

	@Override
	public Page<FormulaModel> getAll(PageRequest page) {
		return this.formulaRepository.findAll(page);
	}

}
