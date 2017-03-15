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

import com.pl.model.PersonalModel;
import com.pl.model.PersonalModel_;
import com.pl.repository.PersonalRepository;

@Component("personalService")
public class PersonalServiceImpl implements PersonalService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final PersonalRepository personalRepository;
	
	public PersonalServiceImpl(PersonalRepository personalRepository){
		
		this.personalRepository = personalRepository;
		
	}

	@Override
	public PersonalModel save(PersonalModel personal) {
		
		return this.personalRepository.save(personal);
		
	}

	@Override
	public PersonalModel deleteById(PersonalModel personal) {
		
		return this.personalRepository.deleteById(personal.getId());
		
	}

	@Override
	public PersonalModel findById(PersonalModel personal) {
		
		return this.personalRepository.findById(personal.getId());
		
	}

	@Override
	public Page<PersonalModel> findAll(PersonalModel personal) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonalModel> query = builder.createQuery(PersonalModel.class);
		Root<PersonalModel> root = query.from(PersonalModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(personal.getId() != 0){
			System.out.println(personal.getId());
			predicates.add(builder.equal(root.get(PersonalModel_.id), personal.getId()));
		}
		
		if(personal.getCodigo() != ""){
			System.out.println(personal.getCodigo());
			predicates.add(builder.like(root.get(PersonalModel_.codigo), "%" + personal.getCodigo() + "%"));
		}
		
		if(personal.getNombres() != ""){
			System.out.println(personal.getNombres());
			predicates.add(builder.like(root.get(PersonalModel_.nombres), "%" + personal.getNombres() + "%"));
		}
		
		if(personal.getApellidos() != ""){
			System.out.println(personal.getApellidos());
			predicates.add(builder.like(root.get(PersonalModel_.apellidos), "%" + personal.getApellidos() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + personal.getRows());
			
			if(personal.getRows() > 0){
				return (Page<PersonalModel>) this.personalRepository.findAll(new PageRequest(0, personal.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<PersonalModel>) this.personalRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<PersonalModel> personas = new PageImpl<PersonalModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return personas;
			
		}
		
	}

}
