package com.pl.services.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pl.model.OrdenTrabajoModel;
import com.pl.repository.TrabajoRepository;
import com.pl.services.ParteProduccionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pl.model.ParteProduccionModel;
import com.pl.model.metamodel.ParteProduccionModel_;
import com.pl.repository.ParteProduccionRepository;

@Transactional
@Component("parteService")
public class ParteProduccionServiceImpl implements ParteProduccionService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final ParteProduccionRepository parteProduccionRepository;
	private final TrabajoRepository trabajoRepository;
	
	public ParteProduccionServiceImpl(ParteProduccionRepository parteProduccionRepository,
                                      TrabajoRepository trabajoRepository){
		this.trabajoRepository = trabajoRepository;
		this.parteProduccionRepository = parteProduccionRepository;
	}

	@Override
	public ParteProduccionModel save(ParteProduccionModel parte) {
		Collection<OrdenTrabajoModel> details = parte.getOrdenTrabajos();
		this.parteProduccionRepository.save(parte);
		ParteProduccionModel parteSaved = parte;
		details.stream().forEach(item -> {
			OrdenTrabajoModel trabajo = this.trabajoRepository.findById(item.getId());
			trabajo.setParteproduccion(parteSaved);
			trabajo = this.trabajoRepository.save(trabajo);
		});
		return parteSaved;
	}

	@Override
	public void deleteById(ParteProduccionModel tarjeta) {
		
		System.out.println("entro: " + tarjeta.getId());
		
		this.parteProduccionRepository.deleteById(tarjeta.getId());
		
	}

	@Override
	public ParteProduccionModel findById(ParteProduccionModel tarjeta) {
		
		return this.parteProduccionRepository.findById(tarjeta.getId());
		
	}

	@Override
	public Page<ParteProduccionModel> findAll(ParteProduccionModel tarjeta) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ParteProduccionModel> query = builder.createQuery(ParteProduccionModel.class);
		Root<ParteProduccionModel> root = query.from(ParteProduccionModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(tarjeta.getId() != 0){
			System.out.println(tarjeta.getId());
			predicates.add(builder.equal(root.get(ParteProduccionModel_.id), tarjeta.getId()));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + tarjeta.getRows());
			
			if(tarjeta.getRows() > 0){
				return (Page<ParteProduccionModel>) this.parteProduccionRepository.findAll(new PageRequest(0, tarjeta.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<ParteProduccionModel>) this.parteProduccionRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<ParteProduccionModel> tarjetas = new PageImpl<ParteProduccionModel>(entityManager.createQuery(query.select(root)).getResultList());
			return tarjetas;
			
		}
		
	}

	@Override
	public Page<ParteProduccionModel> getAll(PageRequest page) {
		return this.parteProduccionRepository.findAll(page);
	}

	@Override
	public ParteProduccionModel getById(Integer id) {
		return this.parteProduccionRepository.findById(id);
	}

}
