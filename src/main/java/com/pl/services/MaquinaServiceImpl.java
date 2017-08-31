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

import com.pl.model.MaquinaModel;
import com.pl.model.MaquinaModel_;
import com.pl.repository.MaquinaRepository;

@Component("maquinaService")
public class MaquinaServiceImpl implements MaquinaService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final MaquinaRepository maquinaRepository;
	
	public MaquinaServiceImpl(MaquinaRepository maquinaRepository){
		
		this.maquinaRepository = maquinaRepository;
		
	}

	@Override
	public MaquinaModel save(MaquinaModel maquina) {
		
		return this.maquinaRepository.save(maquina);
		
	}

	@Override
	public MaquinaModel deleteById(MaquinaModel maquina) {
		
		return this.maquinaRepository.deleteById(maquina.getId());
		
	}

	@Override
	public MaquinaModel findById(MaquinaModel maquina) {
		
		return this.maquinaRepository.findById(maquina.getId());
		
	}

	@Override
	public Page<MaquinaModel> findAll(MaquinaModel maquina) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaquinaModel> query = builder.createQuery(MaquinaModel.class);
		Root<MaquinaModel> root = query.from(MaquinaModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(maquina.getId() != 0){
			System.out.println(maquina.getId());
			predicates.add(builder.equal(root.get(MaquinaModel_.id), maquina.getId()));
		}
		
		if(maquina.getDescripcion() != ""){
			System.out.println(maquina.getDescripcion());
			predicates.add(builder.like(root.get(MaquinaModel_.descripcion), "%" + maquina.getDescripcion() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + maquina.getRows());
			
			if(maquina.getRows() > 0){
				return (Page<MaquinaModel>) this.maquinaRepository.findAll(new PageRequest(0, maquina.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<MaquinaModel>) this.maquinaRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<MaquinaModel> maquinas = new PageImpl<MaquinaModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return maquinas;
			
		}
		
	}

	@Override
	public Page<MaquinaModel> getAll(PageRequest page) {
		return this.maquinaRepository.findAll(page);
	}

	@Override
	public MaquinaModel getById(Integer id) {
		return this.maquinaRepository.findById(id);
	}

}
